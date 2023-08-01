package waveofmymind.wanted.domain.article.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import waveofmymind.wanted.domain.IntegrationTest;
import waveofmymind.wanted.domain.article.application.dto.FindArticleListResponse;
import waveofmymind.wanted.domain.article.application.dto.FindArticleResponse;
import waveofmymind.wanted.domain.article.presentation.dto.EditArticleRequest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleRequest;
import waveofmymind.wanted.domain.article.presentation.dto.RegisterArticleResponse;
import waveofmymind.wanted.domain.user.UserFixture;
import waveofmymind.wanted.domain.user.domain.User;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static waveofmymind.wanted.domain.article.ArticleFixture.*;
import static waveofmymind.wanted.domain.user.UserFixture.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ArticleIntegrationTest extends IntegrationTest {


    @DisplayName("게시글 등록 통합 테스트")
    @Test
    public void registerArticleTest() {
        String token = jwtTokenProvider.createLoginToken(UserFixture.user()).accessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<RegisterArticleRequest> request =
                new HttpEntity<>(registerArticleRequest(), headers);

        ResponseEntity<RegisterArticleResponse> response = restTemplate.postForEntity("/articles", request, RegisterArticleResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("게시글 수정 통합 테스트")
    @Test
    public void editArticleTest() {
        // given
        User user = user();
        Long articleId = 1L;
        String token = jwtTokenProvider.createLoginToken(user).accessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<EditArticleRequest> request =
                new HttpEntity<>(editArticleRequest(), headers);

        ResponseEntity<Long> response = restTemplate.exchange(
                "/articles/" + articleId,
                HttpMethod.PUT,
                request,
                Long.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(articleId);
    }

    @DisplayName("게시글 삭제 통합테스트")
    @Test
    public void deleteArticleTest() {
        // given
        User user = user();
        Long articleId = 1L;
        String token = jwtTokenProvider.createLoginToken(user).accessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<EditArticleRequest> request =
                new HttpEntity<>(editArticleRequest(), headers);

        ResponseEntity<Long> response = restTemplate.exchange(
                "/articles/" + articleId,
                HttpMethod.DELETE,
                request,
                Long.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(articleId);
    }

    @DisplayName("게시글 조회 통합 테스트")
    @Test
    public void getArticleTest() {
        // given
        Long articleId = 1L;
        ResponseEntity<FindArticleResponse> response = restTemplate.getForEntity(
                "/articles/" + articleId,
                FindArticleResponse.class
        );
        FindArticleResponse body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(body).id()).isEqualTo(articleId);
    }

    @DisplayName("게시글 목록 조회 통합 테스트")
    @Test
    public void getArticlesTest() {
        // given
        ResponseEntity<FindArticleListResponse> response = restTemplate.getForEntity(
                "/articles",
                FindArticleListResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).articles().size()).isEqualTo(4);
    }
}
