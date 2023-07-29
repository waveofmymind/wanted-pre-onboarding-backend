package waveofmymind.wanted.domain.user.presentation;


import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import waveofmymind.wanted.domain.ControllerTest;
import waveofmymind.wanted.domain.user.UserFixture;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.InvalidPasswordException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTest {

    @DisplayName("이메일과 패스워드로 회원가입이 성공한다.")
    @Test
    void joinSuccessTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).joinUser(request.toCommand());
    }

    @DisplayName("회원가입시 패스워드 길이가 8자리 미만일 경우 예외가 발생한다.")
    @Test
    void invalidPasswordTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        given(userService.joinUser(request.toCommand())).willThrow(new InvalidPasswordException());
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).joinUser(request.toCommand());
    }

    @DisplayName("회원가입시 이메일 형식이 맞지 않을 경우 예외가 발생한다.")
    @Test
    void invalidEmailTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        given(userService.joinUser(request.toCommand())).willThrow(new ConstraintViolationException(null));
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).joinUser(request.toCommand());
    }

    @DisplayName("이미 가입된 이메일인 경우 예외가 발생한다.")
    @Test
    void duplicateEmailTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        given(userService.joinUser(request.toCommand())).willThrow(new DuplicateJoinException());
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).joinUser(request.toCommand());
    }

}
