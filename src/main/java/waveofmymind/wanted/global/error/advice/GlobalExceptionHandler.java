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

    @ExceptionHandler(UnIdentifiedUserException.class)
    protected ResponseEntity<ErrorResponse> unIdentifiedUserException(UnIdentifiedUserException e) {
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(DuplicateJoinException.class)
    protected ResponseEntity<ErrorResponse> duplicateJoinException(DuplicateJoinException e) {
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> constraintViolationValidation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String violationMessage = violations.iterator().next().getMessage();

        return createErrorResponse(ErrorCode.INVALID_PARAMETER, violationMessage);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ErrorResponse> invalidPasswordException(InvalidPasswordException e) {
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e) {
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(TokenVerifyFailedException.class)
    protected ResponseEntity<ErrorResponse> tokenVerifyFailedException(TokenVerifyFailedException e) {
        return createErrorResponse(e.getErrorCode());
    }
    @ExceptionHandler(ArticleNotFoundException.class)
    protected ResponseEntity<ErrorResponse> articleNotFoundException(ArticleNotFoundException e) {
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity<ErrorResponse> unAuthorizedException(UnAuthorizedException e) {
        return createErrorResponse(e.getErrorCode());
    }
    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.toResponseEntity(errorCode);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.toResponseEntity(errorCode, message);
    }
}
