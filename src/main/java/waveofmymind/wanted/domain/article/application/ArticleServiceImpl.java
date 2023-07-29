package waveofmymind.wanted.domain.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.infrastructure.ArticleRepository;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.global.error.exception.ArticleNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public Long registerArticle(RegisterArticleCommand command) {
        return articleRepository.saveArticle(command.toEntity()).getId();
    }

    @Transactional
    @Override
    public Long editArticle(EditArticleCommand command) {
        Article article = articleRepository.findArticleById(command.articleId())
                .orElseThrow(ArticleNotFoundException::new);
        article.validate(command.userId());
        article.edit(command.title(), command.content());
        return command.articleId();
    }

    @Override
    public Long deleteArticle(Long articleId, Long userId) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(ArticleNotFoundException::new);
        article.validate(userId);
        articleRepository.deleteArticle(articleId);
        return articleId;
    }
}
