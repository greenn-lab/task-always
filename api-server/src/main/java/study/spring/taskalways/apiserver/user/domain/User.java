package study.spring.taskalways.apiserver.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.spring.taskalways.apiserver.support.jpa.audit.Audits;
import study.spring.taskalways.apiserver.support.jpa.audit.AuditsListener;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@EntityListeners(AuditsListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User implements UserDetails, Serializable {

  private static final GrantedAuthority DEFAULT_USER = new SimpleGrantedAuthority("ROLE_USER");

  @Id
  @GeneratedValue
  private Long uid;

  private String nickname;
  private String email;
  private String pin;

  @Embedded
  private Audits audits;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(DEFAULT_USER);
  }

  @Override
  public String getPassword() {
    return pin;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
