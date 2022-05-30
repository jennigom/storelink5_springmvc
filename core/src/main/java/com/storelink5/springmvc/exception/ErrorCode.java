package com.storelink5.springmvc.exception;

import lombok.Getter;

/**
 * Error Code
 * @author JENNI
 * @version 1.0
 * @since 2022.05.19
 */

@Getter
// MessageCode
public enum ErrorCode {
    회원가입("100", "회원가입에 실패하였습니다."),
    회원가입_중복("101", ""),
    로그인_ID("200", "가입되지 않은 Id 입니다."),
    로그인_PASSWORD("300", "잘못된 비밀번호입니다."),
    테스트("999", "테스트");

    private String code;
    private String status_message;

    ErrorCode(String code, String status_message) {
        this.code = code;
        this.status_message = status_message;
    }

    // 어떤 식으로 처리를 할지 / array / python - list
    // enum 아니고 class로 바꾸는 건 어떨지
    // 고민 연구
    public String getStatus_message(String status_message) {
        if(getCode().equals("101"))  status_message = "이미 중복된 " + status_message + " 입니다.";

        return status_message;
    }
}

/*
- 비지니스적 -
공통 CMM_100
회원 MEM_100
금액 PNT_100

- 찐 에러 -
심각 ERR_100
 */