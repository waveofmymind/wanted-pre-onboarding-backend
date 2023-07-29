package waveofmymind.wanted.domain.user.application;

import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;
import waveofmymind.wanted.domain.user.application.dto.LoginUserCommand;
import waveofmymind.wanted.global.jwt.LoginToken;

public interface UserService {
    void joinUser(JoinUserCommand command);

    LoginToken loginUser(LoginUserCommand command);
}
