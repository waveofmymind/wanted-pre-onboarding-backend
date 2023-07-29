package waveofmymind.wanted.domain.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordTest {

    @DisplayName("같은 패스워드는 같은 암호화 값을 생성한다")
    @Test
    void testEncryption() {
        // given
        Password password1 = new Password("12345678");
        Password password2 = new Password("12345678");

        // then
        assertEquals(password1.getValue(), password2.getValue());
    }

    @DisplayName("다른 패스워드는 다른 암호화 값을 생성한다")
    @Test
    void testDifferentPasswords() {
        // given
        Password password1 = new Password("12345678");
        Password password2 = new Password("87654321");

        // then
        assertNotEquals(password1.getValue(), password2.getValue());
    }
}
