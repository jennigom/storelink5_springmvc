package com.storelink5.core.exception;

import lombok.Getter;

/**
 * Message Code
 * @author JENNI
 * @version 1.0
 * @since 2022.05.24
 */

@Getter
public enum MessageCode {
    회원가입_실패("fail", "MEM_101", "회원가입에 실패하였습니다."),
    회원가입_성공("success", "MEM_100", "회원가입에 성공하였습니다."),
    회원가입_중복("fail", "MEM_102", "중복된 %s입니다."),
    로그인_성공("success", "MEM_110", "로그인에 성공하였습니다."),
    로그인_실패("fail", "MEM_111", "로그인에 실패하였습니다."),
    로그인_ID("fail", "MEM_102", "가입되지 않은 Id입니다."),
    로그인_PASSWORD("fail", "MEM_103", "잘못된 비밀번호입니다."),
    회원_정보_조회_성공("success", "MEM_104", "회원 정보 조회에 성공하였습니다."),
    회원_정보_조회_실패("fail", "MEM_105", "회원 정보 조회에 실패하였습니다."),
    회원_정보_EMPTY("success", "MEM_106", "조회된 회원이 없습니다."),
    TOKEN_유효성("fail", "ERR_100", "token이 유효하지 않습니다."),
    테스트("fail","TEST_999", "테스트");

    private String success;
    private String code;
    private String origin_message;
    private String status_message;

    MessageCode(String success, String code, String origin_message) {
        this.success = success;
        this.code = code;
        this.origin_message = origin_message;
        this.status_message = origin_message;
    }

    public void setStatus_message(String[] message) {
        status_message = String.format(getOrigin_message(), message);
    }
}