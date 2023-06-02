package com.mongodb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DataExamplerApplication;
import com.mongodb.entity.EntityModel;
import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;
import com.mongodb.service.DataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebMvcTest(MongoDataControllerImpl.class)
@ContextConfiguration(classes = DataExamplerApplication.class)
 class MongoDataControllerImplTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private DataService dataService;

    EntityModel RECORD_1 = new EntityModel(1, "test1", 23, "A");
    EntityModel RECORD_2 = new EntityModel(2, "test2", 27, "A");
    EntityResponse RESPONSE_1 = new EntityResponse( "test1", 23, "A");
    EntityResponse RESPONSE_2 = new EntityResponse( "test2", 27, "A");
    EntityResponse RESPONSE_3 = new EntityResponse( "test3", 31, "D");

    @Test
     void getAllEntity_success() throws Exception {
        List<EntityResponse> records = new ArrayList<>(Arrays.asList(RESPONSE_1, RESPONSE_2, RESPONSE_3));

        Mockito.when(dataService.getAllEntityModels()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/getResults")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].userId", is("test3")));
    }

    @Test
     void getEntityByTestId_success() throws Exception {
        Mockito.when(dataService.getEntityModelByTestId(RECORD_1.getTestId())).thenReturn(RESPONSE_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/getResult/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userId", is("test1")));
    }
    @Test
     void getPeoplesByUserId_success() throws Exception {
        Mockito.when(dataService.getEntityModelByUserTestId(RECORD_1.getTestUserId())).thenReturn(Collections.singletonList(RESPONSE_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/getResult/find/test1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].userId", is("test1")));
    }
    @Test
     void createRecord_success() throws Exception {
        EntityRequest record = EntityRequest.builder()
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

        Mockito.when(dataService.upsertEntityModel(record)).thenReturn(response);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/upsertResult")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userId", is("test4")));
    }

    @Test
     void updatePeopleRecord_success() throws Exception {
        EntityRequest updatedRecord = EntityRequest.builder()
                .testId(1)
                .testUserId("test4")
                .testNo(10)
                .testStatus("A")
                .build();
       EntityResponse response = EntityResponse.builder()
               .userId(updatedRecord.getTestUserId())
               .testNo(updatedRecord.getTestNo())
               .testStatus(updatedRecord.getTestStatus())
               .build();

        Mockito.when(dataService.upsertEntityModel(updatedRecord)).thenReturn(response);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/upsertResult")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userId", is("test4")));
        }

    @Test
     void updatePeopleEmptyRecord() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/upsertResult")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertEquals(400, result.getResponse().getStatus()));
    }

    @Test
     void deletePeopleById_success() throws Exception {
        Mockito.when(dataService.getEntityModelByTestId(RECORD_2.getTestId())).thenReturn(RESPONSE_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/deleteResult/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
