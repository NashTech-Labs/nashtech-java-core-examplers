package com.nashtechglobal.service;

import com.nashtechglobal.entity.ReactiveEntity;
import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import com.nashtechglobal.repository.ReactiveEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ReactiveEntityService
 * interface that provides methods to
 * perform CRUD operations on ReactiveEntity objects in a reactive way.
 */
@Service
public class ReactiveEntityServiceImpl implements ReactiveEntityService {

    /**
     * Autowired CoreLogger instance for logging application
     * events and messages.
     */
    @Autowired
    private CoreLogger coreLogger;

    /**
     * Autowired field for ReactiveMongoDBRepository instance.
     */
    @Autowired
    private ReactiveEntityRepository repository;

    /**
     * Saves a reactive entity and returns a Mono of the saved entity.
     *
     * @param reactiveEntityRequest the reactive entity to be saved.
     * @return a Mono of the saved reactive entity.
     */
    @Override
    public Mono<ReactiveEntityResponse> saveReactiveEntity(
            final ReactiveEntityRequest reactiveEntityRequest) {
        ReactiveEntity reactiveEntity = new ReactiveEntity(
                reactiveEntityRequest.getId(),
                reactiveEntityRequest.getTestingStringData(),
                reactiveEntityRequest.getTestingLongData(),
                reactiveEntityRequest.getTestingDateData());
        Mono<ReactiveEntity> savedEntity = repository.save(reactiveEntity);
        coreLogger.info("Saving ReactiveEntity - {}", reactiveEntity);
        return mapMonoEntityToResponse(savedEntity);
    }

    /**
     * Retrieves a reactive entity with the specified
     * ID and returns a Mono of the entity.
     *
     * @param reactiveEntityId the ID of the reactive entity to be retrieved.
     * @return a Mono of the retrieved reactive entity.
     */
    @Override
    public Mono<ReactiveEntityResponse> getReactiveEntity(
            final String reactiveEntityId) {
        Mono<ReactiveEntity> reactiveEntityMono = repository
                .findById(reactiveEntityId);
        coreLogger.info("Fetching ReactiveEntity with Id -  {}",
                reactiveEntityId);
        return mapMonoEntityToResponse(reactiveEntityMono);
    }

    /**
     * Returns a Flux emitting all ReactiveEntity objects.
     *
     * @return a Flux of all ReactiveEntity objects.
     */
    @Override
    public Flux<ReactiveEntityResponse> getAllReactiveEntities() {
        Flux<ReactiveEntity> reactiveEntityFlux = repository.findAll();
        coreLogger.info("Fetching all reactive entities");
        return mapFluxEntityToResponse(
                reactiveEntityFlux.switchIfEmpty(Flux.empty())
        );
    }

    /**
     * Updates the ReactiveEntity object with the given id
     * and returns a Mono emitting the updated object.
     *
     * @param reactiveEntityRequest the updated ReactiveEntity object.
     * @param reactiveEntityId      the id of ReactiveEntity object to update.
     * @return a Mono of the updated ReactiveEntity object.
     */
    @Override
    public Mono<ReactiveEntityResponse> updateReactiveEntity(
            final ReactiveEntityRequest reactiveEntityRequest,
            final String reactiveEntityId) {
        Mono<ReactiveEntity> reactiveEntityMono = repository.
                findById(reactiveEntityId);
        coreLogger.info(
                "Updating ReactiveEntity with Id - {}, with data - {} ",
                reactiveEntityId,
                reactiveEntityRequest);
        ReactiveEntity reactiveEntity = new ReactiveEntity(
                reactiveEntityRequest.getId(),
                reactiveEntityRequest.getTestingStringData(),
                reactiveEntityRequest.getTestingLongData(),
                reactiveEntityRequest.getTestingDateData());
        return reactiveEntityMono.flatMap(existingEntity -> {
            existingEntity.setTestingStringData(reactiveEntity
                    .getTestingStringData());
            existingEntity.setTestingLongData(reactiveEntity
                    .getTestingLongData());
            existingEntity.setTestingDateData(reactiveEntity
                    .getTestingDateData());
            return mapMonoEntityToResponse(repository.save(existingEntity));
        });
    }

    /**
     * Deletes the ReactiveEntity object with the given id
     * and returns a Mono emitting nothing upon completion.
     *
     * @param reactiveEntityId the id of the ReactiveEntity object to delete.
     * @return a Mono of nothing upon completion (Void).
     */
    @Override
    public Mono<Void> deleteReactiveEntity(final String reactiveEntityId) {
        coreLogger.info("Deleting ReactiveEntity with Id - {}",
                reactiveEntityId);
        return repository.deleteById(reactiveEntityId);
    }

    private Mono<ReactiveEntityResponse> mapMonoEntityToResponse(
            final Mono<ReactiveEntity> reactiveEntityMono) {
        Optional<ReactiveEntity> optionalReactiveEntity =
                reactiveEntityMono.blockOptional();
        if (optionalReactiveEntity.isPresent()) {
            ReactiveEntity entity = optionalReactiveEntity.get();
            return Mono.just(new ReactiveEntityResponse(
                    entity.getTestingStringData(),
                    entity.getTestingLongData(),
                    entity.getTestingDateData())
            );
        }
        return Mono.empty();
    }

    private Flux<ReactiveEntityResponse> mapFluxEntityToResponse(
            final Flux<ReactiveEntity> reactiveEntity) {
        Optional<List<ReactiveEntity>> reactiveEntities =
                reactiveEntity.collectList().blockOptional();
        if (reactiveEntities.isPresent()) {
            return  Flux.fromIterable(reactiveEntities.get()
                    .stream()
                    .map(entity -> new ReactiveEntityResponse(
                            entity.getTestingStringData(),
                            entity.getTestingLongData(),
                            entity.getTestingDateData()))
                    .toList());
        }
        return Flux.empty();
    }
}
