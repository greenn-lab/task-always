package study.spring.taskalways.apiserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import study.spring.taskalways.apiserver.security.AuthenticationFilter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
  prePostEnabled = true,
  jsr250Enabled = true
)
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC_ACL = {
    "/api/sing-up",
    "/error",
    "/h2-console/**"
  };

  @Value("${spring.security.token-based-key}")
  private String tokenBasedKey;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(PUBLIC_ACL).permitAll()
      .anyRequest().authenticated()
      .and()

      .csrf().disable()
      .headers().frameOptions().disable()
      .and()

      .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
      .logout()
      .logoutUrl("/api/logout")
      .addLogoutHandler(rememberMeService())
      .logoutSuccessHandler(logoutSuccessHandler())
    ;
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    @SuppressWarnings("deprecation") final PasswordEncoder noOpPasswordEncoder = NoOpPasswordEncoder.getInstance();

    final String idForEncrypt = "bcrypt";

    final Map<String, PasswordEncoder> encoders = new HashMap<>(2);
    encoders.put(idForEncrypt, new BCryptPasswordEncoder());
    encoders.put("noop", noOpPasswordEncoder);

    return new DelegatingPasswordEncoder(idForEncrypt, encoders);
  }

  @Bean
  public AuthenticationFilter authenticationFilter() throws Exception {
    final AuthenticationFilter filter = new AuthenticationFilter();
    filter.setAuthenticationManager(authenticationManagerBean());
    filter.setAuthenticationSuccessHandler(successHandler());
    filter.setAuthenticationFailureHandler(failureHandler());
    filter.setRememberMeServices(rememberMeService());
    return filter;
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    return (request, response, authentication) -> {
      final PrintWriter writer = response.getWriter();
      writer.print("ok");
      writer.close();
    };
  }

  @Bean
  public AuthenticationFailureHandler failureHandler() {
    return new SimpleUrlAuthenticationFailureHandler();
  }

  @Bean
  public TokenBasedRememberMeServices rememberMeService() {
    final TokenBasedRememberMeServices service =
      new TokenBasedRememberMeServices(tokenBasedKey, userDetailsService());

    service.setCookieName("iam");
    return service;
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new HttpStatusReturningLogoutSuccessHandler();
  }
}

