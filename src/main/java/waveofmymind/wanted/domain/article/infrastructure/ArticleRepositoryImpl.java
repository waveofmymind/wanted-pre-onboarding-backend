package waveofmymind.wanted.domain.article.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import waveofmymind.wanted.domain.article.domain.Article;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleJpaRepository articleJpaRepository;

    @Override
    public Article saveArticle(Article article) {
        return articleJpaRepository.save(article);
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return articleJpaRepository.findById(id);
    }

    @Override
    public List<Article> findArticleList(Pageable page) {
        return articleJpaRepository.findAllByOrderByCreatedDateDesc(page).getContent();
    }

    @Override
    public void deleteArticle(Long articleId) {
        articleJpaRepository.deleteById(articleId);
    }
}
