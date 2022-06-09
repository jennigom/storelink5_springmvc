package com.storelink5.core.response;

import com.storelink5.core.exception.ServiceMessages;
import lombok.Data;

/**
 * api return 값을 담아 전달하는 model
 * @author JENNI
 * @version 1.0
 * @since 2022.05.09
 */

@Data
public class ServiceResponse {
    // 메세지 코드
    private String messageCode;

    // 응답 메세지
    private String message;

    // 날짜(시간)
    private String timeStamp;

    // 결과 데이터
    private Object result;

    public ServiceResponse() {

    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
        this.message     = ServiceMessages.getMessage(messageCode);
    }
}
