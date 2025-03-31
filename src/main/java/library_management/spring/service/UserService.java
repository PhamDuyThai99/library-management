package library_management.spring.service;

import library_management.spring.dto.Request.UserRequest;
import library_management.spring.dto.Response.UserResponse;
import library_management.spring.entity.UserEntity;

public interface UserService {
    UserResponse registerUser(UserRequest request);
    UserResponse getUserById(Long id);

    UserEntity findUserById(Long id);

    UserEntity findUserByEmail(String email);
}
