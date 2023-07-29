package waveofmymind.wanted.domain.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.LoginUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void joinUser(JoinUserCommand command) {
        if (userRepository.checkDuplicateEmail(command.email())) {
            throw new DuplicateJoinException();
        }
        User user = command.toEntity();
        userRepository.registerUser(user);
    }

    @Override
    public void loginUser(LoginUserCommand command) {
        User user = userRepository.getUserByEmail(command.email());
        user.authenticate(command.password());
    }
}
