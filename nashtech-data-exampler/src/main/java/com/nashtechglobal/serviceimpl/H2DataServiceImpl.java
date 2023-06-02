package com.nashtechglobal.serviceimpl;


import com.nashtechglobal.exception.ResourceNotFoundException;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.repository.H2DBRepository;
import com.nashtechglobal.service.H2DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class H2DataServiceImpl implements H2DataService {
    /**
     *
     */
    @Autowired
    private H2DBRepository h2DBRepository;

    /**
     * Retrieves the EntityModel object associated with
     * the specified test ID from the H2 database.
     *
     * @param testId the ID of the test to retrieve the EntityModel for.
     * @return the EntityModel object associated with the specified test ID.
     * @throws ResourceNotFoundException if no EntityModel
     * object is found for the specified test ID.
     */
    @Override
    public EntityModel getEntityByTestId(final long testId) {
        Optional<EntityModel> optionalEntity = h2DBRepository.findById(testId);
        if (optionalEntity.isPresent()) {
            return optionalEntity.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("Entity not found found "
                            + "for test ID: %d", testId));
        }
    }

    /**
     * Retrieves all EntityModel objects from the H2 database.
     *
     * @return a List of all EntityModel objects in the H2 database.
     */
    @Override
    public List<EntityModel> getAllEntity() {
        List<EntityModel> list = new ArrayList<>();
        h2DBRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Adds an EntityModel object to the H2 database.
     *
     * @param entityModel the EntityModel object
     *                    to add to the H2 database.
     * @return true if the EntityModel object was successfully
     * added to the H2 database, false otherwise.
     */
    @Override
    public boolean addEntity(final EntityModel entityModel) {
        h2DBRepository.save(entityModel);
        return true;
    }

    /**
     * Updates an EntityModel object in the H2 database.
     *
     * @param entityModel the EntityModel object to update in the H2 database.
     */
    @Override
    public void updateEntity(final EntityModel entityModel) {
        h2DBRepository.save(entityModel);
    }

    /**
     * Deletes the EntityModel object associated with the specified
     * test ID from the H2 database.
     *
     * @param testId the ID of the test to delete the EntityModel for.
     */
    @Override
    public void deleteEntity(final long testId) {
        h2DBRepository.delete(getEntityByTestId(testId));
    }
}
