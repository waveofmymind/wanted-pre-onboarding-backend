package waveofmymind.wanted.global.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record LoginToken(
        @JsonProperty("access_token")
        String accessToken
) {
    @Builder
    public LoginToken {
    }
}
