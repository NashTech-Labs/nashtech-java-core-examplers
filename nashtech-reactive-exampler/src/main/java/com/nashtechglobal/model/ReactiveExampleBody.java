package com.nashtechglobal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request body with a unique identifier, string, long,
 * and date data fields.
 */
@Data
@NoArgsConstructor
public class ReactiveExampleBody {

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
    private Date testingDateData;
}
