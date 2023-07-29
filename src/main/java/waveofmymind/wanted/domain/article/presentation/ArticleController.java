package waveofmymind.wanted.domain.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waveofmymind.wanted.domain.article.application.ArticleService;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;
import waveofmymind.wanted.global.auth.AuthCheck;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @AuthCheck
    @PostMapping
    public void register(@RequestBody RegisterArticleRequest request) {
        articleService.saveArticle(request.toCommand());
    }
}
