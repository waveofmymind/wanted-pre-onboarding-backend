# wanted-pre-onboarding-backend

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
- [ ] 게시글 작성,수정,삭제는 JWT 토큰이 필요하다.
- [X] 제목, 내용으로 게시글을 생성할 수 있으며, NotBlank이다.
- [ ] 유저는 게시글을 작성할 수 있다.
- [ ] 유저는 게시글을 수정할 수 있다.
- [ ] 게시글 수정은 작성자 본인만 가능하다.
- [ ] 유저는 게시글을 삭제할 수 있다.
- [ ] 게시글 삭제는 작성자 본인만 가능하다.
- [ ] 유저는 게시글을 조회할 수 있다.
- [ ] 유저는 게시글을 조회할 때 페이징이 가능하다.