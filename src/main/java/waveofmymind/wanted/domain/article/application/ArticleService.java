package waveofmymind.wanted.domain.article.application;

import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;

public interface ArticleService {

    void saveArticle(RegisterArticleCommand command);
}
