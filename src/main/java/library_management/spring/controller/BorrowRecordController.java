package library_management.spring.controller;

import library_management.spring.dto.Response.ApiResponse;
import library_management.spring.dto.Response.BorrowRecordDetailResponse;
import library_management.spring.dto.Response.ResponseStatus;
import library_management.spring.service.BorrowRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/library")
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/{id}")
    public ApiResponse<BorrowRecordDetailResponse> getBorrowRecordDetail(@PathVariable Long id) {
        return ApiResponse.<BorrowRecordDetailResponse>builder()
                .status(new ResponseStatus())
                .data(borrowRecordService.getBorrowRecordDetail(id))
                .build();
    }
    @PostMapping("/borrow/{userId}/{bookId}")
    public ApiResponse<Void> borrowBook(Long userId, Long bookId) {
        borrowRecordService.borrowBook(userId, bookId);
        return ApiResponse.<Void>builder()
                .status(new ResponseStatus())
                .data(null)
                .build();
    }

    @PostMapping("/return/{userId}/{bookId}")
    public ApiResponse<Void> returnBook(Long userId, Long bookId) {
        borrowRecordService.returnBook(userId, bookId);
        return ApiResponse.<Void>builder()
                .status(new ResponseStatus())
                .data(null)
                .build();
    }

}
