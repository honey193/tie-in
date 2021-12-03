package com.example.tie_in.data;

public enum Status {
    LOADING("Loading"),
    SUCCESS("Success"),
    ERROR("Error");

    String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
