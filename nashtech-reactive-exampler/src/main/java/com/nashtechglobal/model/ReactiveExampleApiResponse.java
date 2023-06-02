package com.nashtechglobal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Represents a request body for an external API response, including fields
 * for a unique identifier, string, long, and date data.
 */
@Data
public class ReactiveExampleApiResponse {

    /**
     * The identifier of the response.
     */
    private String id;

    /**
     * A string field used for testing purposes.
     */
    private String testingStringData;

    /**
     * A long field used for testing purposes.
     */
    private Long testingLongData;

    /**
     * A date field used for testing purposes.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "IST")
    private LocalDateTime testingDateData;

}
