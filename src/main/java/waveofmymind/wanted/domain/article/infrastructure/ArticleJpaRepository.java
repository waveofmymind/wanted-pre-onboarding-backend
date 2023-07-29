package waveofmymind.wanted.domain.article.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import waveofmymind.wanted.domain.article.domain.Article;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
