package waveofmymind.wanted.domain.user.application;

import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;

public interface UserService {
    void joinUser(JoinUserCommand command);
}
