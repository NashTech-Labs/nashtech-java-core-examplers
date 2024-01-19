package com.nashtechglobal.controller;


import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.service.H2DataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class H2DataControllerImpl implements H2DataController {
    /**
     * The H2DataService object provides methods for accessing the H2 database.
     */
    private final H2DataService iDataService;

    /**
     * Constructs a new instance of the H2DataControllerImpl class,
     * initializing it with the specified H2DataService.
     *
     * @param iDataServiceParam The H2DataService implementation to
     * be used by this controller. It provides the data-related
     * functionality required by the controller.
     * Must not be null.
     *
     * @throws IllegalArgumentException if the provided iDataService is null.
     */
    @Autowired
    public H2DataControllerImpl(final H2DataService iDataServiceParam) {
        this.iDataService = iDataServiceParam;
    }

    /**
     * Retrieves an EntityModel with the given test ID from the H2 database.
     *
     * @param testId The test ID of the EntityModel to retrieve.
     * @return A ResponseEntity containing the EntityModel
     * and an HTTP status code.
     */
    public ResponseEntity<EntityModel> getEntityByTestId(
            @PathVariable("testId") final long testId) {
        EntityModel student = iDataService.getEntityByTestId(testId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Retrieves all EntityModels from the H2 database.
     *
     * @return A ResponseEntity containing a list of
     * EntityModels and an HTTP status code.
     */
    public ResponseEntity<List<EntityModel>> getAllEntity() {
        List<EntityModel> list = iDataService.getAllEntity();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * Adds an EntityModel to the H2 database.
     *
     * @param entityModel The EntityModel to add.
     * @return A ResponseEntity with an HTTP status code.
     */
    public ResponseEntity<EntityModel> addEntity(
            @RequestBody final EntityModel entityModel) {
        iDataService.addEntity(entityModel);
        return ResponseEntity.ok().body(entityModel);
    }
    /**
     * Updates an existing EntityModel in the H2 database.
     *
     * @param entityModel The updated EntityModel.
     * @return A ResponseEntity containing the updated
     * EntityModel and an HTTP status code.
     */
    public ResponseEntity<EntityModel> updateEntity(
            @RequestBody final EntityModel entityModel) {
        iDataService.updateEntity(entityModel);
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Deletes an existing EntityModel from the H2 database.
     *
     * @param testId The test ID of the EntityModel to delete.
     * @return A ResponseEntity with an HTTP status code.
     */
    public ResponseEntity<Void> deleteEntity(
            @PathVariable("testId") final long testId) {
        iDataService.deleteEntity(testId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
