package com.nashtechglobal.controller;


import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@OpenAPIDefinition(info = @Info(
    title = "Reactive Mongo Data Exampler API Document",
    version = "1.0-SNAPSHOT",
    description = "Nashtech Reactive Mongo Data Exampler is a module"
        + " of the Nashtech Java Core Exampler, which will help you to "
        + "understand.\n how you can use NashTech data Core."))
@Tag(name = "NashTech Reactive Mongo Data Exampler",
    description = "NashTech Reactive Mongo data REST API endpoints")

@RequestMapping("/reactive/entities")
public interface ReactiveEntityController {

    /**
     * Saves a ReactiveEntity in the database.
     *
     * @param reactiveEntityRequest a ReactiveEntity object to be saved.
     * @return a Mono of ReactiveEntity which
     * represents the saved ReactiveEntity.
     */

    @Operation(
            summary = "To save the entity model to the Reactive Mongo database",
        description = """
            This endpoint saves the entity model from the external APIs to the\s
            Reactive Mongo DB, the request body containing input data for the,
            APIs response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = ReactiveEntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Mono<ReactiveEntityResponse> saveReactiveEntity(
        @RequestBody ReactiveEntityRequest reactiveEntityRequest);

    /**
     * Retrieves a ReactiveEntity by its ID.
     *
     * @param reactiveEntityId the ID of the ReactiveEntity to retrieve.
     * @return a Mono of ReactiveEntity which represents
     * the retrieved ReactiveEntity.
     */

    @Operation(summary = "To get the response entity model",
        description = """
                    This endpoint retrieves EntityModel from the external APIs
                    with provided test ID and the request\s
                    body containing input data for the APIs,\s
                    response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = ReactiveEntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping("/{id}")
    Mono<ReactiveEntityResponse> getReactiveEntityById(
        @PathVariable("id") String reactiveEntityId);

    /**
     * Retrieves all ReactiveEntity objects in the database.
     *
     * @return a Flux of ReactiveEntity which represents
     * all the ReactiveEntity objects.
     */

    @Operation(summary = "To get all EntityModels Results",
        description = """
                This endpoint retrieves results from the external APIs from
                the Reactive Mongo DB, the request body containing input data \s
                for the APIs, return the response body
                containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = ReactiveEntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping
    Flux<ReactiveEntityResponse> getAllReactiveEntities();
    /**
     * Updates a ReactiveEntity with a given ID.
     *
     * @param reactiveEntityRequest ReactiveEntity object with updated fields.
     * @param reactiveEntityId the ID of the ReactiveEntity to update.
     * @return a Mono of ReactiveEntity which represents the
     * updated ReactiveEntity.
     */

    @Operation(summary = "To update the existing entity model to the database",
        description = """
            This endpoint updated the existing entity model from the external \s
            APIs to the H2 database, the request body containing input data
            for the APIs response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = ReactiveEntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @PutMapping("/{id}")
    Mono<ReactiveEntityResponse> updateReactiveEntityById(
        @RequestBody ReactiveEntityRequest reactiveEntityRequest,
        @PathVariable("id") String reactiveEntityId);

    /**
     * Deletes a ReactiveEntity with a given ID.
     *
     * @param reactiveEntityId the ID of the ReactiveEntity to delete.
     * @return a Mono of Void which represents an empty
     * response with HTTP status code 204.
     */

    @Operation(summary = "Delete an existing entity model from "
            + "the Reactive Mongo DB",
        description = """
            This endpoint deletes the existing entity model from the external \s
            APIs to the Reactive Mongo DB, the request body containing \s
            input data, for the APIs response body containing results \s
            from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = ReactiveEntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @DeleteMapping("/{id}")
    Mono<Void> deleteReactiveEntityById(
        @PathVariable("id") String reactiveEntityId);
}
