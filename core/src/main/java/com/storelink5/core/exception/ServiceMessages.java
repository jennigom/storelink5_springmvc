package com.storelink5.core.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceMessages {
    private static ServiceMessages serviceMessages;
    private static Map<String, String> messges = new HashMap<String, String>();

    private ServiceMessages() {
        messges.put("MEM_001", "회원가입에 실패하였습니다.");
        messges.put("MEM_002", "회원 ID가 중복입니다.");
        messges.put("MEM_003", "회원 주민등록번호가 중복입니다.");
        messges.put("MEM_004", "비밀번호가 일치하지 않습니다.");
        messges.put("MEM_005", "일치하는 ID가 없습니다.");
    }

    public static String getMessage(String messageCode) {
        if (serviceMessages == null ) serviceMessages = new ServiceMessages();
        return messges.get(messageCode);
    }
}
