package com.storelink5.core.exception;

import lombok.Data;

@Data
public class ServiceExceptionNew extends Exception {
    private String messageCode;
    private String message;

    public ServiceExceptionNew(String messageCode){
        this.messageCode = messageCode;
        this.message     = ServiceMessages.getMessage(messageCode);
    }
}
