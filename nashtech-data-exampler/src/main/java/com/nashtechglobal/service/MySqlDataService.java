package com.nashtechglobal.service;

import com.nashtechglobal.model.EntityModel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface MySqlDataService {
    /**
     * Saves the given EntityModel object to the MySQL database.
     *
     * @param entityModel the EntityModel object to be saved
     * @return the saved EntityModel object
     */
    EntityModel saveEntityModel(EntityModel entityModel);

    /**
     * Updates the given EntityModel object in the MySQL database.
     *
     * @param entityModel the EntityModel object to be updated
     * @return the updated EntityModel object
     * @throws EntityNotFoundException if the EntityModel object
     * to be updated is not found in the database
     */
    EntityModel updateEntityModel(EntityModel entityModel);

    /**
     * Returns a List of all EntityModel objects stored in the MySQL database.
     *
     * @return a List of all EntityModel objects stored in the MySQL database
     */
    List<EntityModel> getAllEntityModels();

    /**
     * Returns the EntityModel object with the specified
     * ID from the MySQL database.
     *
     * @param id the ID of the EntityModel object to retrieve
     * @return the EntityModel object with the specified ID
     * @throws EntityNotFoundException if the EntityModel object
     * with the specified ID is not found in the database
     */
    EntityModel getEntityModelById(Long id) throws EntityNotFoundException;

    /**
     * Deletes the EntityModel object with the specified ID
     * from the MySQL database.
     *
     * @param id the ID of the EntityModel object to delete
     */
    void deleteEntityModel(Long id);


}
