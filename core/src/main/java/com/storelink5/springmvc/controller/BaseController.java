package com.storelink5.springmvc.controller;

import com.storelink5.springmvc.exception.MessageCode;
import com.storelink5.springmvc.exception.ServiceException;
import com.storelink5.springmvc.response.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base Controller
 * @author JENNI
 * @version 1.0
 * @since 2022.05.17
 */

@RequiredArgsConstructor    // 생성자 주입
@Controller
public class BaseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BaseController: get");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BaseController: post");
        super.doPost(req, resp);
    }

    public ApiResponseModel getApiResponse(String success, String status_msg, Object result) {
        return ApiResponseModel.builder()
                .messageCode("")
                .message(status_msg)
                .result(result)
                .build();
    }

    public ApiResponseModel getApiResponse(String success, String message_code, String status_msg, Object result) {
        return ApiResponseModel.builder()
                .messageCode(message_code)
                .message(status_msg)
                .result(result)
                .build();
    }

    public ApiResponseModel getApiResponse(MessageCode messageCode, Object result) {
        return ApiResponseModel.builder()
                .messageCode(messageCode.getCode())
                .message(messageCode.getStatus_message())
                .result(result)
                .build();
    }

    public ApiResponseModel getApiResponse(Object result) {
        return ApiResponseModel.builder()
                .result(result)
                .build();
    }
}
