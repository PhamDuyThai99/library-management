package library_management.spring.util;

import library_management.spring.dto.Request.BookRequest;
import library_management.spring.dto.Request.UserRequest;
import library_management.spring.dto.Response.BookResponse;
import library_management.spring.dto.Response.UserResponse;
import library_management.spring.entity.BookEntity;
import library_management.spring.entity.Role;
import library_management.spring.entity.UserEntity;
import org.apache.coyote.Request;

public class UserMapper {

    public static UserResponse toResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .role(entity.getRole().getName())
                .build();
    }

    public static UserEntity toEntity(UserRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .role(Role.valueOf(request.getRole()))
                .build();
    }
}
