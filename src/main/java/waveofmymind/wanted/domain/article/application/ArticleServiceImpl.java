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
    public Long registerArticle(RegisterArticleCommand command) {
        return articleRepository.saveArticle(command.toEntity()).getId();
    }
}
