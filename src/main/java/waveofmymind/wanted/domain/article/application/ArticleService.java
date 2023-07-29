package waveofmymind.wanted.domain.article.application;

import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;

public interface ArticleService {

    Long registerArticle(RegisterArticleCommand command);

    Long editArticle(EditArticleCommand command);

    Long deleteArticle(Long articleId, Long userId);
}
