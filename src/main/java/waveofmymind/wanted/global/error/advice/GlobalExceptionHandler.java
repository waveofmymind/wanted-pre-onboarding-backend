package waveofmymind.wanted.global.error.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import waveofmymind.wanted.global.error.ErrorCode;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateJoinException.class)
    public ResponseEntity<ErrorResponse> duplicateJoinException(DuplicateJoinException e,
                                                                 HttpServletRequest request){
        return createErrorResponse(ErrorCode.DUPLICATE_JOIN);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.toResponseEntity(errorCode);
    }
}
