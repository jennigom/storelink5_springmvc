package com.storelink5.core.exception;

import lombok.Data;

@Data
public class ServiceException extends Exception {
    private String messageCode;
    private String message;

    public ServiceException(String messageCode){
        this.messageCode = messageCode;
        this.message     = ServiceMessages.getMessage(messageCode);
    }
}
