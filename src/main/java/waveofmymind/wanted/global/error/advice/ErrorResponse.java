package waveofmymind.wanted.global.error.advice;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import waveofmymind.wanted.global.error.ErrorCode;


@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .error(errorCode.getStatus().name())
                        .message(errorCode.getMessage())
                        .build());
    }
}