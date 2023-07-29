package waveofmymind.wanted.domain.article.infrastructure;

import org.springframework.data.domain.Pageable;
import waveofmymind.wanted.domain.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article saveArticle(Article article);

    Optional<Article> findArticleById(Long id);

    void deleteArticle(Long articleId);

    List<Article> findArticleList(Pageable page);
}
