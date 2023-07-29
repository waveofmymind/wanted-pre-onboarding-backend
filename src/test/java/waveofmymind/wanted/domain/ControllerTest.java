package waveofmymind.wanted.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import waveofmymind.wanted.domain.user.application.UserService;
import waveofmymind.wanted.domain.user.infrastructure.UserRepository;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllerTest {
    @MockBean
    protected UserService userService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
}
