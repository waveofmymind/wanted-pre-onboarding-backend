package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD_PARAMETER);
    }
}
