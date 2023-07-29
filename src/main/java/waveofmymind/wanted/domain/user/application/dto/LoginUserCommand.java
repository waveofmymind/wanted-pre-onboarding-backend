package waveofmymind.wanted.domain.user.application.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;

public record LoginUserCommand(
        String email,
        Password password
) {
    @Builder
    public LoginUserCommand {
    }
}
