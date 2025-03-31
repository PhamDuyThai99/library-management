package library_management.spring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ApiError {
    UNKNOWN_ERROR(999, "General Error", HttpStatus.INTERNAL_SERVER_ERROR),
    BOOK_NOT_FOUND(101, "Book Id Not Found", HttpStatus.NOT_FOUND),
    BOOK_NOT_AVAILABLE(102, "Book Not Available", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(201,"User Id Not Found", HttpStatus.NOT_FOUND),
    EMAIL_IN_USED(202, "User Email In Used", HttpStatus.BAD_REQUEST),
    RECORD_NOT_FOUND(301,"Record Id Not Found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401,"invalid credential", HttpStatus.UNAUTHORIZED);

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ApiError(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
