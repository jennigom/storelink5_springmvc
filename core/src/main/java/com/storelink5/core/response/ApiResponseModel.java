package com.storelink5.core.response;

import lombok.Builder;
import lombok.Data;

/**
 * api return 값을 담아 전달하는 model
 * @author JENNI
 * @version 1.0
 * @since 2022.05.09
 */

@Builder
@Data
public class ApiResponseModel {
    // 메세지 코드
    private String messageCode;

    // 응답 메세지
    private String message;

    // 날짜(시간)
    private String timeStamp;

    // 결과 데이터
    private Object result;
}

/*
성공 여부: default - true
메세지 코드
응답 메세지
타임스탬프(날짜)
결과데이터
 */