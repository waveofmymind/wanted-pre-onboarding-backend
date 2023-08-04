package waveofmymind.wanted.domain.article.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeleteArticleResponse(
        @JsonProperty("article_id")
        Long articleId
) {
    public static DeleteArticleResponse of(Long articleId) {
        return new DeleteArticleResponse(articleId);
    }
}
