package waveofmymind.wanted.domain.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import waveofmymind.wanted.global.security.PasswordEncryptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ActiveProfiles("test")
public class UserTest {

    @DisplayName("유저가 생성된다.")
    @Test
    void createUserTest() {
        //given
        String email = "test@test.com";
        Password password = new Password("12345678");
        //when
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        //then
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @DisplayName("유저는 패스워드로 식별된다.")
    @Test
    void userIsIdentifiedByPassword() {
        //given
        String email = "test@test.com";
        Password password = new Password("12345678");
        //when
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        Password requestPassword = new Password("12345678");
        //then
        assertDoesNotThrow(() -> user.authenticate(requestPassword));
    }
}
