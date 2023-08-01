package waveofmymind.wanted.domain.article.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record RegisterArticleResponse(
        @JsonProperty("article_id")
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
