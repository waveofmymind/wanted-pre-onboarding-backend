package waveofmymind.wanted.global.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthConstants {
    HEADER_STRING("Authorization"),
    TOKEN_PREFIX("Bearer ");

    private final String value;
}