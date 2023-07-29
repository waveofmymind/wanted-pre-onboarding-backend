package waveofmymind.wanted.domain.user.infrastructure;

import waveofmymind.wanted.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void registerUser(User user);

    boolean checkDuplicateEmail(String email);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(Long userId);
}
