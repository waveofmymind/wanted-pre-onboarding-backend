package waveofmymind.wanted.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import waveofmymind.wanted.domain.user.domain.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
