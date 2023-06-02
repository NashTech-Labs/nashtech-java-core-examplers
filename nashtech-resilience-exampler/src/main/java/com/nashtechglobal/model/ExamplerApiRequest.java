package com.nashtechglobal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 The ExamplerApiRequest class represents a
 request object for the Exampler API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamplerApiRequest {

    /**
     * The URL of the request.
     */
    private String url;

    /**
     * The HTTP method of the request (e.g. GET, POST, PUT, DELETE).
     */
    private String httpMethod;

    /**
     * The body of the request. This is an ExampleBody object.
     */
    private ExampleBody body;

    /**
     * The headers of the request.
     */
    private Map<String, String> headers;




}
