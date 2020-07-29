package study.spring.taskalways.apiserver.user.service;

import org.junit.jupiter.api.Test;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.repository.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {

  @Test
  void shouldSignUpUser() {
    // GIVEN
    final UserRepository repository = mock(UserRepository.class);
    final UserService service = new UserService(repository);

    // WHEN
    final SignUpRequest req = new SignUpRequest();
    req.setNickname("tester");
    req.setEmail("tester@test.com");
    req.setPin("Test123!");

    service.signUp(req);

    verify(repository).save(req.toUser());
  }

}
