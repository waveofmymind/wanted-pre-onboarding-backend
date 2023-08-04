package waveofmymind.wanted.domain.article.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EditArticleResponse(
        @JsonProperty("article_id")
        Long articleId
) {
    public static EditArticleResponse of(Long articleId) {
        return new EditArticleResponse(articleId);
    }
}
