package org.neoa.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class DaoImpl<T, ID extends Serializable> implements Dao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save (T t) {
        entityManager.persist(t);
    }

    @Override
    public Optional<T> find(Class<T> clazz, ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public Optional<T> findViaSession(Class<T> clazz, ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Session session = entityManager.unwrap(Session.class);

        return Optional.ofNullable(session.get(clazz, id));
    }

}
