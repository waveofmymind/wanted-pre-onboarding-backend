package waveofmymind.wanted.domain.user.application;

import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.LoginUserCommand;

public interface UserService {
    void joinUser(JoinUserCommand command);

    void loginUser(LoginUserCommand command);
}
