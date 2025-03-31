package library_management.spring.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{
    private ApiError apiError;

    public AppException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }
}
