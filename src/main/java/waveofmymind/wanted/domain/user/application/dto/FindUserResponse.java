package waveofmymind.wanted.domain.user.application.dto;

import lombok.Builder;

public record FindUserResponse(
        String email
) {
    @Builder
    public FindUserResponse {
    }

    public static FindUserResponse of(String email) {
        return FindUserResponse.builder()
                .email(email)
                .build();
    }
}
