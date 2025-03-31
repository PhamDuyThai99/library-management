package library_management.spring.controller;

import library_management.spring.dto.Request.BookRequest;
import library_management.spring.dto.Response.ApiResponse;
import library_management.spring.dto.Response.BookResponse;
import library_management.spring.dto.Response.ResponseStatus;
import library_management.spring.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ApiResponse<BookResponse> addBook(@RequestBody BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .status(new ResponseStatus())
                .data(bookService.addBook(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponse> getBookById(@PathVariable Long id) {
        return ApiResponse.<BookResponse>builder()
                .status(new ResponseStatus())
                .data(bookService.getBookById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateById(@PathVariable("id") Long id,
                                        @RequestBody BookRequest request) {
        bookService.updateBook(id, request);
        return ApiResponse.<Void>builder()
                .status(new ResponseStatus())
                .data(null)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> updateById(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ApiResponse.<Void>builder()
                .status(new ResponseStatus())
                .data(null)
                .build();
    }
}
