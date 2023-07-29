package waveofmymind.wanted.global.error.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import waveofmymind.wanted.global.error.ErrorCode;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.InvalidPasswordException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateJoinException.class)
    protected ResponseEntity<ErrorResponse> duplicateJoinException(){
        return createErrorResponse(ErrorCode.DUPLICATE_JOIN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> constraintViolationValidation() {
        return createErrorResponse(ErrorCode.INVALID_EMAIL_PARAMETER);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ErrorResponse> invalidPasswordException() {
        return createErrorResponse(ErrorCode.INVALID_PASSWORD_PARAMETER);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.toResponseEntity(errorCode);
    }
}
