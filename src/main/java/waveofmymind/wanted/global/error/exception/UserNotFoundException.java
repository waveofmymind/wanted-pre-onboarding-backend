package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
