package com.mongodb.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test_db")
@Builder
public class EntityModel {
    /**
     * The testId is a unique identifier for the test.
     */
    @Id
    private int testId;
    /**
     * The testUserId is the identifier for the user who took the test.
     */
    @Field("testUserId")
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
