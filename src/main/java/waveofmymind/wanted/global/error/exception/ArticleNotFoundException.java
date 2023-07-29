package waveofmymind.wanted.global.error.exception;

import waveofmymind.wanted.global.error.BusinessException;
import waveofmymind.wanted.global.error.ErrorCode;

public class ArticleNotFoundException extends BusinessException {
    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
