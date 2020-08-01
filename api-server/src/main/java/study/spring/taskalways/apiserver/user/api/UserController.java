package study.spring.taskalways.apiserver.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.spring.taskalways.apiserver.user.domain.User;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping
  public ResponseEntity<Object> find(
    @RequestParam(required = false) String nickname,
    @RequestParam(required = false) String email
  ) {
    if (!StringUtils.isEmpty(nickname)) {
      return responseOkOrNoContent(service.findByNickname(nickname));
    }
    else if (!StringUtils.isEmpty(email)) {
      return responseOkOrNoContent(service.findByEmail(email));
    }

    return ResponseEntity.badRequest().body("either nickname or email is required.");
  }

  private ResponseEntity<Object> responseOkOrNoContent(final Optional<User> user) {
    if (user.isPresent())
      return ResponseEntity.ok(user);
    else
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public void signUp(@Valid @RequestBody SignUpRequest dto) {
    service.signUp(dto);
  }

}
