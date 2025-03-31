package library_management.spring.service;

import com.nimbusds.jose.JOSEException;
import library_management.spring.dto.Request.AuthRequest;
import library_management.spring.dto.Response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request) throws JOSEException;
}
