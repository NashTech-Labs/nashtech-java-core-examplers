package com.mongodb.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityRequest {
    /**
     * The testId is a unique identifier for the test.
     */
    private int testId;
    /**
     * The testUserId is the identifier for the user who took the test.
     */
    private String testUserId;
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
