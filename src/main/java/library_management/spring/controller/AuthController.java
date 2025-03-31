package library_management.spring.controller;

import com.nimbusds.jose.JOSEException;
import library_management.spring.dto.Request.AuthRequest;
import library_management.spring.dto.Response.ApiResponse;
import library_management.spring.dto.Response.AuthResponse;
import library_management.spring.dto.Response.ResponseStatus;
import library_management.spring.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) throws JOSEException {
        return ApiResponse.<AuthResponse>builder()
                .status(new ResponseStatus())
                .data(authService.login(request))
                .build();
    }

}
