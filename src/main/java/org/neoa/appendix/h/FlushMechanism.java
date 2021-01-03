package org.neoa.appendix.h;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class FlushMechanism {
    private EntityManager entityManager;

    public FlushMechanism(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void flushModes() {
        System.out.println(
                "Flush mode, Hibernate JPA (EntityManager#getFlushMode()): "
                        + entityManager.getFlushMode());
        System.out.println(
                "Flush mode, Hibernate JPA (Session#getFlushMode()): "
                        + (entityManager.unwrap(Session.class)).getFlushMode());
        System.out.println(
                "Flush mode, Hibernate Session (Session#getHibernateFlushMode()): "
                        + (entityManager.unwrap(Session.class)).getHibernateFlushMode());
    }

}
