package com.mongodb.service.impl;

import com.mongodb.entity.EntityModel;
import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;
import com.mongodb.repository.MongodbRepository;
import com.mongodb.service.DataService;
import com.nashtechglobal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class  DataServiceImpl implements DataService {
    /**
     * MongodbRepository used
     * to interact with a MongoDB database.
     * This repository provides methods for CRUD
     * (Create, Read, Update, Delete) operations
     * on the EntityModel documents stored in the database.
     */

    private final MongodbRepository mongodbRepository;

    /**
     * Constructs a new instance of {@code DataServiceImpl}
     * with the provided {@code MongodbRepository}.
     *
     * @param mongodbRepositoryParam The MongoDB repository
     *                               to be injected into the
     *                               service.
     *                               This repository is used
     *                               for interacting with the
     *                               MongoDB data store.
     *                               It should not be {@code null}.
     */
    @Autowired
    public DataServiceImpl(final MongodbRepository mongodbRepositoryParam) {
        this.mongodbRepository = mongodbRepositoryParam;
    }

    @Override
    public List<EntityResponse> getAllEntityModels() {

        return this.mongodbRepository.findAll()
                .stream()
                .map(model -> EntityResponse.builder()
                        .userId(model.getTestUserId())
                        .testNo(model.getTestNo())
                        .testStatus(model.getTestStatus())
                        .build()).collect(Collectors.toList());
    }
    @Override
    public EntityResponse getEntityModelByTestId(final int testId) {
        Optional<EntityModel> testDb = this.mongodbRepository.findById(testId);
        if (testDb.isPresent()) {
                EntityModel model = testDb.get();
                return EntityResponse.builder()
                        .userId(model.getTestUserId())
                        .testNo(model.getTestNo())
                        .testStatus(model.getTestStatus()).build();
        } else {
            throw new ResourceNotFoundException(
                    String.format("Record not found with id : %d",
                            testId));
        }
    }

    @Override
    public List<EntityResponse> getEntityModelByUserTestId(
            final String testUserId) {

        return this.mongodbRepository.findEntityModelByUserId(testUserId)
                .stream()
                .map(model -> EntityResponse.builder()
                        .userId(model.getTestUserId())
                        .testNo(model.getTestNo())
                        .testStatus(model.getTestStatus())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public EntityResponse upsertEntityModel(final EntityRequest entityRequest) {
        EntityModel model = mongodbRepository.save(EntityModel.builder()
                        .testId(entityRequest.getTestId())
                        .testUserId(entityRequest.getTestUserId())
                        .testNo(entityRequest.getTestNo())
                        .testStatus(entityRequest.getTestStatus())
                        .build());
        return EntityResponse.builder()
                .userId(model.getTestUserId())
                .testNo(model.getTestNo())
                .testStatus(model.getTestStatus()).build();
    }

    @Override
    public void deleteEntityModel(final int testId) {
        Optional<EntityModel> testDb = this.mongodbRepository.findById(testId);
        if (testDb.isPresent()) {
            this.mongodbRepository.delete(testDb.get());
        } else {
            throw new ResourceNotFoundException(
                    String.format("Record not found with id : %d",
                            testId));
        }
    }

}
