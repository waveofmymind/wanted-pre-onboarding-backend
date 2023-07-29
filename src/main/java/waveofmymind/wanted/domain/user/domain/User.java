package waveofmymind.wanted.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import waveofmymind.wanted.global.error.exception.UnIdentifiedUserException;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private Password password;

    public void authenticate(Password password) {
        if (!this.password.equals(password)) {
            throw new UnIdentifiedUserException();
        }
    }
}
