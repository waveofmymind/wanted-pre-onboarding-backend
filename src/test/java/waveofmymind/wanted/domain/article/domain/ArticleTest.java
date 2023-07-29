package waveofmymind.wanted.domain.article.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest {

    @DisplayName("게시글이 생성된다.")
    @Test
    void articleTest() {
        // given
        Article article = Article.builder()
                .title("제목")
                .content("내용").build();
        // when & then
        assertThat(article.getTitle()).isEqualTo("제목");
        assertThat(article.getContent()).isEqualTo("내용");
    }
}
