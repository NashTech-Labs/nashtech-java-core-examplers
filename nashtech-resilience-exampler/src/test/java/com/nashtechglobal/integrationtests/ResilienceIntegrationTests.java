package com.nashtechglobal.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtechglobal.ResilienceExamplerApplication;
import com.nashtechglobal.model.ExamplerApiRequest;
import com.nashtechglobal.model.ExamplerApiResponse;
import com.nashtechglobal.service.ResilienceExampleService;
import com.nashtechglobal.web.service.WebClientService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ResilienceExamplerApplication.class)
@AutoConfigureMockMvc
class ResilienceIntegrationTests {
    private static final String CONTENT_TYPE = "application/json";
    private static final String BASE_URL = "/resilience";
    protected static String CIRCUIT_BREAKER_NAME = "circuit_breaker";
    @Autowired
    private CircuitBreakerRegistry registry;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WebClientService webClientService;
    @Autowired
    private ResilienceExampleService resilienceExampleService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext applicationContext;
    @Value("${resilience4j.retry.instances.retry.maxAttempts}")
    private int retryMaxAttempts;

    @Value("${resilience4j.circuitbreaker.instances.circuit_breaker.slidingWindowSize}")
    private int slidingWindowSize;

    @Value("${resilience4j.circuitbreaker.instances.circuit_breaker.waitDurationInOpenState}")
    private String waitDurationInOpenState;

    @Value("${resilience4j.circuitbreaker.instances.circuit_breaker.permittedNumberOfCallsInHalfOpenState}")
    private int permittedNoOfCallsInHalfOpenState;
    @Value("${resilience4j.ratelimiter.instances.ratelimiter.limitForPeriod}")
    private int limitForPeriod;

    @Value("${resilience4j.ratelimiter.instances.ratelimiter.limitRefreshPeriod}")
    private String limitRefreshPeriod;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    /**
     * This method calls the external service once as service is giving successful response.
     */
    @SneakyThrows
    @DisplayName("retrySuccessTest")
    @Test
    void testShouldCallExternalService_Once() {

        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doReturn(ResponseEntity.ok().body(examplerApiResponse))
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/retry"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isOk());

        verify(webClientService, times(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);
    }

    /**
     * This method calls external service when it is down for some reason.
     * It retries 3 times as per configuration and then enters into fallback method.
     */
    @SneakyThrows
    @DisplayName("retryFallbackTest")
    @Test
    void testShouldCallExternalServiceAsPer_MaxRetryAttempts() {

        ExamplerApiRequest apiRequest
                = new ExamplerApiRequest("https://www.goog22le.com/", "GET", null, null);

        doThrow(ResourceAccessException.class)
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/retry"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isBadRequest());

        verify(webClientService, times(retryMaxAttempts))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);
    }

    /**
     * This method calls the external service once as service is giving successful response.
     */
    @SneakyThrows
    @DisplayName("circuitBreakerSuccessTest")
    @Test
    void testShouldCallExternalService_Once_Success() {

        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doReturn(ResponseEntity.ok().body(examplerApiResponse))
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/circuit-breaker"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isOk());

        verify(webClientService, times(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);
        registry.circuitBreaker(CIRCUIT_BREAKER_NAME).reset();
    }


    @SneakyThrows
    @DisplayName("circuitBreakerAndRetrySuccessTest")
    @Test
    void testShouldCallExternalServiceOnSuccess() {

        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doReturn(ResponseEntity.ok().body(examplerApiResponse))
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/circuitBreaker-retry"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isOk());

        verify(webClientService, times(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);
        registry.circuitBreaker(CIRCUIT_BREAKER_NAME).reset();
    }

    /**
     * This method tests all the states of circuit-breaker as the service is down so the state will be OPEN.
     * It remains in OPEN state for 5S as per configuration(waitDurationInOpenState:5s)
     * After 5s, it goes in HALF OPEN state.
     * Only 2 calls are allowed in HALF OPEN state as per configuration(waitDurationInOpenState:2)
     * If the service will come UP, then the state will move to CLOSED state otherwise it remains in OPEN state.
     */
    /*circuit breaker fallback tests. Contains 4 different groups of 5 requests (based on configs)each.
    1. CLOSED STATE for first 5 requests
    2. OPEN STATE AFTER FIRST 5 REQUESTS as (sliding-window-size: 5)
    3. HALF_OPEN STATE as (wait-duration-in-open-state: 5s)
    4. OPEN STATE AFTER (permitted-number-of-calls-in-half-open-state:5)
   */
    @SneakyThrows
    @DisplayName("circuitBreakerFallbackTest")
    @RepeatedTest(value = 20)
    void testShouldVerify_AllStatesOfCircuitBreaker(RepetitionInfo repetitionInfo) {

        int currentRepetition = repetitionInfo.getCurrentRepetition();
        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.goog22le.com/", "GET", null, null);

        doThrow(CallNotPermittedException.class)
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/circuit-breaker"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isBadRequest());

        // CLOSED STATE for first 5 requests
        if (currentRepetition < slidingWindowSize) {
            assertThat(getCircuitBreakerStatus(), equalTo(CircuitBreaker.State.CLOSED));
        }

        // OPEN STATE AFTER FIRST 5 REQUESTS as (sliding-window-size: 5)
        if (currentRepetition >= slidingWindowSize && currentRepetition <= slidingWindowSize * 2) {
            assertThat(getCircuitBreakerStatus(), equalTo(CircuitBreaker.State.OPEN));
        }

        // HALF_OPEN STATE as (wait-duration-in-open-state: 5s)
        if (currentRepetition == slidingWindowSize * 2) {
            CountDownLatch latch = new CountDownLatch(1);
            latch.await(Long.parseLong(waitDurationInOpenState.substring(0, waitDurationInOpenState.length() - 1)), TimeUnit.SECONDS);
            assertThat(getCircuitBreakerStatus(), equalTo(CircuitBreaker.State.HALF_OPEN));
        }

        //OPEN STATE AFTER (permitted-number-of-calls-in-half-open-state:5)
        if (currentRepetition >= permittedNoOfCallsInHalfOpenState + slidingWindowSize * 2) {
            registry.circuitBreaker(CIRCUIT_BREAKER_NAME).reset();
            assertThat(getCircuitBreakerStatus(), equalTo(CircuitBreaker.State.CLOSED));
        }
    }

    /**
     * This method calls the external service once as service is giving successful response.
     */
    @SneakyThrows
    @DisplayName("rateLimiterSuccessTest")
    @Test
    void testShouldCallExternalService_Once_Ratelimiter() {
        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "POST", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doReturn(ResponseEntity.ok().body(examplerApiResponse))
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/rate-limiter"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isOk());

        verify(webClientService, times(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);
    }

    /**
     * This method calls the external service 2 times (limitRefreshPeriod:2) and then enters to fallback method.
     */
    @SneakyThrows
    @DisplayName(value = "rateLimiterFallbackTest")
    @RepeatedTest(value = 5)
    void testShouldCallServiceAsPer_LimitForPeriod(RepetitionInfo repetitionInfo) {

        int currentRepetition = repetitionInfo.getCurrentRepetition();
        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);

        doThrow(ResourceAccessException.class)
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/rate-limiter"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isBadRequest());

        if (currentRepetition <= limitForPeriod) {
            verify(webClientService, times(1))
                    .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        } else {
            verify(webClientService, times(0))
                    .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        }
    }


    /**
     * This method calls the external service once as service is giving successful response.
     */
    @SneakyThrows
    @DisplayName(value = "bulkheadSuccessTest")
    @Test
    void bulkhead_Success_Test() {

        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doReturn(ResponseEntity.ok().body(examplerApiResponse))
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/bulk-head"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isOk());

        verify(webClientService, atLeast(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

    }

    /**
     *
     */
    @SneakyThrows
    @DisplayName(value = "bulkheadFallbackTest")
    @Test
    void bulkhead_Fallback_Test() {

        ExamplerApiRequest apiRequest =
                new ExamplerApiRequest("https://www.google.com/", "GET", null, null);
        ExamplerApiResponse examplerApiResponse = new ExamplerApiResponse();

        doThrow(ResourceAccessException.class)
                .when(webClientService)
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

        mockMvc.perform(post(BASE_URL.concat("/bulk-head"))
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(apiRequest)))
                .andExpect(status().isBadRequest());

        verify(webClientService, times(1))
                .getExternalApiResponse(resilienceExampleService.createRequest(apiRequest), ExamplerApiResponse.class);

    }


    /**
     * This method returns the STATE of circuit-breaker.
     */
    protected CircuitBreaker.State getCircuitBreakerStatus() {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        return circuitBreaker.getState();
    }


}
