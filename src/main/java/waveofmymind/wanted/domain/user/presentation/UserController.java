package waveofmymind.wanted.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.domain.user.application.dto.JoinUserCommand;
import waveofmymind.wanted.domain.user.application.dto.LoginUserCommand;
import waveofmymind.wanted.domain.user.dto.request.JoinUserRequest;
import waveofmymind.wanted.domain.user.dto.request.LoginUserRequest;
import waveofmymind.wanted.global.jwt.LoginToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void join(@Valid @RequestBody JoinUserRequest request) {
        JoinUserCommand command = request.toCommand();
        userService.joinUser(command);
    }

    @PostMapping("/login")
    public LoginToken login(@Valid @RequestBody LoginUserRequest request) {
        LoginUserCommand command = request.toCommand();
        return userService.loginUser(command);
    }
}
