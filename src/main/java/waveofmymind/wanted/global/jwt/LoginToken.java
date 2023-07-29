package waveofmymind.wanted.global.jwt;

import lombok.Builder;

public record LoginToken(
        String accessToken
) {
    @Builder
    public LoginToken {
    }
}
