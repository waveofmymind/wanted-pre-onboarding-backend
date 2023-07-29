package waveofmymind.wanted.global.error.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import waveofmymind.wanted.global.error.ErrorCode;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.InvalidPasswordException;
import waveofmymind.wanted.global.error.exception.TokenVerifyFailedException;
import waveofmymind.wanted.global.error.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateJoinException.class)
    protected ResponseEntity<ErrorResponse> duplicateJoinException() {
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

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> userNotFoundException() {
        return createErrorResponse(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(TokenVerifyFailedException.class)
    protected ResponseEntity<ErrorResponse> tokenVerifyFailedException() {
        return createErrorResponse(ErrorCode.TOKEN_VERIFY_FAILED);
    }
    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.toResponseEntity(errorCode);
    }
}
