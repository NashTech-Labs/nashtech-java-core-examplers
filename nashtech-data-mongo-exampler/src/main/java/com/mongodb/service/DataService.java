package com.mongodb.service;

import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;

import java.util.List;

/**
 * An interface that defines data service operations
 * for managing EntityModel objects.
 */
public interface DataService {
    /**
     * Inserts or updates an EntityModel object in the database.
     * @param entityRequest the EntityModel object to be inserted or updated
     * @return the updated EntityModel object
     */
    EntityResponse upsertEntityModel(EntityRequest entityRequest);
    /**
     * Retrieves all EntityModel objects from the database.
     * @return a List of all EntityModel objects
     */
    List<EntityResponse> getAllEntityModels();
    /**
     * Retrieves an EntityModel object from the database by its test ID.
     * @param testId the ID of the test associated with the EntityModel object
     * @return the EntityModel object with the given test ID,
     * or null if not found
     */
    EntityResponse getEntityModelByTestId(int testId);
    /**
     * Retrieves a List of EntityModel objects from the
     * database by their user test ID.
     * @param testUserId the user test ID associated with the
     * EntityModel objects to retrieve
     * @return a List of EntityModel objects with the given user test ID
     */
    List<EntityResponse> getEntityModelByUserTestId(String testUserId);
    /**
     * Deletes an EntityModel object from the database by its test ID.
     * @param testId the ID of the test associated
     * with the EntityModel object to be deleted
     */
    void deleteEntityModel(int testId);


}
