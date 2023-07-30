package waveofmymind.wanted.global.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;
import waveofmymind.wanted.global.error.exception.TokenVerifyFailedException;
import waveofmymind.wanted.global.jwt.JwtVerifier;

@Aspect
@RequiredArgsConstructor
@Component
public class AuthCheckAspect {

    private final JwtVerifier jwtVerifier;
    private final UserRepository userRepository;

    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(waveofmymind.wanted.global.auth.AuthCheck)")
    public Object authCheck(ProceedingJoinPoint pjp) throws Throwable {
        String authorizationHeader = httpServletRequest.getHeader(AuthConstants.HEADER_STRING.getValue());
        if (authorizationHeader == null || !authorizationHeader.startsWith(AuthConstants.TOKEN_PREFIX.getValue())) {
            throw new TokenVerifyFailedException();
        }
        String token = authorizationHeader.replace(AuthConstants.TOKEN_PREFIX.getValue(), "");
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String email = decodedJWT.getSubject();
        User user = userRepository.getUserByEmail(email).orElseThrow(TokenVerifyFailedException::new);
        UserContext.currentUser.set(user);
        return pjp.proceed();
    }
}
