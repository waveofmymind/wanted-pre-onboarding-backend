package waveofmymind.wanted.domain.user.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import waveofmymind.wanted.domain.ServiceTest;
import waveofmymind.wanted.domain.user.application.dto.FindUserResponse;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;
import waveofmymind.wanted.domain.user.presentation.dto.LoginUserRequest;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.UnIdentifiedUserException;
import waveofmymind.wanted.global.error.exception.UserNotFoundException;
import waveofmymind.wanted.global.jwt.LoginToken;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static waveofmymind.wanted.domain.user.UserFixture.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends ServiceTest {

    @Test
    @DisplayName("회원 가입이 성공한다.")
    void join() {
        //given
        JoinUserCommand command = joinUserCommand();
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        doAnswer((invocation) -> {
            User argument = invocation.getArgument(0);
            ReflectionTestUtils.setField(argument, "id", 1L);
            return argument;
        }).when(userRepository).registerUser(userCaptor.capture());

        //when
        userService.joinUser(command);

        //then
        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getId()).isEqualTo(1L);
    }

    @DisplayName("중복된 이메일로 가입하는 경우 예외가 발생한다.")
    @Test
    void invalidEmail() {
        //given
        JoinUserCommand command = joinUserCommand();
        given(userRepository.checkDuplicateEmail(command.email())).willReturn(true);
        //when
        assertThatThrownBy(() -> userService.joinUser(command))
                .isInstanceOf(DuplicateJoinException.class);
    }

    @DisplayName("올바른 이메일과 패스워드로 로그인이 성공한다.")
    @Test
    void login() {
        //given
        LoginUserRequest request = loginUserRequest();
        User user = user();
        given(userRepository.getUserByEmail(request.email())).willReturn(Optional.of(user));
        given(jwtTokenProvider.createLoginToken(any(User.class))).willReturn(loginToken());
        //when
        LoginToken response = userService.loginUser(request.toCommand());
        //then
        assertThat(response.accessToken()).startsWith("Bearer ");
    }

    @DisplayName("존재하지 않는 이메일로 로그인을 시도하는 경우 예외가 발생한다.")
    @Test
    void invalidEmailLogin() {
        //given
        LoginUserRequest request = loginUserRequest();
        given(userRepository.getUserByEmail(request.email())).willReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> userService.loginUser(request.toCommand()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("올바르지 않은 패스워드로 로그인을 시도하는 경우 예외가 발생한다.")
    @Test
    void invalidPasswordLogin() {
        //given
        LoginUserRequest request = loginUserRequest();
        User user = invalidUser();
        given(userRepository.getUserByEmail(request.email())).willReturn(Optional.of(user));
        //when
        assertThatThrownBy(() -> userService.loginUser(request.toCommand()))
                .isInstanceOf(UnIdentifiedUserException.class);
    }

    @DisplayName("id로 유저를 조회한다.")
    @Test
    void getUserById() {
        //given
        User user = user();
        given(userRepository.getUserById(user.getId())).willReturn(Optional.of(user));
        //when
        FindUserResponse response = userService.findUser(user.getId());
        //then
        assertThat(response.email()).isEqualTo(user.getEmail());
    }

    @DisplayName("존재하지 않는 id로 유저를 조회하는 경우 예외가 발생한다.")
    @Test
    void invalidId() {
        //given
        User user = user();
        given(userRepository.getUserById(user.getId())).willReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> userService.findUser(user.getId()))
                .isInstanceOf(UserNotFoundException.class);
    }

}
