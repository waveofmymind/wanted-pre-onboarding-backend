package waveofmymind.wanted.global.error.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import waveofmymind.wanted.global.error.ErrorCode;
import waveofmymind.wanted.global.error.exception.*;

import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateJoinException.class)
    protected ResponseEntity<ErrorResponse> duplicateJoinException() {
        return createErrorResponse(ErrorCode.DUPLICATE_JOIN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> constraintViolationValidation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String violationMessage = violations.iterator().next().getMessage();

        return createErrorResponse(ErrorCode.INVALID_PARAMETER, violationMessage);
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
    @ExceptionHandler(ArticleNotFoundException.class)
    protected ResponseEntity<ErrorResponse> articleNotFoundException() {
        return createErrorResponse(ErrorCode.ARTICLE_NOT_FOUND);
    }
    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.toResponseEntity(errorCode);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.toResponseEntity(errorCode, message);
    }
}
