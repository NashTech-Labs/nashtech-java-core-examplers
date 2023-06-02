package com.nashtechglobal.service;

import com.nashtechglobal.model.ExamplerApiRequest;
import com.nashtechglobal.model.ExamplerApiResponse;
import com.nashtechglobal.resilience.service.ResilienceService;
import com.nashtechglobal.web.model.ExternalApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * Service class that provides methods for calling
 * external APIs using various Resilience4j modules.
 */
@Service
public class ResilienceExampleService {

    /**
     * An instance of the ResilienceService to call external
     * APIs with Resilience4j modules.
     */
    @Autowired
    private ResilienceService resilienceService;


    /**
     * Calls an external API using RateLimiter module and
     * returns the response.
     *
     * @param requestBody An instance of ExamplerApiRequest representing
     *                    the request body of the external API.
     * @return An instance of ExamplerApiResponse representing the
     * response of the external API.
     */
    public ExamplerApiResponse getRateLimiterExternalApiResponse(
            final ExamplerApiRequest requestBody) {
        return createResponse(resilienceService.
                callExternalApiWithRateLimiter(createRequest(requestBody),
                        ExamplerApiResponse.class));
    }

    /**
     * Calls an external API using Retry module and returns
     * the response.
     *
     * @param requestBody An instance of ExamplerApiRequest
     *                    representing the request body of the external API.
     * @return An instance of ExamplerApiResponse representing
     * the response of the external API.
     */
    public ExamplerApiResponse getRetryExternalApiResponse(
            final ExamplerApiRequest requestBody) {

        return createResponse(resilienceService.
                callExternalApiWithRetry(createRequest(requestBody),
                        ExamplerApiResponse.class));
    }

    /**
     * Calls an external API using CircuitBreaker module and
     * returns the response.
     *
     * @param requestBody An instance of ExamplerApiRequest representing
     *                    the request body of the external API.
     * @return An instance of ExamplerApiResponse representing the
     * response of the external API.
     */
    public ExamplerApiResponse getCircuitBreakerExternalApiResponse(
            final ExamplerApiRequest requestBody) {
        return createResponse(resilienceService.
                callExternalApiWithCircuitBreaker(createRequest(requestBody),
                        ExamplerApiResponse.class));
    }

    /**
     * Calls an external API using CircuitBreaker with Retry module
     * and returns the response.
     *
     * @param requestBody An instance of ExamplerApiRequest representing
     *                    the request body of the external API.
     * @return An instance of ExamplerApiResponse representing
     * the response of the external API.
     */
    public ExamplerApiResponse getCircuitBreakerAndRetryExternalApiResponse(
            final ExamplerApiRequest requestBody) {
        return createResponse(resilienceService.
                callExternalApiWithCircuitBreakerAndRetry(
                        createRequest(requestBody), ExamplerApiResponse.class));
    }

    /**
     * Calls an external API using Bulkhead module and returns the response.
     *
     * @param requestBody An instance of ExamplerApiRequest representing
     *                    the request body of the external API.
     * @return An instance of ExamplerApiResponse representing
     * the response of the external API.
     */
    public ExamplerApiResponse getBulkHeadExternalApiResponse(
            final ExamplerApiRequest requestBody) {
        return createResponse(resilienceService.
                callExternalApiWithBulkHead(createRequest(requestBody),
                        ExamplerApiResponse.class));
    }

    /**
     * Creates an ExamplerApiResponse based on the provided ResponseEntity.
     *
     * @param response the ResponseEntity containing the ExamplerApiResponse.
     * @return the ExamplerApiResponse extracted from the
     * ResponseEntity or null if the response was not successful.
     */
    private ExamplerApiResponse createResponse(
            final ResponseEntity<ExamplerApiResponse> response) {

        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    /**
     * Creates an instance of HttpHeaders and sets the headers
     * passed as a map parameter to it.
     *
     * @param httpHeadersMap a Map containing headers as key-value
     *                       pairs to be added to the HttpHeaders object
     * @return a HttpHeaders object with the headers added
     */
    private HttpHeaders createHeaders(
            final Map<String, String> httpHeadersMap) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(httpHeadersMap)) {
            for (Map.Entry<String, String> entry : httpHeadersMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return headers;
    }

    /**
     * Creates an instance of ExternalApiRequest from ExamplerApiRequest.
     *
     * @param examplerRequest An instance of ExamplerApiRequest representing
     *                        the request body of the external API.
     * @return An instance of ExternalApiRequest representing
     * the external API request.
     */
    public ExternalApiRequest<ExamplerApiRequest> createRequest(
            final ExamplerApiRequest examplerRequest) {
        if (examplerRequest != null) {
            return ExternalApiRequest.<ExamplerApiRequest>builder().
                    body(examplerRequest.getBody())
                    .httpMethod(HttpMethod
                            .valueOf(examplerRequest.getHttpMethod()))
                    .url(examplerRequest.getUrl())
                    .headers(createHeaders(examplerRequest.getHeaders()))
                    .build();
        }
        return null;
    }

}
