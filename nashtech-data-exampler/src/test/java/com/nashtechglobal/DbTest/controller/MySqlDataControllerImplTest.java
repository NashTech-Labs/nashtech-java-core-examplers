package com.nashtechglobal.DbTest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtechglobal.DBExamplerApplication;
import com.nashtechglobal.controller.MySqlDataControllerImpl;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.service.MySqlDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MySqlDataControllerImpl.class)
@ContextConfiguration(classes = DBExamplerApplication.class)
class MySqlDataControllerImplTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    EntityModel RECORD_1 = new EntityModel(1L, "test1");
    EntityModel RECORD_2 = new EntityModel(2L, "test2");
    EntityModel RECORD_3 = new EntityModel(3L, "test3");
    @MockBean
    private MySqlDataService mySqlDataService;

    @Test
    void getEntity_success() throws Exception {
        List<EntityModel> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(mySqlDataService.getAllEntityModels()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/mysql/getResults")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].testName", is("test3")));
    }

    @Test
    void getEntityByTestId_success() throws Exception {
        when(mySqlDataService.getEntityModelById(RECORD_1.getTestId())).thenReturn(RECORD_1);

        mockMvc.perform(get("/api/v1/mysql/getResult/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.testName", is("test1")));
    }

    @Test
    void createEntity_success() throws Exception {
        EntityModel record = EntityModel.builder()
                .testId(1L)
                .testName("test11")
                .build();

        when(mySqlDataService.saveEntityModel(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/mysql/addResult")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.testName", is("test11")));
    }

    @Test
    void updateEntityModel_Success() throws Exception {
        EntityModel record = EntityModel.builder()
                .testId(4L)
                .testName("test11")
                .build();
        String jsonRequest = mapper.writeValueAsString(record);

        Mockito.when(mySqlDataService.updateEntityModel(Mockito.any(EntityModel.class))).thenReturn(record);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/mysql/updateResult/4")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        EntityModel response = mapper.readValue(result.getResponse().getContentAsString(), EntityModel.class);
        assertNotNull(response);
        assertEquals("test11", response.getTestName());
    }


    @Test
    void deleteResultByTestId_success() throws Exception {
        when(mySqlDataService.getEntityModelById(RECORD_2.getTestId())).thenReturn(RECORD_2);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/mysql/deleteResult/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

