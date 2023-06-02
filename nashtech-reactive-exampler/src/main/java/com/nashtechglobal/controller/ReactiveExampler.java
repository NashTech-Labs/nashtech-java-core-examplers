package com.nashtechglobal.controller;

import com.nashtechglobal.model.ReactiveExampleApiRequest;
import com.nashtechglobal.model.ReactiveExampleApiResponse;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@OpenAPIDefinition(info = @Info(
        title = "ReactiveExampler API Document", version = "1.0-SNAPSHOT",
        description = "Nashtech Reactive Exampler is a module"
                + " of the Nashtech Java Core Exampler, which will help you to "
                + "understand\n"
                + "how you can use NashTech Reactive Core."))
@Tag(name = "NashTech Reactive",
        description = "NashTech Reactive Exampler REST API endpoints")

@RequestMapping("exampler/reactive")
public interface ReactiveExampler {


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
    @Operation(summary = "To get the Mono ReactiveExampleApiRequest Results",
            description = """
                    This endpoint retrieves request to
                    external APIs to Handles an HTTP Request to retrieve
                    mono of ReactiveExampleApiResponse objects.\s
                    param requestBody containing input data for the APIs.\s
                    return the response body containing
                    results from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully Found The ExampleBody",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    ReactiveExampleApiResponse.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Mono ApiResponse not found",
                    content = @Content)})
    @PostMapping("/mono")
    ResponseEntity<Mono<ReactiveExampleApiResponse>> getMonoApiResponse(
            @RequestBody  ReactiveExampleApiRequest requestBody);


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
    @Operation(summary = "To get the Flux ReactiveExampleApiRequest Results",
            description = """
                     This endpoint retrieves request to
                     external APIs to Handles  an HTTP Request to retrieve Flux
                     of ReactiveExampleApiResponse objects.\s
                     param requestBody containing input data for the APIs.
                     return the response body containing
                     results from the APIs.""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully Found The ExampleBody",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation =
                                    ReactiveExampleApiResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Flux ApiResponse not found",
                    content = @Content)})
    @PostMapping("/flux")
    ResponseEntity<Flux<ReactiveExampleApiResponse>> getFluxApiResponse(
            @RequestBody  ReactiveExampleApiRequest requestBody);

}
