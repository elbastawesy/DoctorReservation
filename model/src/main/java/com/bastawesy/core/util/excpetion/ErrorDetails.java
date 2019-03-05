package com.bastawesy.core.util.excpetion;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

public class ErrorDetails implements Serializable {
    private Date timestamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    // 1 for success and 0 for fail
    private int requestStatus;
    private String message;
    private String details;

    public ErrorDetails(){}

    public ErrorDetails(Date timestamp, int httpStatusCode, HttpStatus httpStatus, int requestStatus, String messagee, String details) {
        this.timestamp = timestamp;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.requestStatus = requestStatus;
        this.message = messagee;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
