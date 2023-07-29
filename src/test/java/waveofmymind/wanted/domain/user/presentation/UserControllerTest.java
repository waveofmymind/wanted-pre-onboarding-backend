package waveofmymind.wanted.domain.user.presentation;


import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import waveofmymind.wanted.domain.ControllerTest;
import waveofmymind.wanted.domain.user.UserFixture;
import waveofmymind.wanted.domain.user.dto.request.JoinUserRequest;
import waveofmymind.wanted.domain.user.dto.request.LoginUserRequest;
import waveofmymind.wanted.global.error.exception.DuplicateJoinException;
import waveofmymind.wanted.global.error.exception.InvalidPasswordException;
import waveofmymind.wanted.global.error.exception.UserNotFoundException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTest {

    @DisplayName("회원가입 성공 API 테스트")
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

    @DisplayName("회원가입시 패스워드 형식 예외 테스트")
    @Test
    void invalidPasswordTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        doThrow(new InvalidPasswordException()).when(userService).joinUser(request.toCommand());
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).joinUser(request.toCommand());
    }

    @DisplayName("회원가입 중복 이메일 예외 테스트")
    @Test
    void duplicateEmailTest() throws Exception {
        //given
        JoinUserRequest request = UserFixture.joinUserRequest();
        doThrow(new DuplicateJoinException()).when(userService).joinUser(request.toCommand());
        //when
        mockMvc.perform(post("/users/join")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).joinUser(request.toCommand());
    }

    @DisplayName("로그인 성공 API 테스트")
    @Test
    void loginSuccessTest() throws Exception {
        //given
        LoginUserRequest request = UserFixture.loginUserRequest();
        //when
        mockMvc.perform(post("/users/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).loginUser(request.toCommand());
    }

    @DisplayName("로그인시 미가입 이메일 예외 테스트")
    @Test
    void notJoinedEmailTest() throws Exception {
        //given
        LoginUserRequest request = UserFixture.loginUserRequest();
        doThrow(new UserNotFoundException()).when(userService).loginUser(request.toCommand());
        //when
        mockMvc.perform(post("/users/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).loginUser(request.toCommand());
    }

    @DisplayName("로그인시 패스워드 불일치 예외 테스트")
    @Test
    void invalidPasswordLoginTest() throws Exception {
        //given
        LoginUserRequest request = UserFixture.loginUserRequest();
        doThrow(new InvalidPasswordException()).when(userService).loginUser(request.toCommand());
        //when
        mockMvc.perform(post("/users/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).loginUser(request.toCommand());
    }

}
