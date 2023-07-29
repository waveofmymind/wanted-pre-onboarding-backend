package waveofmymind.wanted.domain.user.dto.request;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;

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
