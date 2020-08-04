package study.spring.taskalways.apiserver.user.domain.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class LoginRequest {

  private String email;
  private String pin;
  private boolean rememberMe;

  public boolean isValid() {
    return !StringUtils.isEmpty(email) && !StringUtils.isEmpty(pin);
  }

}
