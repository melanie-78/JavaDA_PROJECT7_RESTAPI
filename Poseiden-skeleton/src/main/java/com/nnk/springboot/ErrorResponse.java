package com.nnk.springboot;

public class ErrorResponse {
    private String errorMessage;

    public ErrorResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
