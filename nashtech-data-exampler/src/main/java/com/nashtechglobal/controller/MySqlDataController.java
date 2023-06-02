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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@OpenAPIDefinition(info = @Info(
    title = "MySqlData Controller API Document",
    version = "1.0-SNAPSHOT",
    description = "Nashtech MySql Controller is a module"
        + " of the Nashtech Java Core Exampler, which will help you to "
        + "understand.\n how you can use NashTech data Core."))
@Tag(name = "NashTech MySql Data Controller",
    description = "NashTech MySql data REST API endpoints")

@RequestMapping("/api/v1/mysql")
public interface MySqlDataController {

    /**
     * Retrieves all EntityModels from the MySQL database.
     *
     * @return a {@code ResponseEntity<List<EntityModel>>} containing
     * a list of all EntityModels and an HTTP status of 200 OK.
     */

    @Operation(summary = "To get all EntityModels Results",
        description = """
                This endpoint retrieves results from the external APIs from
                MySql database , the request body containing input data\s
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
    ResponseEntity<List<EntityModel>> getAllEntityModel();

    /**
     * Retrieves an EntityModel from the MySQL database by its ID.
     *
     * @param testId the ID of the EntityModel to retrieve.
     * @return a {@code ResponseEntity<EntityModel>} containing
     * the retrieved EntityModel and an HTTP status of 200 OK.
     * @throws Exception if the EntityModel is not found in the database.
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
    ResponseEntity<EntityModel> getEntityModelById(
        @PathVariable Long testId);

    /**
     * Creates a new EntityModel in the MySQL database.
     *
     * @param entityModel the EntityModel to create.
     * @return a {@code ResponseEntity<EntityModel>} containing
     * the created EntityModel and an HTTP status of 201 CREATED.
     */

    @Operation(summary = "To save the entity model to the database",
        description = """
            This endpoint saves the entity model from the external APIs to the\s
            MySql database, the request body containing input data for the APIs,
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
    ResponseEntity<EntityModel> createEntityModel(
        @RequestBody  EntityModel entityModel);

    /**
     * Updates an existing EntityModel in the MySQL database.
     *
     * @param entityModel the EntityModel to update.
     * @return a {@code ResponseEntity<EntityModel>} containing the
     * updated EntityModel and an HTTP status of 200 OK.
     */

    @Operation(summary = "To update the existing entity model to the database",
        description = """
            This endpoint updated the existing entity model from the external \s
            APIs to the MySql database, the request body containing input data
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
    ResponseEntity<EntityModel> updateEntityModel(
        @RequestBody  EntityModel entityModel);

    /**
     * Deletes an existing EntityModel
     * from the MySQL database.
     *
     * @param testId the ID of the EntityModel to delete.
     * @return an HTTP status of 200 OK.
     */

    @Operation(summary = "Delete an existing entity model from the MySql "
        + "database",
        description = """
            This endpoint deletes the existing entity model from the external\s
            APIs to the MySql database, the request body containing input data,
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
    HttpStatus deleteEntityModel(@PathVariable Long testId);

}



