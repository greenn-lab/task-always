package study.spring.taskalways.apiserver.support.jpa.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AuditsListener {

  @PrePersist
  public void willInsert(Object entity) throws IllegalAccessException, InvocationTargetException {
    final Audits audits = new Audits();
    audits.setCreated(LocalDateTime.now());
    audits.setUpdated(LocalDateTime.now());

    findAndConsumeAudits(entity, audits);
  }

  @PreUpdate
  public void willUpdate(Object entity) throws InvocationTargetException, IllegalAccessException {
    final Audits audits = new Audits();
    audits.setUpdated(LocalDateTime.now());

    findAndConsumeAudits(entity, audits);
  }

  private void findAndConsumeAudits(Object entity, Audits audits) throws IllegalAccessException, InvocationTargetException {
    for (Method method : entity.getClass().getDeclaredMethods()) {
      final Class<?>[] parameters = method.getParameterTypes();
      if (parameters.length > 0 && Audits.class == parameters[0]) {
        method.invoke(entity, audits);
      }
    }
  }
}
