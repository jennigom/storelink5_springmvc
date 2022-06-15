package com.storelink5.core.controller;

import com.storelink5.core.exception.MessageCode;
import com.storelink5.core.exception.ServiceException;
import com.storelink5.core.response.ApiResponseModel;
import com.storelink5.core.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Base Controller
 * @author JENNI
 * @version 1.0
 * @since 2022.05.17
 */

@RequiredArgsConstructor    // 생성자 주입
@Controller
public class BaseController extends HttpServlet {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BaseController: service");
        System.out.println(getBody(req));
        actionService(req, resp);
    }

    private ServiceResponse actionService(HttpServletRequest req, HttpServletResponse resp) {
        ServiceResponse serviceResponse = new ServiceResponse();

        try {
            // request 에서 파라미터 꺼내오기
            System.out.println(">>>>>>>>>>" + req.getMethod() + ":" + req.getPathInfo());
            if (req.getMethod().equals(METHOD_GET)) {
                actionGet();
            } else if (req.getMethod().equals(METHOD_POST)) {
                actionPost();
            }

            System.out.println("actionService finished.");
        } catch (ServiceException se) {
            se.printStackTrace();
            serviceResponse.setMessageCode(se.getMessageCode());
        } catch (Exception e) {
            serviceResponse.setMessageCode("ERR-001");
        }

        return serviceResponse;
    }

    public void actionGet() throws ServiceException {
        System.out.println("called super actionGet");
    };

    public void actionPost() throws ServiceException {
        System.out.println("called super actionPost");
    };

    public static String getBody(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        return builder.toString();
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
