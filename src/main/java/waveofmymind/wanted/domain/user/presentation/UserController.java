package waveofmymind.wanted.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.domain.user.infrastructure.JoinUserCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public void join(@RequestBody JoinUserRequest request) {
        JoinUserCommand command = request.toCommand();
        userService.joinUser(command);
    }
}
