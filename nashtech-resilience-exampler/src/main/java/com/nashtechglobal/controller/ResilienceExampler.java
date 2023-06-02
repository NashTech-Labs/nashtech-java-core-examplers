package com.nashtechglobal.controller;

import com.nashtechglobal.model.ExamplerApiRequest;
import com.nashtechglobal.model.ExamplerApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition(info = @Info(
        title = "ResilienceExampler API Document",
        version = "1.0-SNAPSHOT",
        description = "Nashtech Resilience Exampler is a module"
                + " of the Nashtech Java Core Exampler, which will help you to "
                + "understand.\n how you can use NashTech Resilience Core."))
@Tag(name = "NashTech Resilience4j",
        description = "NashTech Resilience4j REST API endpoints")
@RequestMapping("/resilience")
public interface ResilienceExampler {

    /**
     * This endpoint retrieves results from the
     * external APIs with circuit breaker behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    @Operation(summary = "To get Circuit Breaker Results",
            description = """
                    This endpoint retrieves results from the external APIs with
                    circuit breaker behavior and the request\s
                    body containing input data for the APIs,\s
                    response body containing results from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = ExamplerApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/circuit-breaker")
    ResponseEntity<ExamplerApiResponse> getCircuitBreakerResults(
            @RequestBody ExamplerApiRequest requestBody);

    /**
     * This endpoint retrieves results from the
     * external APIs with ratelimiter behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    @Operation(summary = "To get Rate Limiter Results",
            description = """
                    This endpoint retrieves results from the external APIs with
                    Ratelimiter behavior, the request body containing input data
                    for the APIs, the response body containing results from\s
                    the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description
                    = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = ExamplerApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),

            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/rate-limiter")
    ResponseEntity<ExamplerApiResponse> getRateLimiterResults(
            @RequestBody ExamplerApiRequest requestBody);

    /**
     * This endpoint retrieves results from the
     * external APIs with bulkhead behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    @Operation(summary = "To get all BulkHead Results", description =
            """
                    This endpoint retrieves results from the external APIs with
                    bulkhead behavior, the request body containing input data\s
                    for the APIs, return the response body
                    containing results from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = ExamplerApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/bulk-head")
    ResponseEntity<ExamplerApiResponse> getAllBulkHeadResults(
            @RequestBody ExamplerApiRequest requestBody);

    /**
     * This endpoint retrieves results from the
     * external APIs with retry behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    @Operation(summary = "To get all Retry Results", description = """
            This endpoint retrieves results from the external APIs with retry\s
            behavior, the request body containing input data for the APIs,
            response body containing results from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = ExamplerApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/retry")
    ResponseEntity<ExamplerApiResponse> getRetryResults(
            @RequestBody ExamplerApiRequest requestBody);

    /**
     * This endpoint retrieves results from the
     * external APIs with circuitbreaker  and retry behavior.
     *
     * @param requestBody the request body containing input data for the APIs.
     * @return the response body containing results from the APIs.
     */
    @Operation(summary = "get all the circuit breaker and retry results",
            description = """
                    This endpoint retrieves results from the external APIs with
                     circuitbreaker  and retry behavior, request body\s
                     containing input data for the APIs, the response body\s
                     containing results\s
                     from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = ExamplerApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/circuitBreaker-retry")
    ResponseEntity<ExamplerApiResponse> getCircuitBreakerAndRetryResults(

            @RequestBody ExamplerApiRequest requestBody);

}
