package waveofmymind.wanted.domain.user.presentation;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;

public record JoinUserRequest(
        String email,
        String password
) {
    @Builder
    public JoinUserRequest {
    }

    public JoinUserCommand toCommand() {
        return new JoinUserCommand(email, new Password(password));
    }
}
