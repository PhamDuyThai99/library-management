package library_management.spring.util;

import library_management.spring.dto.Request.BookRequest;
import library_management.spring.dto.Response.BookResponse;
import library_management.spring.entity.BookEntity;

public class BookMapper {

    public static BookResponse toResponse(BookEntity entity) {
        return BookResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .isAvailable(entity.isAvailable())
                .build();
    }

    public static BookEntity toEntity(BookRequest request) {
        return BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .build();
    }

    public static BookRequest toRequest(BookEntity entity) {
        return BookRequest.builder()
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .build();
    }
}
