package com.nashtechglobal.model;


import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class representing the headers, request body, URL, and HTTP method for
 * an external API call.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveExampleApiRequest {

    /**
     * A map of headers to be included in the external API request.
     */
    private Map<String, String> headers;

    /**
     * The body of the external API request.
     */
    private ReactiveExampleBody body;

    /**
     * The URL of the external API endpoint to be called.
     */
    private String url;

    /**
     * The HTTP method to be used for the external API request, such as "GET".
     */
    private String httpMethod;

}
