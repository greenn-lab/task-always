package study.spring.taskalways.apiserver.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import study.spring.taskalways.apiserver.user.domain.dto.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public AuthenticationFilter() {
    super(new AntPathRequestMatcher("/api/login", RequestMethod.POST.name()));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final ObjectMapper objectMapper = new ObjectMapper();
    final LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);

    if (!loginRequest.isValid()) {
      throw new InsufficientAuthenticationException("insufficient!");
    }

    final UsernamePasswordAuthenticationToken
      token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPin());

    return this.getAuthenticationManager().authenticate(token);
  }

}
