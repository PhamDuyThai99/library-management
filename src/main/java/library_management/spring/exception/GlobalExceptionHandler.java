package library_management.spring.exception;

import library_management.spring.dto.Response.ApiResponse;
import library_management.spring.dto.Response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Void>> handlingRunTimeException(RuntimeException e) {
        ResponseStatus responseStatus = ResponseStatus.builder()
                .code(ApiError.UNKNOWN_ERROR.getCode())
                .message(ApiError.UNKNOWN_ERROR.getMessage())
                .build();

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .status(responseStatus)
                .data(null)
                .build();

        return ResponseEntity
                .status(ApiError.UNKNOWN_ERROR.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> handlingAppException(AppException e) {
        ResponseStatus responseStatus = ResponseStatus.builder()
                .code(e.getApiError().getCode())
                .message(e.getApiError().getMessage())
                .build();

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .status(responseStatus)
                .data(null)
                .build();

        return ResponseEntity
                .status(e.getApiError().getStatusCode())
                .body(apiResponse);
    }
}
