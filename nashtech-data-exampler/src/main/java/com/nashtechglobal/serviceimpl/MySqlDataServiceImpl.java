package com.nashtechglobal.serviceimpl;

import com.nashtechglobal.exception.ResourceNotFoundException;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.repository.MySqlDBRepository;
import com.nashtechglobal.service.MySqlDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MySqlDataServiceImpl implements MySqlDataService {
    /**
     *
     */

    @Autowired
    private MySqlDBRepository mySqlDBRepository;

    /**
     * Retrieves a list of all EntityModel objects from the MySQL database.
     *
     * @return a List of EntityModel objects.
     */
    @Override
    public List<EntityModel> getAllEntityModels() {

        return this.mySqlDBRepository.findAll();
    }

    /**
     * Retrieves an EntityModel object from the
     * MySQL database with the given testId.
     *
     * @param testId the ID of the EntityModel to retrieve.
     * @return the retrieved EntityModel object.
     * @throws ResourceNotFoundException if the EntityModel
     * object is not found in the database.
     */
    @Override
    public EntityModel getEntityModelById(final Long testId)  {
        Optional<EntityModel> testDb = this.mySqlDBRepository.findById(testId);
        if (testDb.isPresent()) {
            return testDb.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("Record not found with id : %d", testId));
        }
    }

    /**
     * Adds an EntityModel object to the MySQL database.
     *
     * @param entityModel the EntityModel object to add.
     * @return the added EntityModel object.
     */
    @Override
    public EntityModel saveEntityModel(final EntityModel entityModel) {
        return mySqlDBRepository.save(entityModel);
    }


    /**
     * Updates an EntityModel object in the MySQL database.
     *
     * @param entityModel the EntityModel object to update.
     * @return the updated EntityModel object.
     * @throws EntityNotFoundException if the EntityModel
     * object is not found in the database.
     */
    @Override
    public EntityModel updateEntityModel(final EntityModel entityModel) {
        EntityModel existingEntityModel = mySqlDBRepository
                .findById(entityModel.getTestId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Entity not found with "
                                + "id : %d ", entityModel.getTestId())));
        existingEntityModel.setTestId(entityModel.getTestId());
        existingEntityModel.setTestName(entityModel.getTestName());
        return mySqlDBRepository.save(existingEntityModel);
    }

    /**
     * Deletes an EntityModel object from
     * the MySQL database with the given testId.
     *
     * @param testId the ID of the EntityModel object to delete.
     * @throws ResourceNotFoundException if the EntityModel
     * object is not found in the database.
     */
    @Override
    public void deleteEntityModel(final Long testId) {
        Optional<EntityModel> testDb = this.mySqlDBRepository.findById(testId);
        if (testDb.isPresent()) {
            this.mySqlDBRepository.delete(testDb.get());
        } else {
            throw new ResourceNotFoundException(
                    String.format("Record not found with id : %d", testId));
        }
    }
}
