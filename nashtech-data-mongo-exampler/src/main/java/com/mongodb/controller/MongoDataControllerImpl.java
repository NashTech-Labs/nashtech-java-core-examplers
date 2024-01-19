package com.mongodb.controller;

import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;
import com.mongodb.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MongoDataControllerImpl implements MongoDataController {

    /**
     * DataService used to
     * perform business logic operations on
     * EntityModel objects. This service contains methods
     * for retrieving, creating, updating,
     * and deleting EntityModel objects from the MongoDB
     * database through the MongodbRepository.
     */
    private final DataService dataService;

    /**
     * Constructs a new instance of MongoDBDataControllerImpl
     * with the specified DataService.
     *
     * @param dataServiceParam The DataService implementation
     *                          to be used for data operations.
     */
    @Autowired
    public MongoDataControllerImpl(final DataService dataServiceParam) {
        this.dataService = dataServiceParam;
    }

    /**
     * Retrieves all EntityModel objects stored in the MongoDB database.
     *
     * @return A ResponseEntity object containing a list
     * of EntityModel objects and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */
    public ResponseEntity<List<EntityResponse>> getAllEntityModel() {
        return ResponseEntity.ok().body(dataService.getAllEntityModels());
    }

    /**
     * Retrieves a single EntityModel object from the MongoDB
     * database based on its testId.
     *
     * @param testId The ID of the EntityModel object to retrieve.
     * @return A ResponseEntity object containing the EntityModel
     * object and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */
    public ResponseEntity<EntityResponse> getEntityModelByTestId(
            @PathVariable final int testId) {
        return ResponseEntity.ok()
                .body(dataService.getEntityModelByTestId(testId));
    }

    /**
     * Retrieves all EntityModel objects associated
     * with a specific testUserId.
     *
     * @param testUserId The user ID of the EntityModel objects to retrieve.
     * @return A ResponseEntity object containing a list
     * of EntityModel
     * objects and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    public ResponseEntity<List<EntityResponse>>
    getEntityModelByUserTestId(
            @PathVariable("testUserId") final String testUserId) {
        return ResponseEntity.ok()
                .body(dataService.getEntityModelByUserTestId(testUserId));
    }

    /**
     * Inserts or updates an EntityModel object in the MongoDB database.
     * @param entityRequest The EntityRequest object to insert or update.
     * @return A ResponseEntity object containing the inserted
     * or updated EntityModel object and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    public ResponseEntity<EntityResponse>
    upsertEntityModel(@RequestBody final EntityRequest entityRequest) {
        return ResponseEntity.ok()
                .body(this.dataService.upsertEntityModel(entityRequest));
    }

    /**
     * Deletes an EntityModel object from the MongoDB database
     * based on its testId.
     * @param testId The ID of the EntityModel object to delete.
     * @return An HTTP status code indicating the result
     * of the delete operation.
     * The HTTP status code is set to 200 if the delete
     * operation was successful.
     */

    public HttpStatus deleteEntityModel(@PathVariable final int testId) {
        this.dataService.deleteEntityModel(testId);
        return HttpStatus.OK;
    }
}
