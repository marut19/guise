package com.guise.guiseproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Validation error provides with validation on specific fields and the error message for them.
 *
 * <p>Generates a no-args constructor. @NoArgsConstructor Annotation Will generate an error message
 * if such a constructor cannot be written due to the existence of final fields.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationError {

    private String field;
    private String code;
    private String message;
}