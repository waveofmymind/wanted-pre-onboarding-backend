package waveofmymind.wanted.domain.article.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import waveofmymind.wanted.domain.ControllerTest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;
import waveofmymind.wanted.global.error.exception.ArticleNotFoundException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static waveofmymind.wanted.domain.article.ArticleFixture.*;

public class ArticleControllerTest extends ControllerTest {

    @DisplayName("유효하지 않은 토큰으로 게시글 등록시 예외가 발생한다.")
    @Test
    public void registerArticleExceptionTest() throws Exception {
        // given
        RegisterArticleRequest request = registerArticleRequest();
        mockMvc.perform(post("/articles")
                        .header("Authorization", "Bearer " + "invalidToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("게시글 조회시 성공한다,")
    @Test
    public void getArticleSuccessTest() throws Exception {
        // given
        long articleId = 1L;
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andDo(print());

        verify(articleService).getArticle(articleId);
    }

    @DisplayName("존재하지 않는 게시글 조회시 예외가 발생한다.")
    @Test
    public void getArticleExceptionTest() throws Exception {
        // given
        long articleId = 1L;
        doThrow(new ArticleNotFoundException()).when(articleService).getArticle(articleId);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/" + articleId))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(articleService).getArticle(articleId);
    }

    @DisplayName("게시글 목록 조회시 성공한다.")
    @Test
    public void getArticlesSuccessTest() throws Exception {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/articles")
                .param("page", String.valueOf(pageable.getPageNumber())))
                .andExpect(status().isOk())
                .andDo(print());

        verify(articleService).getArticleList(pageable);
    }
}
