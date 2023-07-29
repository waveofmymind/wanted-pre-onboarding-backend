package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class UnIdentifiedUserException extends BusinessException {
    public UnIdentifiedUserException() {
        super(ErrorCode.AUTHENTICATION_FAILED);
    }
}
