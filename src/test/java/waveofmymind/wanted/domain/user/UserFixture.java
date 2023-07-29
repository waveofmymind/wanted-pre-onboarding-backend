package waveofmymind.wanted.domain.user;

import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;
import waveofmymind.wanted.domain.user.presentation.JoinUserRequest;

public class UserFixture {

    public static User user() {
        return User.builder()
                .email("test@test.com")
                .password(password())
                .build();
    }

    public static JoinUserCommand joinUserCommand() {
        return JoinUserCommand.builder()
                .email("test@test.com")
                .password(password())
                .build();
    }

    private static Password password() {
        return new Password("12345678");
    }


    public static JoinUserRequest joinUserRequest() {
        return JoinUserRequest.builder()
                .email("test@test.com")
                .password("12345678")
                .build();
    }
}
