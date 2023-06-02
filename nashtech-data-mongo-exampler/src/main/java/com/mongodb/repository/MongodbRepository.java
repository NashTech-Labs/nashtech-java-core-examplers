package com.mongodb.repository;

import com.mongodb.entity.EntityModel;
import com.nashtechglobal.data.MongoDBRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongodbRepository extends
        MongoDBRepository<EntityModel, Integer> {
    /**
     * Retrieves a List of EntityModel objects from
     * the database by their user ID.
     * @param testUserId the user ID associated with the
     * EntityModel objects to retrieve
     * @return a List of EntityModel objects with the given user ID
     */
    @Query("{'testUserId' : ?0}")
    List<EntityModel> findEntityModelByUserId(String testUserId);

}
