package com.spa.ecommerce.common;

import java.util.Collection;
import java.util.Optional;

public interface GeneralService<T, ID> {
    Collection<T> getAll();

    Optional<T> get(ID id);

    Optional<T> save(T entity);

    Optional<T> update(ID id, T entity);

    Optional<T> delete(ID id, T entity);
}
