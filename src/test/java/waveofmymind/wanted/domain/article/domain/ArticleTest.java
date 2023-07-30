package waveofmymind.wanted.domain.article.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import waveofmymind.wanted.domain.article.ArticleFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ArticleTest {

    @DisplayName("게시글이 생성된다.")
    @Test
    void articleTest() {
        // given
        Article article = ArticleFixture.article();
        // when & then
        assertThat(article.getTitle()).isEqualTo("title");
        assertThat(article.getContent()).isEqualTo("content");
    }

    @DisplayName("게시글이 수정된다.")
    @Test
    void editArticleTest() {
        // given
        Article article = ArticleFixture.article();
        // when
        article.edit("수정된 제목", "수정된 내용");
        // then
        assertThat(article.getTitle()).isEqualTo("수정된 제목");
        assertThat(article.getContent()).isEqualTo("수정된 내용");
    }

    @DisplayName("작성자를 검증한다.")
    @Test
    void validateTest() {
        // given
        Article article = ArticleFixture.article();
        // when & then
        assertThatNoException().isThrownBy(() -> article.validate(1L));
    }
}
