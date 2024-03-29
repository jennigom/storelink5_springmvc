package com.storelink5.core.exception;

import lombok.Getter;

/**
 * Service 관련 Exception 처리
 * @author JENNI
 * @version 1.0
 * @since 2022.05.19
 */

@Getter
public class _ServiceException extends Exception {
    private MessageCode messageCode;

    public _ServiceException(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public _ServiceException(MessageCode messageCode, String[] status_message) {
        messageCode.setStatus_message(status_message);
        this.messageCode = messageCode;
    }
}
