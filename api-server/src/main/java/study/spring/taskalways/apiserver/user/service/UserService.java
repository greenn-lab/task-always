package study.spring.taskalways.apiserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public void signUp(SignUpRequest dto) {
    repository.save(dto.toUser());
  }

}
