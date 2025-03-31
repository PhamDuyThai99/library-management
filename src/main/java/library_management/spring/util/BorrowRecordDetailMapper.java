package library_management.spring.util;

import library_management.spring.dto.Response.BorrowRecordDetailResponse;
import library_management.spring.entity.BorrowRecordEntity;

public class BorrowRecordDetailMapper {
    public static BorrowRecordDetailResponse toResponse(BorrowRecordEntity entity) {
        return BorrowRecordDetailResponse.builder()
                .userId(entity.getUser().getId())
                .bookId(entity.getBook().getId())
                .borrowDate(entity.getBorrowDate())
                .dueDate(entity.getDueDate())
                .returnDate(entity.getReturnDate())
                .fineAmount(entity.getFineAmount())
                .build();
    }
}
