package waveofmymind.wanted.domain.article.application.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.article.domain.Article;

public record RegisterArticleCommand(
        Long userId,
        String title,
        String content
) {
    @Builder
    public RegisterArticleCommand {
    }

    public Article toEntity() {
        return Article.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
