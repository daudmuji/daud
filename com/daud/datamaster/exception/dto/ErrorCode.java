package com.daud.datamaster.exception.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    INVALID_DATA(1), INTERNAL_ERROR(2), ERROR_NETWORK(3), DATA_NOT_FOUND(4), OTHER_ERROR(5)
    ;

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int code() {
        return code;
    }
}
