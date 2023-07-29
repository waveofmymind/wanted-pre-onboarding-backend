package waveofmymind.wanted.domain.user.application.dto;

import lombok.Builder;
import waveofmymind.wanted.domain.user.domain.Password;
import waveofmymind.wanted.domain.user.domain.User;

public record JoinUserCommand(
        String email,
        Password password
) {
    @Builder
    public JoinUserCommand {
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
