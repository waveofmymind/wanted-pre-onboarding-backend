package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class TokenVerifyFailedException extends BusinessException {
    public TokenVerifyFailedException() {
        super(ErrorCode.TOKEN_VERIFY_FAILED);
    }
}
