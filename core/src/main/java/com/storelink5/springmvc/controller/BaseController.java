package com.storelink5.springmvc.controller;

import com.storelink5.springmvc.exception.MessageCode;
import com.storelink5.springmvc.response.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * Base Controller
 * @author JENNI
 * @version 1.0
 * @since 2022.05.17
 */

@RequiredArgsConstructor    // 생성자 주입
@Controller
public class BaseController {
    public ApiResponseModel getApiResponse(String success, String status_msg, Object result) {
        return ApiResponseModel.builder()
                .success(success)
                .message_code("")
                .status_message(status_msg)
                .result(result)
                .build();
    }

    public ApiResponseModel getApiResponse(String success, String message_code, String status_msg, Object result) {
        return ApiResponseModel.builder()
                .success(success)
                .message_code(message_code)
                .status_message(status_msg)
                .result(result)
                .build();
    }

    public ApiResponseModel getApiResponse(MessageCode messageCode, Object result) {
        return ApiResponseModel.builder()
                .success(messageCode.getSuccess())
                .message_code(messageCode.getCode())
                .status_message(messageCode.getStatus_message())
                .result(result)
                .build();
    }
}
