package waveofmymind.wanted.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "사용자 정보가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
    DUPLICATE_JOIN(HttpStatus.BAD_REQUEST, "이미 가입된 사용자입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "올바른 형식이 아닙니다."),
    INVALID_PASSWORD_PARAMETER(HttpStatus.BAD_REQUEST, "올바른 비밀번호 형식이 아닙니다."),
    TOKEN_VERIFY_FAILED(HttpStatus.BAD_REQUEST, "토큰 검증에 실패했습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."),
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

    private final HttpStatus status;

    private final String message;
}
