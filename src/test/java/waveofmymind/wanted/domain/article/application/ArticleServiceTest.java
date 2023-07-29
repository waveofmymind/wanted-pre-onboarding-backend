package waveofmymind.wanted.domain.article.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import waveofmymind.wanted.domain.article.application.dto.RegisterArticleCommand;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.infrastructure.ArticleRepository;
import static org.assertj.core.api.Assertions.assertThat;
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
    void saveArticle() {
        //given
        RegisterArticleCommand command = registerArticleCommand();
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        doAnswer((invocation) -> {
            Article argument = invocation.getArgument(0);
            ReflectionTestUtils.setField(argument, "id", 1L);
            return argument;
        }).when(articleRepository).saveArticle(articleCaptor.capture());

        //when
        articleService.saveArticle(command);

        //then
        Article savedArticle = articleCaptor.getValue();
        assertThat(savedArticle.getId()).isEqualTo(1L);
    }
}
