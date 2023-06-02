package com.nashtechglobal.service;





import com.nashtechglobal.model.EntityModel;

import java.util.List;

public interface H2DataService {
     /**
      * Returns all entity models.
      * @return a list of {@link EntityModel}
      */
     List<EntityModel> getAllEntity();

     /**
      * Returns the entity model with the given test ID.
      * @param testId the test ID of the entity to retrieve
      * @return the {@link EntityModel} with the given test ID
      */
     EntityModel getEntityByTestId(long testId);

     /**
      * Adds a new entity model to the database.
      * @param entityModel the {@link EntityModel} to add
      * @return true if the operation was successful, false otherwise
      */
     boolean addEntity(EntityModel entityModel);

     /**
      * Updates the given entity model in the database.
      * @param entityModel the {@link EntityModel} to update
      */
     void updateEntity(EntityModel entityModel);

     /**
      * Deletes the entity model with the given test ID from the database.
      * @param testId the test ID of the entity to delete
      */
     void deleteEntity(long testId);
}
