package com.nashtechglobal.controller;

import com.nashtechglobal.model.ExamplerApiRequest;
import com.nashtechglobal.model.ExamplerApiResponse;
import com.nashtechglobal.service.ResilienceExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the controller for the resilience service,
 * which defines the REST API endpoints.
 */
@RestController
public class ResilienceExamplerController implements ResilienceExampler {

    /**
     * The ResilienceExampleService instance to use for calling the
     * external APIs with resilient behavior.
     */
    @Autowired
    private ResilienceExampleService resilienceExampleService;


    /**
     * This endpoint retrieves results from the
     * external APIs with circuit breaker behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    public ResponseEntity<ExamplerApiResponse> getCircuitBreakerResults(
            @RequestBody final ExamplerApiRequest requestBody) {
        return createResponse(resilienceExampleService
                .getCircuitBreakerExternalApiResponse(requestBody));
    }

    /**
     * This endpoint retrieves results from the
     * external APIs with ratelimiter behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    public ResponseEntity<ExamplerApiResponse> getRateLimiterResults(
            @RequestBody final ExamplerApiRequest requestBody) {
        return createResponse(resilienceExampleService
                .getRateLimiterExternalApiResponse(requestBody));
    }

    /**
     * This endpoint retrieves results from the
     * external APIs with bulkhead behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    public ResponseEntity<ExamplerApiResponse> getAllBulkHeadResults(
            @RequestBody final ExamplerApiRequest requestBody) {

        return createResponse(resilienceExampleService
                .getBulkHeadExternalApiResponse(requestBody));
    }

    /**
     * This endpoint retrieves results from the
     * external APIs with retry behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    public ResponseEntity<ExamplerApiResponse> getRetryResults(
            @RequestBody final ExamplerApiRequest requestBody) {

        return createResponse(resilienceExampleService
                .getRetryExternalApiResponse(requestBody));
    }

    /**
     * This endpoint retrieves results from the
     * external APIs with circuitbreaker  and retry behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    public ResponseEntity<ExamplerApiResponse> getCircuitBreakerAndRetryResults(
            @RequestBody final ExamplerApiRequest requestBody) {

        return createResponse(resilienceExampleService
                .getCircuitBreakerAndRetryExternalApiResponse(requestBody));
    }


    /**
     * Helper method to create a ResponseEntity object based on the
     * given ExamplerApiResponse object.
     *
     * @param response the ExamplerApiResponse object to be
     *                 returned in the body of the ResponseEntity object.
     * @return a ResponseEntity object with the HTTP status
     * and the response object in the body.
     */
    private ResponseEntity<ExamplerApiResponse> createResponse(
            final ExamplerApiResponse response) {
        if (response != null) {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(null);

    }
}
