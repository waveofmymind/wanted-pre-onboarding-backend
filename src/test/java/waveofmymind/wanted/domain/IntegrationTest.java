package waveofmymind.wanted.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import waveofmymind.wanted.domain.article.ArticleFixture;
import waveofmymind.wanted.domain.article.application.ArticleService;
import waveofmymind.wanted.domain.article.domain.Article;
import waveofmymind.wanted.domain.article.infrastructure.ArticleJpaRepository;
import waveofmymind.wanted.domain.user.UserFixture;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.infrastructure.UserJpaRepository;
import waveofmymind.wanted.global.jwt.JwtTokenProvider;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class IntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected UserJpaRepository userJpaRepository;

    @Autowired
    protected ArticleJpaRepository articleJpaRepository;

    @BeforeEach
    public void setUp() {
        User testUser = userJpaRepository.save(UserFixture.user());
        List<Article> articleList = ArticleFixture.articleList();
        articleJpaRepository.saveAll(articleList);
    }
}
