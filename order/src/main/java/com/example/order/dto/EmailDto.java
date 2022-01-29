package com.example.order.dto;

public class EmailDto {
    private String toAddress;
    private String subject;
    private String message;
    private String userId;

    public EmailDto(String toAddress, String subject, String message,String userId) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
        this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
