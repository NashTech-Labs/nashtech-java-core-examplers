package com.mongodb.service;

import com.mongodb.entity.EntityModel;
import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;
import com.mongodb.repository.MongodbRepository;
import com.mongodb.service.impl.DataServiceImpl;
import com.nashtechglobal.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {DataServiceImpl.class})
@ExtendWith(SpringExtension.class)
 class DataServiceImplTest {

    @Autowired
    private DataServiceImpl dataServiceImpl;

    @MockBean
    private MongodbRepository mongodbRepository;

    EntityModel RECORD_1 = new EntityModel(1, "test1", 23, "A");
    EntityModel RECORD_2 = new EntityModel(2, "test2", 27, "A");
    EntityModel RECORD_3 = new EntityModel(3, "test3", 31, "D");
    EntityResponse RESPONSE_1 = new EntityResponse( "test1", 23, "A");
    EntityResponse RESPONSE_2 = new EntityResponse( "test2", 27, "A");
    EntityResponse RESPONSE_3 = new EntityResponse( "test3", 31, "D");

    @Test
    void getPeoplesById_success() {
        when(mongodbRepository.findById(RECORD_1.getTestId())).thenReturn(Optional.ofNullable(RECORD_1));
        assertEquals(RESPONSE_1, dataServiceImpl.getEntityModelByTestId(1));
        verify(mongodbRepository).findById( any());
    }

    @Test
    void testGetPeopleByIdNoRecordFound() {
        when(mongodbRepository.findById(RECORD_1.getTestId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> dataServiceImpl.getEntityModelByTestId(1));
    }

    @Test
    void getAllPeoples_success() {
        List<EntityModel> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        when(mongodbRepository.findAll()).thenReturn(records);
        List<EntityResponse> result = dataServiceImpl.getAllEntityModels();
        assertEquals(3, result.size());
        assertEquals(Arrays.asList(RESPONSE_1, RESPONSE_2, RESPONSE_3), result);
        verify(mongodbRepository,times(1)).findAll();
    }

    @Test
    void getPeoplesByUserId_success() {
        List<EntityModel> records = new ArrayList<>(Arrays.asList(RECORD_1));
        when(mongodbRepository.findEntityModelByUserId("test1")).thenReturn(records);
        assertEquals(Collections.singletonList(RESPONSE_1), dataServiceImpl.getEntityModelByUserTestId("test1"));
        verify(mongodbRepository, times(1)).findEntityModelByUserId( any());
    }

    @Test
    void createPeopleRecord_success() {
        EntityModel record = EntityModel.builder()
                .testId(4)
                .testUserId("test4")
                .testNo(47)
                .testStatus("D")
                .build();
        EntityRequest request = EntityRequest.builder()
                .testId(4)
                .testUserId("test4")
                .testNo(47)
                .testStatus("D")
                .build();
        EntityResponse response = EntityResponse.builder()
                .userId(record.getTestUserId())
                .testNo(record.getTestNo())
                .testStatus(record.getTestStatus())
                .build();

        when(mongodbRepository.save(any(EntityModel.class))).thenReturn(record);
        assertEquals(response, dataServiceImpl.upsertEntityModel(request));
        verify(mongodbRepository, times(1)).save((EntityModel) any());
    }

    @Test
    void updatePeopleRecord_success() {
        EntityModel record = EntityModel.builder()
                .testId(4)
                .testUserId("test4")
                .testNo(47)
                .testStatus("D")
                .build();
        EntityRequest request = EntityRequest.builder()
                .testId(4)
                .testUserId("test4")
                .testNo(47)
                .testStatus("D")
                .build();
        when(mongodbRepository.save((EntityModel) any())).thenReturn(record);
        EntityResponse response = dataServiceImpl.upsertEntityModel(request);
        verify(mongodbRepository).save((EntityModel) any());
        assertEquals("test4", response.getUserId());
        assertEquals(47, response.getTestNo());
        assertEquals("D", response.getTestStatus());
        assertTrue(dataServiceImpl.getAllEntityModels().isEmpty());
    }

    @Test
    void deletePeopleById_success() {
        EntityModel record = EntityModel.builder()
                .testId(4)
                .testUserId("test4")
                .testNo(47)
                .testStatus("D")
                .build();
        Optional<EntityModel> ofResult = Optional.of(record);
        doNothing().when(mongodbRepository).delete((EntityModel) any());
        when(mongodbRepository.findById( any())).thenReturn(ofResult);
        dataServiceImpl.deleteEntityModel(4);
        verify(mongodbRepository).findById( any());
        verify(mongodbRepository).delete((EntityModel) any());
        assertTrue(dataServiceImpl.getAllEntityModels().isEmpty());
    }

    @Test
    void deletePeopleById_NotFound() {
        when(mongodbRepository.findById( any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> dataServiceImpl.deleteEntityModel(4));
    }
}
