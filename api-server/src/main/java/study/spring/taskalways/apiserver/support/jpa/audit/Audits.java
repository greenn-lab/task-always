package study.spring.taskalways.apiserver.support.jpa.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Audits {

  @CreatedDate
  private LocalDateTime created;

  @CreatedBy
  private String creator;

  @LastModifiedDate
  private LocalDateTime updated;

  @LastModifiedBy
  private String updater;

}
