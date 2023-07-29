package waveofmymind.wanted.domain.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.infrastructure.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    @Override
    public void saveArticle(RegisterArticleCommand command) {
        articleRepository.saveArticle(command.toEntity());
    }
}
