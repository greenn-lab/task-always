package study.spring.taskalways.apiserver;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  jsr250Enabled = true
)
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC_ACL = {
    "/error",
    "/login",
    "/logout",
    "/api/sing-up",
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(PUBLIC_ACL).permitAll()
      .anyRequest().authenticated()
      .and()

      .csrf().disable()

      .formLogin()
      .loginPage("/login")
      .and()

      .logout()
      .logoutUrl("/logout")
      .logoutSuccessUrl("/login#logged-out")
    ;
  }
}
