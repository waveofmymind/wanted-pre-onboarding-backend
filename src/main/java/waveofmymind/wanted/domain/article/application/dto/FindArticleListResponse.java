package waveofmymind.wanted.domain.article.application.dto;

import lombok.Builder;

import java.util.List;

public record FindArticleListResponse(
        List<FindArticleResponse> articles
) {
    @Builder
    public FindArticleListResponse {
    }

    public static FindArticleListResponse of(List<FindArticleResponse> articles) {
        return new FindArticleListResponse(articles);
    }

    public int size() {
        return articles.size();
    }
}
