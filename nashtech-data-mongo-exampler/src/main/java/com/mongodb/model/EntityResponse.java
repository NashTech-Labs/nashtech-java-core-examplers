package com.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EntityResponse {

    /**
     * The testUserId is the identifier for the user who took the test.
     */
    private String userId;
    /**
     * The testNo is the number assigned to the test.
     */
    private int testNo;
    /**
     * The testStatus represents the current status of the test,
     * such as "completed" or "in progress".
     */
    private String testStatus;
}
