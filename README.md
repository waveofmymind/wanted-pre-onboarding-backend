# wanted-pre-onboarding-backend

지원자: 전상준

### 배포 링크

http://15.165.84.161:8080/

### API 명세서

- 포스트맨: https://documenter.getpostman.com/view/2s9XxtzG1X?version=latest

### 실행 방법


```bash
// 도커 컴포즈 파일 위치에서
docker-compose up -d
```

### 시스템 아키텍처

![스크린샷 2023-07-31 오후 10 57 40](https://github.com/waveofmymind/wanted-pre-onboarding-backend/assets/93868431/766cf1bc-c4ad-437c-a62d-1f5501349867)

### 기능 구현 목록

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

