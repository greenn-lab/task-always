package study.spring.taskalways.apiserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.taskalways.apiserver.user.domain.User;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public Optional<User> findByNickname(String username) {
    return repository.findByNicknameLike(username);
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public void signUp(SignUpRequest dto) {
    repository.save(dto.toUser());
  }

}
