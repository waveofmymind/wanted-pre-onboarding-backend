package waveofmymind.wanted.domain.article.application.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.article.domain.Article;

public record RegisterArticleCommand(
        String title,
        String content
) {
    @Builder
    public RegisterArticleCommand {
    }

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
