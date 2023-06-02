package com.nashtechglobal.controller;

import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.service.MySqlDataService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySqlDataControllerImpl implements MySqlDataController {

    /**
     *The H2DataController implements methods for accessing the MySql database.
     */

    @Autowired
    private MySqlDataService mySqlDataService;

    /**
     * Retrieves all EntityModels from the MySQL database.
     *
     * @return a {@code ResponseEntity<List<EntityModel>>} containing
     * a list of all EntityModels and an HTTP status of 200 OK.
     */

    public ResponseEntity<List<EntityModel>> getAllEntityModel() {
        return ResponseEntity.ok().body(mySqlDataService.getAllEntityModels());
    }

    /**
     * Retrieves an EntityModel from the MySQL database by its ID.
     *
     * @param testId the ID of the EntityModel to retrieve.
     * @return a {@code ResponseEntity<EntityModel>} containing
     * the retrieved EntityModel and an HTTP status of 200 OK.
     * @throws Exception if the EntityModel is not found in the database.
     */

    public ResponseEntity<EntityModel> getEntityModelById(
        @PathVariable final Long testId) {
        return ResponseEntity.ok().body(
            mySqlDataService.getEntityModelById(testId));
    }

    /**
     * Creates a new EntityModel in the MySQL database.
     *
     * @param entityModel the EntityModel to create.
     * @return a {@code ResponseEntity<EntityModel>} containing
     * the created EntityModel and an HTTP status of 201 CREATED.
     */

    public ResponseEntity<EntityModel> createEntityModel(
        @RequestBody final EntityModel entityModel) {
        EntityModel savedEntity = this.mySqlDataService.
            saveEntityModel(entityModel);
        return ResponseEntity.created(
                URI.create("/result/" + savedEntity.getTestId()))
            .body(savedEntity);
    }

    /**
     * Updates an existing EntityModel in the MySQL database.
     *
     * @param entityModel the EntityModel to update.
     * @return a {@code ResponseEntity<EntityModel>} containing the
     * updated EntityModel and an HTTP status of 200 OK.
     */

    public ResponseEntity<EntityModel> updateEntityModel(
        @RequestBody final EntityModel entityModel) {
        mySqlDataService.updateEntityModel(entityModel);
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Deletes an existing EntityModel
     * from the MySQL database.
     *
     * @param testId the ID of the EntityModel to delete.
     * @return an HTTP status of 200 OK.
     */

    public HttpStatus deleteEntityModel(@PathVariable final Long testId) {
        this.mySqlDataService.deleteEntityModel(testId);
        return HttpStatus.OK;
    }
}
