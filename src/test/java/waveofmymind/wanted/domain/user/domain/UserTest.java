package waveofmymind.wanted.domain.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @DisplayName("유저가 생성된다.")
    @Test
    void createUserTest() {
        //given
        String email = "test@test.com";
        String password = "12345678";
        //when
        User user = User.builder()
                    .email(email)
                    .password(password)
                    .build();
        //then
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
