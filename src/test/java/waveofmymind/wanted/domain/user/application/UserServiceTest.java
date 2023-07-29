package waveofmymind.wanted.domain.user.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import waveofmymind.wanted.domain.user.domain.User;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static waveofmymind.wanted.domain.user.UserFixture.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("회원 가입")
    void join()  {
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
}
