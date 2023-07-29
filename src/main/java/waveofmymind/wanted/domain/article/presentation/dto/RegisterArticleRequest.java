package waveofmymind.wanted.domain.article.presentation.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;

public record RegisterArticleRequest(
        String title,
        String content
) {
    @Builder
    public RegisterArticleRequest {
    }

    public RegisterArticleCommand toCommand(Long userId) {
        return RegisterArticleCommand.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
