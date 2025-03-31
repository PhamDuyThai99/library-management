package library_management.spring.service;

import library_management.spring.dto.Response.BorrowRecordDetailResponse;
import library_management.spring.entity.BookEntity;
import library_management.spring.entity.BorrowRecordEntity;
import library_management.spring.entity.UserEntity;
import library_management.spring.exception.ApiError;
import library_management.spring.exception.AppException;
import library_management.spring.repository.BorrowRecordsRepository;
import library_management.spring.util.BookMapper;
import library_management.spring.util.BorrowRecordDetailMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private final BorrowRecordsRepository borrowRecordsRepository;
    private final BookService bookService;
    private final UserService userService;
    private static final int BORROW_DAYS = 14;
    private static final double FEE_MULTIPLIER_IN_DAY = 0.5;

    public BorrowRecordServiceImpl(BorrowRecordsRepository borrowRecordsRepository,
                                   BookService bookService,
                                   UserService userService) {
        this.borrowRecordsRepository = borrowRecordsRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public BorrowRecordDetailResponse getBorrowRecordDetail(Long id) {
        BorrowRecordEntity borrowRecordEntity = findRecordById(id);
        return BorrowRecordDetailMapper.toResponse(borrowRecordEntity);
    }

    @Override
    public void borrowBook(Long userId, Long bookId) {
        // Check user if exist
        UserEntity user = userService.findUserById(userId);

        // Check book if exist
        BookEntity book = bookService.findBookById(bookId);

        // Check if book is available
        if(!book.isAvailable()){
            throw new AppException(ApiError.BOOK_NOT_AVAILABLE);
        }

        // Create borrow record
        BorrowRecordEntity borrowRecord = BorrowRecordEntity.builder()
                        .user(user)
                        .book(book)
                        .borrowDate(LocalDateTime.now())
                        .dueDate(LocalDateTime.now().plusDays(BORROW_DAYS))
                        .build();

        // Save borrow record
        borrowRecordsRepository.save(borrowRecord);

        // update book status
        book.setAvailable(false);
        bookService.updateBook(bookId, BookMapper.toRequest(book));
    }

    @Override
    public void returnBook(Long userId, Long bookId) {
        BorrowRecordEntity borrowRecord = borrowRecordsRepository.findByUserIdAndBookId(userId, bookId).orElseThrow(
                () -> new AppException(ApiError.RECORD_NOT_FOUND)
        );
        borrowRecord.setReturnDate(LocalDateTime.now());

        // update book status
        BookEntity book = bookService.findBookById(bookId);
        book.setAvailable(true);
        bookService.updateBook(bookId, BookMapper.toRequest(book));

        // calculate fine
        if (borrowRecord.getDueDate().isAfter(borrowRecord.getReturnDate())) {
            long daysLate = ChronoUnit.DAYS.between(borrowRecord.getDueDate(), borrowRecord.getReturnDate());
            double fineAmount = daysLate * FEE_MULTIPLIER_IN_DAY;
            borrowRecord.setFineAmount(fineAmount);
        }

        // save borrow record
        borrowRecordsRepository.save(borrowRecord);
    }

    private BorrowRecordEntity findRecordById(Long id){
        return borrowRecordsRepository.findById(id)
                .orElseThrow(
                        () -> new AppException(ApiError.RECORD_NOT_FOUND)
                );
    }
}
