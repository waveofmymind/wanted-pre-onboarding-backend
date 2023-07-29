package waveofmymind.wanted.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import waveofmymind.wanted.domain.article.application.ArticleServiceImpl;
import waveofmymind.wanted.domain.article.infrastructure.ArticleRepository;
import waveofmymind.wanted.domain.user.application.UserServiceImpl;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;
import waveofmymind.wanted.global.jwt.JwtTokenProvider;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {

    @Mock
    protected ArticleRepository articleRepository;

    @Mock
    protected UserRepository userRepository;

    @Mock
    protected JwtTokenProvider jwtTokenProvider;
    protected UserServiceImpl userService;
    protected ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, jwtTokenProvider);
        articleService = new ArticleServiceImpl(articleRepository, userService);
    }
}
