package com.nashtechglobal.controller;

import com.nashtechglobal.model.ReactiveExampleApiRequest;
import com.nashtechglobal.model.ReactiveExampleApiResponse;
import com.nashtechglobal.service.ReactiveExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A controller that handles reactive requests to the "/exampler/reactive"
 * endpoint for external API requests. It provides methods for retrieving
 * data from an external API using reactive programming constructs such as
 * Mono and Flux.
 *
 * @see Mono
 * @see Flux
 * @see ReactiveExampleService
 */
@RestController
public class ReactiveExamplerController implements ReactiveExampler {

    /**
     * The {@link ReactiveExampleService} instance.
     */
    @Autowired
    private ReactiveExampleService exampleService;

    /**
     * Handles an HTTP Request to retrieve a {@link Mono}
     * of {@link ReactiveExampleApiResponse} objects.
     *
     * @param requestBody {@link ReactiveExampleApiRequest}
     *                    containing request information
     * @return a {@link ResponseEntity} containing the {@link Mono} of
     *     {@link ReactiveExampleApiResponse} objects, with a status code of
     *     200 (OK) if the request is successful, otherwise status code of 400
     *     (Bad Request)
     * @see ReactiveExampleApiRequest
     * @see ReactiveExampleApiResponse
     * @see Mono
     * @see ResponseEntity
     */

    public ResponseEntity<Mono<ReactiveExampleApiResponse>> getMonoApiResponse(
            @RequestBody final ReactiveExampleApiRequest requestBody) {
        return exampleService.fetchMonoExternalApiResponses(requestBody);
    }

    /**
     * Handles an HTTP Request to retrieve a {@link Flux}
     * of {@link ReactiveExampleApiResponse} objects.
     *
     * @param requestBody {@link ReactiveExampleApiRequest}
     *                    containing request information
     * @return a {@link ResponseEntity} containing the {@link Flux}
     *     of {@link ReactiveExampleApiResponse} objects,
     *     with a status code of 200 (OK) if the request is successful,
     *     otherwise a status code of 400 (Bad Request)
     * @see ReactiveExampleApiRequest
     * @see ReactiveExampleApiResponse
     * @see Flux
     * @see ResponseEntity
     */

    public ResponseEntity<Flux<ReactiveExampleApiResponse>> getFluxApiResponse(
            @RequestBody final ReactiveExampleApiRequest requestBody) {
        return exampleService.fetchFluxExternalApiResponses(requestBody);
    }
}
