package waveofmymind.wanted.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;
import waveofmymind.wanted.domain.user.infrastructure.LoginUserCommand;

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
    public void login(@Valid @RequestBody LoginUserRequest request) {
        LoginUserCommand command = request.toCommand();
        userService.loginUser(command);
    }
}
