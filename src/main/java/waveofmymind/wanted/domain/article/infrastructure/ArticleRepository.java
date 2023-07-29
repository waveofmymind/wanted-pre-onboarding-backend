package waveofmymind.wanted.domain.article.infrastructure;

import waveofmymind.wanted.domain.article.domain.Article;

import java.util.Optional;

public interface ArticleRepository {

    Article saveArticle(Article article);

    Optional<Article> findArticleById(Long id);
}
