package waveofmymind.wanted.domain.article.presentation;

import lombok.Builder;
import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.user.domain.User;

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
