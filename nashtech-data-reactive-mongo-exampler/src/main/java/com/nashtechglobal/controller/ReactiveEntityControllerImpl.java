package com.nashtechglobal.controller;

import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import com.nashtechglobal.service.ReactiveEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A RestController that handles HTTP requests
 * for CRUD operations on ReactiveEntity objects.
 * <p>
 * Uses ReactiveEntityService to perform the CRUD operations.
 * </p>
 */
@RestController
public class ReactiveEntityControllerImpl implements ReactiveEntityController {
    /**
     * Service for performing reactive operations on entity data.
     */

    private final ReactiveEntityService reactiveEntityService;

    /**
     * Constructs a new instance of the
     * {@code ReactiveEntityControllerImpl} class.
     * This controller is designed to handle reactive operations
     * related to entities
     * and relies on the provided {@code ReactiveEntityService}
     * for the underlying business logic.
     *
     * @param reactiveEntityServiceParam The {@code ReactiveEntityService}
     *                                   implementation that this controller
     *                                   will use to perform reactive operations
     *                                   on entities. Must not be {@code null}.
     */
    @Autowired
    public ReactiveEntityControllerImpl(
            final ReactiveEntityService reactiveEntityServiceParam
    ) {
        this.reactiveEntityService = reactiveEntityServiceParam;
    }

    /**
     * Saves a ReactiveEntity in the database.
     *
     * @param reactiveEntityRequest a ReactiveEntity object to be saved.
     * @return a Mono of ReactiveEntity which
     * represents the saved ReactiveEntity.
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ReactiveEntityResponse> saveReactiveEntity(
            @RequestBody final ReactiveEntityRequest reactiveEntityRequest) {
        return reactiveEntityService.saveReactiveEntity(reactiveEntityRequest);
    }

    /**
     * Retrieves a ReactiveEntity by its ID.
     *
     * @param reactiveEntityId the ID of the ReactiveEntity to retrieve.
     * @return a Mono of ReactiveEntity which represents
     * the retrieved ReactiveEntity.
     */

    public Mono<ReactiveEntityResponse> getReactiveEntityById(
            @PathVariable("id") final String reactiveEntityId) {
        return reactiveEntityService.getReactiveEntity(reactiveEntityId);
    }

    /**
     * Retrieves all ReactiveEntity objects in the database.
     *
     * @return a Flux of ReactiveEntity which represents
     * all the ReactiveEntity objects.
     */
    @GetMapping
    public Flux<ReactiveEntityResponse> getAllReactiveEntities() {
        return reactiveEntityService.getAllReactiveEntities();
    }

    /**
     * Updates a ReactiveEntity with a given ID.
     *
     * @param reactiveEntityRequest ReactiveEntity object with updated fields.
     * @param reactiveEntityId the ID of the ReactiveEntity to update.
     * @return a Mono of ReactiveEntity which represents the
     * updated ReactiveEntity.
     */

    public Mono<ReactiveEntityResponse> updateReactiveEntityById(
            @RequestBody final ReactiveEntityRequest reactiveEntityRequest,
            @PathVariable("id") final String reactiveEntityId) {
        return reactiveEntityService.updateReactiveEntity(
                reactiveEntityRequest, reactiveEntityId);
    }

    /**
     * Deletes a ReactiveEntity with a given ID.
     *
     * @param reactiveEntityId the ID of the ReactiveEntity to delete.
     * @return a Mono of Void which represents an empty
     * response with HTTP status code 204.
     */

    public Mono<Void> deleteReactiveEntityById(
            @PathVariable("id") final String reactiveEntityId) {
        return reactiveEntityService.deleteReactiveEntity(reactiveEntityId);
    }
}
