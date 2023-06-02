package com.mongodb.controller;

import com.mongodb.model.EntityRequest;
import com.mongodb.model.EntityResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@OpenAPIDefinition(info = @Info(
    title = "Mongo Data Exampler API Document",
    version = "1.0-SNAPSHOT",
    description = "Nashtech Mongo Data Exampler is a module"
        + " of the Nashtech Java Core Exampler, which will help you to "
        + "understand.\n how you can use NashTech data Core."))
@Tag(name = "NashTech Mongo Data Exampler",
    description = "NashTech Mongo data REST API endpoints")

@RequestMapping("/api/v1")
public interface MongoDataController {

    /**
     * Retrieves all EntityModel objects stored in the MongoDB database.
     * @return A ResponseEntity object containing a list
     * of EntityModel objects and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    @Operation(summary = "To get all EntityModels Results",
        description = """
                This endpoint retrieves results from the external APIs from
                the Mongo DB ,, the request body containing input data\s
                for the APIs, return the response body
                containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping("/getResults")
    ResponseEntity<List<EntityResponse>> getAllEntityModel();

    /**
     * Retrieves a single EntityModel object from the MongoDB
     * database based on its testId.
     * @param testId The ID of the EntityModel object to retrieve.
     * @return A ResponseEntity object containing the EntityModel
     * object and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    @Operation(summary = "To get the response entity model based on test id",
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
                    = EntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping("/getResult/{testId}")
    ResponseEntity<EntityResponse> getEntityModelByTestId(
        @PathVariable int testId);

    /**
     * Retrieves all EntityModel objects associated
     * with a specific testUserId.
     * @param testUserId The user ID of the EntityModel objects to retrieve.
     * @return A ResponseEntity object containing a list
     * of EntityModel
     * objects and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    @Operation(summary =
            "To get the response entity model based on User test id",
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
                    = EntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping("/getResult/find/{testUserId}")
    ResponseEntity<List<EntityResponse>>
    getEntityModelByUserTestId(@PathVariable("testUserId")
    String testUserId);
    /**
     * Inserts or updates an EntityModel object in the MongoDB database.
     * @param entityRequest The EntityRequest object to insert or update.
     * @return A ResponseEntity object containing the inserted
     * or updated EntityModel object and an HTTP status code.
     * The HTTP status code is set to 200 if the request was successful.
     */

    @Operation(summary = "To save the entity model to the database",
        description = """
            This endpoint saves the entity model from the external APIs to the\s
            Mongo DB, the request body containing input data for the APIs,
            response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @RequestMapping(path = "/upsertResult", method =
        {RequestMethod.PUT, RequestMethod.POST})
    ResponseEntity<EntityResponse>
    upsertEntityModel(@RequestBody EntityRequest entityRequest);

    /**
     * Deletes an EntityModel object from the MongoDB database
     * based on its testId.
     * @param testId The ID of the EntityModel object to delete.
     * @return An HTTP status code indicating the result
     * of the delete operation.
     * The HTTP status code is set to 200 if the delete
     * operation was successful.
     */

    @Operation(summary = "Delete an existing entity model from the Mongo DB",
        description = """
            This endpoint deletes the existing entity model from the external\s
            APIs to the Mongo DB, the request body containing input data,
            for the APIs response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @DeleteMapping("/deleteResult/{testId}")
    HttpStatus deleteEntityModel(@PathVariable int testId);

}
