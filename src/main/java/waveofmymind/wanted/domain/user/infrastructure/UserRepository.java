package waveofmymind.wanted.domain.user.infrastructure;

import waveofmymind.wanted.domain.user.domain.User;

public interface UserRepository {

    void registerUser(User user);

    boolean checkDuplicateEmail(String email);

    User getUserByEmail(String email);
}
