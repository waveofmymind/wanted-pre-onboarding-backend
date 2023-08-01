package waveofmymind.wanted.domain.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import waveofmymind.wanted.domain.article.application.ArticleService;
import waveofmymind.wanted.domain.article.application.dto.FindArticleListResponse;
import waveofmymind.wanted.domain.article.application.dto.FindArticleResponse;
import waveofmymind.wanted.domain.article.presentation.dto.EditArticleRequest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleResponse;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.global.auth.AuthCheck;
import waveofmymind.wanted.global.auth.UserContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @AuthCheck
    @PostMapping
    public RegisterArticleResponse register(@RequestBody RegisterArticleRequest request) {
        User user = UserContext.currentUser.get();
        return RegisterArticleResponse.of(articleService.registerArticle(request.toCommand(user.getId())));
    }

    @AuthCheck
    @PutMapping("/{articleId}")
    public Long edit(@PathVariable Long articleId, @RequestBody EditArticleRequest request) {
        User user = UserContext.currentUser.get();
        return articleService.editArticle(request.toEditCommand(articleId, user.getId()));
    }

    @AuthCheck
    @DeleteMapping("/{articleId}")
    public Long delete(@PathVariable Long articleId) {
        User user = UserContext.currentUser.get();
        return articleService.deleteArticle(articleId, user.getId());
    }

    @GetMapping("/{articleId}")
    public FindArticleResponse getArticle(@PathVariable Long articleId) {
        return articleService.getArticle(articleId);
    }

    @GetMapping
    public FindArticleListResponse getArticleList(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.getArticleList(pageable);
    }
}
