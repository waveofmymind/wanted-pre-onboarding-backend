package waveofmymind.wanted.domain.user;

import waveofmymind.wanted.domain.user.application.dto.LoginUserCommand;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;
import waveofmymind.wanted.domain.user.dto.request.JoinUserRequest;
import waveofmymind.wanted.domain.user.dto.request.LoginUserRequest;
import waveofmymind.wanted.global.jwt.LoginToken;

public class UserFixture {

    public static User user() {
        return User.builder()
                .id(1L)
                .email("test@test.com")
                .password(password())
                .build();
    }

    public static User invalidUser() {
        return User.builder()
                .id(1L)
                .email("test@test.com")
                .password(new Password("invalidPassword"))
                .build();
    }

    public static JoinUserRequest joinUserRequest() {
        return JoinUserRequest.builder()
                .email("test@test.com")
                .password("12345678")
                .build();
    }

    public static JoinUserCommand joinUserCommand() {
        return joinUserRequest().toCommand();
    }

    public static LoginUserRequest loginUserRequest() {
        return LoginUserRequest.builder()
                .email("test@test.com")
                .password("12345678")
                .build();
    }

    public static LoginUserCommand loginUserCommand() {
        return loginUserRequest().toCommand();
    }

    public static LoginToken loginToken() {
        return LoginToken.builder()
                .accessToken("Bearer accessToken")
                .build();
    }
    private static Password password() {
        return new Password("12345678");
    }

}
