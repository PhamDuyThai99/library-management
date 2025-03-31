package library_management.spring.service;

import com.nimbusds.jose.JOSEException;
import library_management.spring.dto.Request.AuthRequest;
import library_management.spring.dto.Response.AuthResponse;
import library_management.spring.entity.UserEntity;
import library_management.spring.exception.ApiError;
import library_management.spring.exception.AppException;
import library_management.spring.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public AuthResponse login(AuthRequest request) throws JOSEException {
        UserEntity user = userService.findUserByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new AppException(ApiError.UNAUTHENTICATED);
        }
        String token = JwtUtil.generateToken(user.getEmail(), user.getRole().getName());
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
