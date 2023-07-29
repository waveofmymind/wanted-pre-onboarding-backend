package waveofmymind.wanted.domain.user.application.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.domain.User;

public record LoginUserCommand(
        String email,
        Password password
) {
    @Builder
    public LoginUserCommand {
    }
}
