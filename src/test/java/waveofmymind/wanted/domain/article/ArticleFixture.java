package waveofmymind.wanted.domain.article;

import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.FindArticleResponse;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.presentation.dto.EditArticleRequest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;

import java.util.List;

public class ArticleFixture {

    public static RegisterArticleRequest registerArticleRequest() {
        return RegisterArticleRequest.builder()
                .title("title")
                .content("content")
                .build();
    }

    public static RegisterArticleCommand registerArticleCommand() {
        return RegisterArticleCommand.builder()
                .userId(1L)
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

    public static List<Article> articleList() {
        return List.of(
                Article.builder()
                        .id(1L)
                        .userId(1L)
                        .title("title1")
                        .content("content1")
                        .build(),
                Article.builder()
                        .id(2L)
                        .userId(1L)
                        .title("title2")
                        .content("content2")
                        .build(),
                Article.builder()
                        .id(3L)
                        .userId(1L)
                        .title("title3")
                        .content("content3")
                        .build()
        );
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

    public static List<RegisterArticleCommand> registerArticleCommandList() {
        return List.of(
                RegisterArticleCommand.builder()
                        .userId(2L)
                        .title("title1")
                        .content("content1")
                        .build(),
                RegisterArticleCommand.builder()
                        .userId(3L)
                        .title("title2")
                        .content("content2")
                        .build(),
                RegisterArticleCommand.builder()
                        .userId(4L)
                        .title("title3")
                        .content("content3")
                        .build()
        );
    }

    public static List<FindArticleResponse> findArticleResponseList() {
        return List.of(
                FindArticleResponse.builder()
                        .id(1L)
                        .userEmail("test@test.com")
                        .title("title1")
                        .content("content1")
                        .build(),
                FindArticleResponse.builder()
                        .id(2L)
                        .userEmail("test@test.com")
                        .title("title2")
                        .content("content2")
                        .build(),
                FindArticleResponse.builder()
                        .id(3L)
                        .userEmail("test@test.com")
                        .title("title3")
                        .content("content3")
                        .build()
        );
    }
}
