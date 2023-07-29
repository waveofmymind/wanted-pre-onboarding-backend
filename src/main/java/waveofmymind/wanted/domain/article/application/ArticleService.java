package waveofmymind.wanted.domain.article.application;

import org.springframework.data.domain.Pageable;
import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.FindArticleResponse;

import java.util.List;

public interface ArticleService {

    Long registerArticle(RegisterArticleCommand command);

    Long editArticle(EditArticleCommand command);

    Long deleteArticle(Long articleId, Long userId);

    FindArticleResponse getArticle(Long articleId);

    List<FindArticleResponse> getArticleList(Pageable page);
}
