package study.spring.taskalways.apiserver.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.repository.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {

  @Test
  @SuppressWarnings("deprecation")
  void shouldSignUpUser() {
    // GIVEN
    final UserRepository repository = mock(UserRepository.class);
    final PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
    final UserService service = new UserService(repository, passwordEncoder);

    // WHEN
    final SignUpRequest req = new SignUpRequest();
    req.setNickname("tester");
    req.setEmail("tester@test.com");
    req.setPin("Test123!");

    service.signUp(req);

    verify(repository).save(req.toUser());
  }

}
