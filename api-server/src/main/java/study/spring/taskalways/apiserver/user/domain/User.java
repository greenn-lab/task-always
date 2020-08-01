package study.spring.taskalways.apiserver.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.spring.taskalways.apiserver.support.jpa.audit.Audits;
import study.spring.taskalways.apiserver.support.jpa.audit.AuditsListener;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User {

  @Id
  @GeneratedValue
  private Long uid;

  private String nickname;
  private String email;
  private String pin;

}
