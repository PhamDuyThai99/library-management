package library_management.spring.dto.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest {
    private String email;
    private String password;
}
