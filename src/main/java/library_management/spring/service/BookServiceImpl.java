package library_management.spring.service;

import library_management.spring.dto.Request.BookRequest;
import library_management.spring.dto.Response.BookResponse;
import library_management.spring.entity.BookEntity;
import library_management.spring.exception.ApiError;
import library_management.spring.exception.AppException;
import library_management.spring.repository.BookRepository;
import library_management.spring.util.BookMapper;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse getBookById(Long id) {
        BookEntity bookEntity = findBookById(id);
        return BookMapper.toResponse(bookEntity);
    }

    @Override
    public BookResponse addBook(BookRequest request) {
        BookEntity bookEntity = BookMapper.toEntity(request);
        return BookMapper.toResponse(bookRepository.save(bookEntity));
    }

    @Override
    public void updateBook(Long id, BookRequest request) {
        BookEntity bookEntity = findBookById(id);
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity bookEntity = findBookById(id);
        bookRepository.delete(bookEntity);
    }

    public BookEntity findBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new AppException(ApiError.BOOK_NOT_FOUND)
                );
    }
}
