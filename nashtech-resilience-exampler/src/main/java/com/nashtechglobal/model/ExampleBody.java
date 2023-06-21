package com.nashtechglobal.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 This class represents the request body for an example API.
 */
@Data
@NoArgsConstructor
public class ExampleBody {

    /**

     The ID of the example object.
     */
    private String id;

    /**

     The name of the example object.
     */
    private String name;

}
