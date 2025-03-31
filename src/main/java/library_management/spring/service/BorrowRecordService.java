package library_management.spring.service;

import library_management.spring.dto.Response.BorrowRecordDetailResponse;

public interface BorrowRecordService {
    BorrowRecordDetailResponse getBorrowRecordDetail(Long id);
    void borrowBook(Long userId, Long bookId);
    void returnBook(Long userId, Long bookId);
}
