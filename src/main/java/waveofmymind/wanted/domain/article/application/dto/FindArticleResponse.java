package waveofmymind.wanted.domain.article.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import waveofmymind.wanted.domain.article.domain.Article;

import java.time.LocalDateTime;

public record FindArticleResponse(
        Long id,
        String title,
        String content,
        @JsonProperty("user_email")
        String userEmail,
        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
    @Builder
    public FindArticleResponse {
    }

    public static FindArticleResponse from(Article article, String email) {
        return FindArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .userEmail(email)
                .createdAt(article.getCreatedDate())
                .build();
    }
}
