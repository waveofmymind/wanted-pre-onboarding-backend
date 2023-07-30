package waveofmymind.wanted.domain.article.application.dto;

public record EditArticleCommand(
        Long articleId,
        Long userId,
        String title,
        String content
) {
}
