package waveofmymind.wanted.domain.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;
import waveofmymind.wanted.domain.user.application.dto.LoginUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.UserNotFoundException;
import waveofmymind.wanted.global.jwt.JwtTokenProvider;
import waveofmymind.wanted.global.jwt.LoginToken;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void joinUser(JoinUserCommand command) {
        if (userRepository.checkDuplicateEmail(command.email())) {
            throw new DuplicateJoinException();
        }
        User user = command.toEntity();
        userRepository.registerUser(user);
    }

    @Override
    public LoginToken loginUser(LoginUserCommand command) {
        User user = userRepository.getUserByEmail(command.email()).orElseThrow(UserNotFoundException::new);
        user.authenticate(command.password());
        return jwtTokenProvider.createLoginToken(user);
    }
}
