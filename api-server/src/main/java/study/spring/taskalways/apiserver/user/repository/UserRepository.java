package study.spring.taskalways.apiserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.taskalways.apiserver.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByNicknameLike(String nickname);

  Optional<User> findByEmail(String email);
}
