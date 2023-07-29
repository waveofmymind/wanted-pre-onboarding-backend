package waveofmymind.wanted.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import waveofmymind.wanted.global.error.exception.TokenVerifyFailedException;

@Component
public class JwtVerifier {

    @Value("${jwt.secret-key}")
    private String SECRET;

    public DecodedJWT verify(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(SECRET))
                    .build().verify(token);
        } catch (Exception e) {
            throw new TokenVerifyFailedException();
        }
    }
}
