package waveofmymind.wanted.domain.user.dto.request;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.application.dto.LoginUserCommand;

public record LoginUserRequest(
        String email,
        String password
) {
    @Builder
    public LoginUserRequest {
    }

    public LoginUserCommand toCommand() {
        return LoginUserCommand.builder()
                .email(email)
                .password(new Password(password))
                .build();
    }
}
