package com.hr.ci.commons.model;

import com.squareup.moshi.Json;

public class ErrorResponse {


    @Json(name = "httpStatus")
    private int httpStatus;
    @Json(name = "status")
    private String status;
    @Json(name = "code")
    private String code;
    @Json(name = "message")
    private String message;


    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    private ErrorResponse(Builder builder) {
        this.httpStatus = builder.httpStatus;
        this.status = builder.status;
        this.code = builder.code;
        this.message = builder.message;
    }

    public static class Builder {
        private int httpStatus;
        private String status;
        private String code;
        private String message;

        public Builder httpStatus(int httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder fromPrototype(ErrorResponse prototype) {
            httpStatus = prototype.httpStatus;
            status = prototype.status;
            code = prototype.code;
            message = prototype.message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
