package com.storelink5.springmvc.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceMessages {
    private static ServiceMessages serviceMessages;
    private static Map<String, String> messges = new HashMap<String, String>();

    private ServiceMessages() {
        messges.put("MEM_001", "회원 정보가 없습니다. 확인 후 재시도 바랍니다");
        messges.put("MEM_002", "회원 ID가 중복입니다.");
    }

    public static String getMessage(String messageCode) {
        if (serviceMessages == null ) serviceMessages = new ServiceMessages();
        return messges.get(messageCode);
    }
}
