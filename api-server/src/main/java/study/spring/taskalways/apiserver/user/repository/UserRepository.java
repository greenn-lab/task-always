package study.spring.taskalways.apiserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.taskalways.apiserver.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
