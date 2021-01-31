package com.guise.guiseproject.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Image type enum contains all the allowed image types
 */
public enum ImageTypeEnum {
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/png");

    private final String value;

    ImageTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
