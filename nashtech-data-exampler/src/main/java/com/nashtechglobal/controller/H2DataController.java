package com.nashtechglobal.controller;

import com.nashtechglobal.model.EntityModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@OpenAPIDefinition(info = @Info(
    title = "H2DataExampler API Document",
    version = "1.0.0",
    description = "Nashtech H2Data Controller is a module"
        + " of the Nashtech Java Core Controller, which will help you to "
        + "understand.\n how you can use NashTech data Core."))
@Tag(name = "NashTech Data Controller",
    description = "NashTech H2 data REST API endpoints")
@RequestMapping("/api/v1/h2")
public interface H2DataController {

    /**
     * Retrieves an EntityModel with the given test ID from the H2 database.
     *
     * @param testId The test ID of the EntityModel to retrieve.
     * @return A ResponseEntity containing the EntityModel
     * and an HTTP status code.
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
                    = EntityModel.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

   @GetMapping("/getResult/{testId}")
    ResponseEntity<EntityModel> getEntityByTestId(
       @PathVariable("testId") long testId);

    /**
     * Retrieves all EntityModels from the H2 database.
     *
     * @return A ResponseEntity containing a list of
     * EntityModels and an HTTP status code.
     */

    @Operation(summary = "To get all EntityModels Results",
        description = """
                This endpoint retrieves results from the external APIs from
                the H2 database , the request body containing input data\s
                for the APIs, return the response body
                containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityModel.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @GetMapping("/getResults")
    ResponseEntity<List<EntityModel>> getAllEntity();

    /**
     * Adds an EntityModel to the H2 database.
     *
     * @param entityModel The EntityModel to add.
     * @return A ResponseEntity with an HTTP status code.
     */

    @Operation(summary = "To save the entity model to the database",
        description = """
            This endpoint saves the entity model from the external APIs to the\s
            H2 database, the request body containing input data for the APIs,
            response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityModel.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @PostMapping("/addResult")
    ResponseEntity<EntityModel> addEntity(
        @RequestBody EntityModel entityModel);

    /**
     * Updates an existing EntityModel in the H2 database.
     *
     * @param entityModel The updated EntityModel.
     * @return A ResponseEntity containing the updated
     * EntityModel and an HTTP status code.
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
                    = EntityModel.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @PutMapping("/updateResult/{testId}")
    ResponseEntity<EntityModel> updateEntity(
        @RequestBody EntityModel entityModel);

    /**
     * Deletes an existing EntityModel from the H2 database.
     *
     * @param testId The test ID of the EntityModel to delete.
     * @return A ResponseEntity with an HTTP status code.
     */

    @Operation(summary = "Delete an existing entity model from the H2 database",
        description = """
            This endpoint deletes the existing entity model from the external\s
            APIs to the H2 database, the request body containing input data,
            for the APIs response body containing results from the APIs.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Found the ExampleBody",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation
                    = EntityModel.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "not found",
            content = @Content)})

    @DeleteMapping("/deleteResult/{testId}")
    ResponseEntity<Void> deleteEntity(
        @PathVariable("testId") long testId);
}
