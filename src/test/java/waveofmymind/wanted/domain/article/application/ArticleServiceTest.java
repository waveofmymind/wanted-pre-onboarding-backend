package waveofmymind.wanted.domain.article.application;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import waveofmymind.wanted.domain.article.application.dto.EditArticleCommand;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.infrastructure.ArticleRepository;
import waveofmymind.wanted.global.error.exception.ArticleNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static waveofmymind.wanted.domain.article.ArticleFixture.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @DisplayName("제목과 내용으로 게시글이 저장된다.")
    @Test
    void registerArticle() {
        //given
        RegisterArticleCommand command = registerArticleCommand();
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        doAnswer((invocation) -> {
            Article argument = invocation.getArgument(0);
            ReflectionTestUtils.setField(argument, "id", 1L);
            return argument;
        }).when(articleRepository).saveArticle(articleCaptor.capture());

        //when
        articleService.registerArticle(command);

        //then
        Article savedArticle = articleCaptor.getValue();
        assertThat(savedArticle.getId()).isEqualTo(1L);
    }

    @DisplayName("제목이 Blank일 경우 예외가 발생한다.")
    @Test
    void registerArticleWithBlankTitle() {
        //given
        RegisterArticleCommand command = RegisterArticleCommand.builder()
                .title("")
                .content("content").build();
        given(articleRepository.saveArticle(any(Article.class))).willThrow(ConstraintViolationException.class);
        //when & then
        assertThatThrownBy(() -> articleService.registerArticle(command))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("내용이 Blank일 경우 예외가 발생한다.")
    @Test
    void registerArticleWithBlankContent() {
        //given
        RegisterArticleCommand command = RegisterArticleCommand.builder()
                .title("title")
                .content("").build();
        given(articleRepository.saveArticle(any(Article.class))).willThrow(ConstraintViolationException.class);
        //when & then
        assertThatThrownBy(() -> articleService.registerArticle(command))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("수정할 제목과 내용으로 게시글을 수정한다.")
    @Test
    void test() {
        // given
        EditArticleCommand command = editArticleCommand();
        // when
        given(articleRepository.findArticleById(any(Long.class))).willReturn(Optional.of(article()));
        // then
        Long editedArticle = articleService.editArticle(command);
        assertThat(editedArticle).isEqualTo(1L);
    }

    @DisplayName("수정할 게시글이 없을 경우 예외가 발생한다.")
    @Test
    void test2() {
        // given
        EditArticleCommand command = editArticleCommand();
        // when
        given(articleRepository.findArticleById(any(Long.class))).willReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> articleService.editArticle(command))
                .isInstanceOf(ArticleNotFoundException.class);
    }
}
