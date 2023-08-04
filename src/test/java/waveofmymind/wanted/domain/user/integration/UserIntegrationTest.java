package waveofmymind.wanted.domain.user.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import waveofmymind.wanted.domain.IntegrationTest;
import waveofmymind.wanted.domain.user.UserFixture;
import waveofmymind.wanted.domain.user.presentation.dto.JoinUserRequest;
import waveofmymind.wanted.domain.user.presentation.dto.LoginUserRequest;
import waveofmymind.wanted.global.jwt.LoginToken;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIntegrationTest extends IntegrationTest {

    @DisplayName("회원가입 통합 테스트")
    @Test
    public void registerUserTest() {
        // given
        JoinUserRequest request = JoinUserRequest.builder()
                .email("integration@test.com")
                .password("12345678").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JoinUserRequest> requestEntity = new HttpEntity<>(request, headers);
        // when
        ResponseEntity<Void> response = restTemplate.postForEntity("/users/join", requestEntity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("중복 이메일 회원가입 통합 테스트")
    @Test
    public void duplicateEmailTest() {
        // given
        JoinUserRequest request = UserFixture.joinUserRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JoinUserRequest> requestEntity = new HttpEntity<>(request, headers);
        // when
        ResponseEntity<Void> response = restTemplate.postForEntity("/users/join", requestEntity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("로그인 통합 테스트")
    @Test
    public void loginUserTest() {
        // given
        LoginUserRequest request = UserFixture.loginUserRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginUserRequest> requestEntity = new HttpEntity<>(request, headers);

        // when
        ResponseEntity<LoginToken> response = restTemplate.postForEntity("/users/login", requestEntity, LoginToken.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).accessToken()).isNotNull();
    }
}
