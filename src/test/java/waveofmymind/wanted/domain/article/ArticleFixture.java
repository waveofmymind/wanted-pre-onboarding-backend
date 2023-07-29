package waveofmymind.wanted.domain.article;

import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.presentation.EditArticleRequest;
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
                .id(1L)
                .userId(1L)
                .title("title")
                .content("content")
                .build();
    }

    public static EditArticleRequest editArticleRequest() {
        return EditArticleRequest.builder()
                .title("fix title")
                .content("fix content")
                .build();
    }

    public static EditArticleCommand editArticleCommand() {
        return editArticleRequest().toEditCommand(1L, 1L);
    }
}
