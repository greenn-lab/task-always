package study.spring.taskalways.apiserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.spring.taskalways.apiserver.user.domain.User;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public Optional<User> findByNickname(String username) {
    return repository.findByNicknameLike(username);
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public void signUp(SignUpRequest dto) {
    dto.setPin(passwordEncoder.encode(dto.getPin()));
    repository.save(dto.toUser());
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    final Optional<User> user = findByEmail(username);
    return user
      .orElseThrow(() -> new UsernameNotFoundException("not exist username"));
  }

}
