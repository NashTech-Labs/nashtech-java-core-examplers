package com.nashtechglobal.service;

import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface for a reactive service that performs
 * CRUD operations on ReactiveEntity objects.
 */
public interface ReactiveEntityService {

    /**
     * Saves a reactive entity and returns a Mono of the saved entity.
     *
     * @param reactiveEntityRequest the reactive entity to be saved.
     * @return a Mono of the saved reactive entity.
     */
    Mono<ReactiveEntityResponse> saveReactiveEntity(
            ReactiveEntityRequest reactiveEntityRequest);

    /**
     * Retrieves a reactive entity with the specified
     * ID and returns a Mono of the entity.
     *
     * @param reactiveEntityId the ID of the reactive entity to be retrieved.
     * @return a Mono of the retrieved reactive entity.
     */
    Mono<ReactiveEntityResponse> getReactiveEntity(String reactiveEntityId);

    /**
     * Returns a Flux emitting all ReactiveEntity objects.
     *
     * @return a Flux of all ReactiveEntity objects.
     */
    Flux<ReactiveEntityResponse> getAllReactiveEntities();

    /**
     * Updates the ReactiveEntity object with the given id and
     * returns a Mono emitting the updated object.
     *
     * @param reactiveEntityRequest   the updated ReactiveEntity object.
     * @param reactiveEntityId the id of the ReactiveEntity object to update.
     * @return a Mono of the updated ReactiveEntity object.
     */
    Mono<ReactiveEntityResponse> updateReactiveEntity(
            ReactiveEntityRequest reactiveEntityRequest,
            String reactiveEntityId);

    /**
     * Deletes the ReactiveEntity object with the given id and
     * returns a Mono emitting nothing upon completion.
     *
     * @param reactiveEntityId the id of the ReactiveEntity object to delete.
     * @return a Mono of nothing upon completion (Void).
     */
    Mono<Void> deleteReactiveEntity(String reactiveEntityId);
}
