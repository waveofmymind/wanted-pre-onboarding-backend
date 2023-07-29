package waveofmymind.wanted.domain.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.global.error.exception.UserNotFoundException;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void registerUser(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
