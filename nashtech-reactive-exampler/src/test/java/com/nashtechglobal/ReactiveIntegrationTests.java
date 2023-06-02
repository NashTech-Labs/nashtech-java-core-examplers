package com.nashtechglobal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtechglobal.model.ReactiveExampleApiRequest;
import com.nashtechglobal.model.ReactiveExampleApiResponse;
import com.nashtechglobal.model.ReactiveExampleBody;
import com.nashtechglobal.service.ReactiveExampleService;
import com.nashtechglobal.web.service.WebClientService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ReactiveExamplerApplication.class)
@AutoConfigureMockMvc
class ReactiveIntegrationTests {

    private static final String CONTENT_TYPE = "application/json";

    private static final String REACTIVE_BASE_URL = "http://localhost:8081/reactive/entities/";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WebClientService webClientService;
    @Autowired
    private ReactiveExampleService reactiveExampleService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext applicationContext;

    private final ReactiveExampleApiResponse exampleApiResponse = new ReactiveExampleApiResponse();

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    void callExternalApiGetAllWithMono() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL, HttpMethod.GET);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/mono").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiGetAllWithFlux() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL,  HttpMethod.GET);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/flux").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiGetByIdWithMono() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL + "101",  HttpMethod.GET);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/mono").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiGetByIdWithFlux() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL + "101",  HttpMethod.GET);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/flux").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiPostWithMono() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type",CONTENT_TYPE);
        ReactiveExampleApiRequest apiRequest = callExternalApi(headers, createRequestBody(), REACTIVE_BASE_URL,  HttpMethod.POST);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/mono").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiPostWithFlux() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", CONTENT_TYPE);
        ReactiveExampleApiRequest apiRequest = callExternalApi(headers, createRequestBody(), REACTIVE_BASE_URL,  HttpMethod.POST);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/flux").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiPutWithMono() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", CONTENT_TYPE);
        ReactiveExampleApiRequest apiRequest = callExternalApi(headers, createRequestBody(), REACTIVE_BASE_URL + "101",  HttpMethod.PUT);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/mono").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiPutWithFlux() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", CONTENT_TYPE);
        ReactiveExampleApiRequest apiRequest = callExternalApi(headers, createRequestBody(), REACTIVE_BASE_URL + "101", HttpMethod.PUT);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/flux").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiDeleteByIdWithMono() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL + "101", HttpMethod.DELETE);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/mono").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    @Test
    void callExternalApiDeleteByIdWithFlux() throws Exception {
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        ReactiveExampleApiRequest apiRequest = callExternalApi(null, exampleBody, REACTIVE_BASE_URL + "101",HttpMethod.DELETE);
        doReturn(ResponseEntity.ok().body(exampleApiResponse)).when(webClientService).getExternalApiResponse(reactiveExampleService.createRequest(apiRequest), ReactiveExampleApiResponse.class);
        mockMvc.perform(post("/exampler/reactive/flux").contentType(CONTENT_TYPE).content(objectMapper.writeValueAsString(apiRequest))).andExpect(status().isOk());
    }

    private ReactiveExampleApiRequest callExternalApi( Map<String, String> headers, ReactiveExampleBody exampleBody, String url,  HttpMethod httpMethod) {
        return new ReactiveExampleApiRequest(headers, exampleBody, url, httpMethod.name());}

    private ReactiveExampleBody createRequestBody(){
        ReactiveExampleBody exampleBody = new ReactiveExampleBody();
        exampleBody.setId("101");
        exampleBody.setTestingStringData("test");
        exampleBody.setTestingLongData(123L);
        exampleBody.setTestingDateData(new Date(1));
        return exampleBody;
    }

}