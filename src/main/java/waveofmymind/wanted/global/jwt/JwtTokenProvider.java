package waveofmymind.wanted.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.global.auth.AuthConstants;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String SECRET;

    @Value("${jwt.access-time}")
    private Long ACCESS_EXPIRATION_TIME;

    public LoginToken createLoginToken(User user) {
        return LoginToken.builder()
                .accessToken(AuthConstants.TOKEN_PREFIX.getValue() + accessToken(user))
                .build();
    }

    private String accessToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
    }

}
