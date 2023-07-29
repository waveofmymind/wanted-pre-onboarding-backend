package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class DuplicateJoinException extends BusinessException {
    public DuplicateJoinException() {
        super(ErrorCode.DUPLICATE_JOIN);
    }
}
