package waveofmymind.wanted.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import waveofmymind.wanted.global.domain.BaseTimeEntity;
import waveofmymind.wanted.global.error.exception.UnIdentifiedUserException;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private Password password;

    public void authenticate(Password password) {
        if (!this.password.equals(password)) {
            throw new UnIdentifiedUserException();
        }
    }
}
