package com.nashtechglobal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a reactive entity with a unique -
 * identifier, string, long, and date data fields,
 * The entity is having the collection name-
 * "ReactiveEntity_Collection" for identification purposes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "ReactiveEntity_Collection")
public class ReactiveEntity {
    /**
     * The unique identifier for the reactive entity.
     */
    @Id
    private String id;
    /**
     * A string representing testing data for the reactive entity.
     */
    private String testingStringData;
    /**
     * A long representing testing data for the reactive entity.
     */
    private Long testingLongData;
    /**
     * A date object representing testing data for the reactive entity.
     * This is formatted according to ISO 8601 standard.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "IST")
    private Date testingDateData;
}
