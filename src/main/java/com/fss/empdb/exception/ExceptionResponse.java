package com.fss.empdb.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private String httpCodeMessage;
    private int httpCode;
    public ExceptionResponse(Date timestamp, String message, String details, String httpCodeMessage,int httpCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpCodeMessage=httpCodeMessage;
        this.httpCode=httpCode;
    }
    public String getHttpCodeMessage() {
        return httpCodeMessage;
    }
    public int getHttpCode() {
        return httpCode;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
}