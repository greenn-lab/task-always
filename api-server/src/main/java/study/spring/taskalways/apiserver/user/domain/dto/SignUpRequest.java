package study.spring.taskalways.apiserver.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import study.spring.taskalways.apiserver.user.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignUpRequest {

  @NotBlank
  @Length(min = 2, max = 10)
  private String nickname;

  @NotEmpty
  @Pattern(regexp = "\\w+@\\w+\\..+")
  private String email;

  @NotEmpty
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w])(?=\\S+$).{4,}$")
  private String pin;

  public User toUser() {
    return User.builder()
      .nickname(nickname)
      .email(email)
      .pin(pin)
      .build();
  }
}
