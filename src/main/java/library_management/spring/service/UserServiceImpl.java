package library_management.spring.service;

import library_management.spring.dto.Request.UserRequest;
import library_management.spring.dto.Response.UserResponse;
import library_management.spring.entity.UserEntity;
import library_management.spring.exception.ApiError;
import library_management.spring.exception.AppException;
import library_management.spring.repository.UserRepository;
import library_management.spring.util.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ApiError.EMAIL_IN_USED);
        }
        UserEntity userEntity = UserMapper.toEntity(request);

        // Encrypt password
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        return UserMapper.toResponse(userRepository.save(userEntity));
    }

    @Override
    public UserResponse getUserById(Long id) {
        UserEntity userEntity = findUserById(id);
        return UserMapper.toResponse(userEntity);
    }


    @Override
    public UserEntity findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new AppException(ApiError.USER_NOT_FOUND)
                );
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new AppException(ApiError.USER_NOT_FOUND)
                );
    }
}
