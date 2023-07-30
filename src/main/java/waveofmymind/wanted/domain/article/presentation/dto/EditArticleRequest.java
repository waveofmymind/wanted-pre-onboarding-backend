package waveofmymind.wanted.domain.article.presentation.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;

public record EditArticleRequest(
        String title,
        String content
) {
    @Builder
    public EditArticleRequest {
    }

    public EditArticleCommand toEditCommand(Long articleId, Long userId) {
        return new EditArticleCommand(articleId,userId, title, content);
    }
}
