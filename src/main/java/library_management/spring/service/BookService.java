package library_management.spring.service;

import library_management.spring.dto.Request.BookRequest;
import library_management.spring.dto.Response.BookResponse;
import library_management.spring.entity.BookEntity;

public interface BookService {

    BookResponse getBookById(Long id);
    BookResponse addBook(BookRequest request);
    void updateBook(Long id, BookRequest request);
    void deleteBook(Long id);

    BookEntity findBookById(Long id);
}
