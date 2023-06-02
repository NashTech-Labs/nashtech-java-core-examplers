package com.nashtechglobal.repository;

import com.nashtechglobal.data.ReactiveMongoDBRepository;
import com.nashtechglobal.entity.ReactiveEntity;
import org.springframework.stereotype.Repository;


/**
 * A reactive repository interface for performing CRUD
 * operations on {@link ReactiveEntity} entities in a MongoDB database.
 * Extends {@link ReactiveMongoDBRepository} with the entity type
 * {@code ReactiveEntity} and identifier type {@code String}.
 */
@Repository
public interface ReactiveEntityRepository extends
    ReactiveMongoDBRepository<ReactiveEntity, String> {
}
