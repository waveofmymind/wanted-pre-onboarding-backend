# wanted-pre-onboarding-backend

지원자: 전상준

### 배포 링크

http://49.50.173.88:8080

### 데모 영상

[https://youtu.be/I9UKuR4JRXs](https://youtu.be/I9UKuR4JRXs)

### API 명세서

- 포스트맨: https://documenter.getpostman.com/view/2s9XxtzG1X?version=latest

### USER

- [[POST] 회원 가입](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BPOST%5D-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)
- [[POST] 로그인](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BPOST%5D-%EB%A1%9C%EA%B7%B8%EC%9D%B8)

### ARTICLE

- [[POST] 게시글 작성](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BPOST%5D-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%9E%91%EC%84%B1)
- [[GET] 게시글 상세 조회](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BGET%5D-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%83%81%EC%84%B8-%EC%A1%B0%ED%9A%8C)
- [[PUT] 게시글 수정](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BPUT%5D-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%88%98%EC%A0%95)
- [[DELETE] 게시글 삭제](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BDELETE%5D-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%82%AD%EC%A0%9C)
- [[GET] 게시글 목록 조회](https://github.com/waveofmymind/wanted-pre-onboarding-backend/wiki/%5BGET%5D-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EB%AA%A9%EB%A1%9D-%EC%A1%B0%ED%9A%8C)

## 구현 특징

### AOP를 활용한 권한 인증 처리

단순 JWT 토큰의 권한 인증 방법을 위해 스프링 시큐리티를 사용하는 것은 과하다고 생각했습니다.

그래서 AOP를 활용하여 Aspect로 토큰 인증에 대한 관심사를 분리하여 권한 인증이 필요한 메서드에 어노테이션 형식으로 사용할 수 있도록 했습니다.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
}

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
            throw new UnAuthorizedException();
        }
        String token = authorizationHeader.replace(AuthConstants.TOKEN_PREFIX.getValue(), "");
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String email = decodedJWT.getSubject();
        User user = userRepository.getUserByEmail(email).orElseThrow(UnAuthorizedException::new);
        UserContext.currentUser.set(user);
        return pjp.proceed();
    }
}

//실제 사용 사례

@AuthCheck
@PostMapping
public RegisterArticleResponse register(@RequestBody RegisterArticleRequest request) {
    User user = UserContext.currentUser.get();
    return RegisterArticleResponse.of(articleService.registerArticle(request.toCommand(user.getId())));
}
```

### 에러 응답 관리

커스텀 예외처리를 진행하였지만, HttpStatus와 Message에 대한 관리를 한 곳에서 할 수 있도록 하는 것이 유지보수, 기능 확장에 대해 효율적일 것이라고 생각했습니다.

그래서 `ErrorCode`라는 열거형 클래스로 관리할 수 있도록 했습니다.

```java
@AllArgsConstructor
@Getter
public enum ErrorCode {

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "사용자 정보가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    DUPLICATE_JOIN(HttpStatus.BAD_REQUEST, "이미 가입된 사용자입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "올바른 형식이 아닙니다."),
    INVALID_PASSWORD_PARAMETER(HttpStatus.BAD_REQUEST, "올바른 비밀번호 형식이 아닙니다."),
    TOKEN_VERIFY_FAILED(HttpStatus.BAD_REQUEST, "토큰 검증에 실패했습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

    private final HttpStatus status;

    private final String message;
}

//사용 사례

// 커스텀 예외에서 필드 추가
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

public class ArticleNotFoundException extends BusinessException {
    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}

// 핸들러에서 해당 에러 코드를 통한 응답 처리

@ExceptionHandler(ArticleNotFoundException.class)
protected ResponseEntity<ErrorResponse> articleNotFoundException(ArticleNotFoundException e) {
    return createErrorResponse(e.getErrorCode());
}
```

### 패스워드 암호화

패스워드를 별도의 클래스로 두어 패스워드 클래스에서 검증 및 자체 암호화, 직렬화 및 역직렬화를 할 수 있도록 했습니다.
```java
@Getter
@Embeddable
@JsonSerialize(using = Password.PasswordSerializer.class)
@JsonDeserialize(using = Password.PasswordDeserializer.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password")
    private String value;

    public Password(String value) {
        validate(value);
        this.value = PasswordEncryptor.sha256Encrypt(value);
    }

    public void validate(String value) {
        if (value.length() < 8) {
            throw new InvalidPasswordException();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static class PasswordSerializer extends JsonSerializer<Password> {
        @Override
        public void serialize(Password password, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(password.getValue());
        }
    }

    public static class PasswordDeserializer extends JsonDeserializer<Password> {

        @Override
        public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new Password(p.getText());
        }
    }
}
```

## 실행 방법


```bash
// 도커 컴포즈 파일 위치에서
docker-compose up -d
```

## ERD

![스크린샷 2023-08-04 오전 11 39 12](https://github.com/waveofmymind/wanted-pre-onboarding-backend/assets/93868431/b96eb040-8f0b-4ac8-932b-3bfd19b9a749)

## 시스템 아키텍처

![스크린샷 2023-07-31 오후 10 57 40](https://github.com/waveofmymind/wanted-pre-onboarding-backend/assets/93868431/766cf1bc-c4ad-437c-a62d-1f5501349867)

## 기능 구현 목록

- [X] 이메일, 패스워드로 유저를 생성한다.
- [X] 유저는 회원가입을 할 수 있다.
- [X] 유저는 회원가입시 이메일, 패스워드를 입력해야한다.
- [X] 이메일은 @가 포함되어 있는 이메일 형식이여야한다.
- [X] 이메일은 중복될 수 없다.
- [X] 패스워드는 암호화되어 저장된다.
- [X] 패스워드는 8자 이상이여야한다.
- [X] 유저는 로그인을 할 수 있다.
- [X] 유저는 로그인시 이메일, 패스워드를 입력해야한다.
- [X] 올바른 이메일, 패스워드인 경우 로그인에 성공한다.
- [X] 로그인에 성공한 유저는 JWT 토큰을 발급받는다.
- [X] 올바르지 않은 이메일, 패스워드인 경우 로그인에 실패한다.
- [X] 게시글 작성,수정,삭제는 JWT 토큰이 필요하다.
- [X] 제목, 내용으로 게시글을 생성할 수 있으며, NotBlank이다.
- [X] 유저는 게시글을 작성할 수 있다.
- [X] 유저는 게시글을 수정할 수 있다.
- [X] 게시글 수정은 작성자 본인만 가능하다.
- [X] 유저는 게시글을 삭제할 수 있다.
- [X] 게시글 삭제는 작성자 본인만 가능하다.
- [X] 게시글을 조회할 수 있다.
- [X] 유저는 게시글 목록을 조회할 수 있다.
- [X] 게시글 목록 조회시 페이지네이션을 지원한다.

### 테스트

![스크린샷 2023-07-31 오후 10 59 56](https://github.com/waveofmymind/wanted-pre-onboarding-backend/assets/93868431/5c3b2eaa-a8d9-4e89-aabe-5cadb46d2979)

![스크린샷 2023-07-31 오후 11 00 16](https://github.com/waveofmymind/wanted-pre-onboarding-backend/assets/93868431/83039946-39c7-4110-b841-6f2205d4e12c)

