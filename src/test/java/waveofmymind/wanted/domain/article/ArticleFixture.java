package waveofmymind.wanted.domain.article;

import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;

public class ArticleFixture {

    public static RegisterArticleRequest registerArticleRequest() {
        return RegisterArticleRequest.builder()
                .title("title")
                .content("content")
                .build();
    }

    public static RegisterArticleCommand registerArticleCommand() {
        return RegisterArticleCommand.builder()
                .title("title")
                .content("content")
                .build();
    }

    public static Article article() {
        return Article.builder()
                .title("title")
                .content("content")
                .build();
    }
}
