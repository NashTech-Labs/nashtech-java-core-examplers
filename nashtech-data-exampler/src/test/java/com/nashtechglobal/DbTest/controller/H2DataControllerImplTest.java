package com.nashtechglobal.DbTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtechglobal.controller.H2DataController;
import com.nashtechglobal.controller.H2DataControllerImpl;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.service.H2DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {H2DataControllerImpl.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(H2DataController.class)
class H2DataControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private H2DataService h2DataService;

    @Autowired
    private H2DataControllerImpl h2DataControllerImpl;

    /**
     * Method under test: {@link H2DataControllerImpl#getEntityByTestId(long)} (Long)} (long)} (long)}
     */
    @Test
    void testGetEntityByTestId() throws Exception {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("test");
        when(h2DataService.getEntityByTestId(anyLong())).thenReturn(entityModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/h2/getResult/{testId}", 1L);
        MockMvcBuilders.standaloneSetup(h2DataControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"testId\":1,\"testName\":\"test\"}"));
    }

    /**
     * Method under test: {@link H2DataControllerImpl#getAllEntity()} ()} ()} ()}
     */
    @Test
    void testGetAllEntity() throws Exception {
        when(h2DataService.getAllEntity()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/h2/getResults");
        MockMvcBuilders.standaloneSetup(h2DataControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[]"));
    }
    @Test
    void testAddEntity() throws Exception {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestName("test");
        entityModel.setTestId(1L);

        when(h2DataService.addEntity(entityModel)).thenReturn(true);

        // Prepare the request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/h2/addResult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entityModel));

        // Perform the request and check the response
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        // Verify that the addEntity method of the H2DataService was called with the expected EntityModel
        verify(h2DataService, times(1)).addEntity(entityModel);;
    }


    /**
     * Method under test: {@link H2DataControllerImpl#updateEntity(EntityModel)} (EntityModel)} (EntityModel)} (EntityModel)}
     */
    @Test
    void testUpdateEntity() throws Exception {
        doNothing().when(h2DataService).updateEntity(Mockito.<EntityModel>any());

        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("test");

        String content = (new ObjectMapper()).writeValueAsString(entityModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/h2/updateResult/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(h2DataControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"testId\":1,\"testName\":\"test\"}"));
    }

    /**
     * Method under test: {@link H2DataControllerImpl#deleteEntity(long)} (Long)} (long)} (long)}
     */
    @Test
    void testDeleteEntity() throws Exception {
        doNothing().when(h2DataService).deleteEntity(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/h2/deleteResult/1");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(h2DataControllerImpl)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isOk());
    }

}

