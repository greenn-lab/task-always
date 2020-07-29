package study.spring.taskalways.apiserver.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/sign-up")
@RequiredArgsConstructor
public class SignUpController {

  private final UserService service;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void signUp(@Valid @RequestBody SignUpRequest dto) {
    service.signUp(dto);
  }

}
