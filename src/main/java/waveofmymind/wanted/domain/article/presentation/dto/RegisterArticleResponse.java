package waveofmymind.wanted.domain.article.presentation.dto;

import lombok.Builder;

public record RegisterArticleResponse(
        Long articleId
) {
    @Builder
    public RegisterArticleResponse {
    }

    public static RegisterArticleResponse of(Long articleId) {
        return RegisterArticleResponse.builder()
                .articleId(articleId)
                .build();
    }
}
