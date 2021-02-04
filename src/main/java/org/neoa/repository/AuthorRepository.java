package org.neoa.repository;

import java.io.Serializable;
import java.util.Optional;

public interface AuthorRepository<T, ID extends Serializable> {
    Optional<T> find(Class<T> clazz, ID id);
    Optional<T> findViaSession(Class<T> clazz, ID id);
    void save(T t);
}
