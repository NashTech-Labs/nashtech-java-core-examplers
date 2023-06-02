package com.nashtech.observation.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@OpenAPIDefinition(info = @Info(
        title = "Observability API Document",
        version = "1.0-SNAPSHOT",
        description = "The observability module helps "
                + "the developer to quickly setup the trace and"
                + " metrics for the application."))
@Tag(name = "NashTech Observability",
        description = "NashTech Observability REST API endpoints")
@RequestMapping("/v1/observation")
public interface ObservationController {

    /**
     *  Observed method.
     * @param argument
     */
    @Operation(summary = "Endpoint to observe",
            description = "This endpoint when called is added"
                    + "to the handler for observation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the ExampleBody",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation
                                    = String.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @GetMapping("/endpoint/{arg}")
    void observerMethod(@PathVariable("arg") String argument);
}
