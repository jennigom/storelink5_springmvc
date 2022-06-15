package com.storelink5.admin.base;

import com.storelink5.core.exception.ServiceException;
import com.storelink5.core.response.ServiceResponse;
import org.springframework.stereotype.Controller;

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

@Controller
public class _BaseController extends HttpServlet {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

//    public BaseController1(){
//        super();
//        System.out.println("BaseController1 created....");
//    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseController: get");
//        super.doGet(req, resp);
//        this.actionService(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseController: post");
//        //super.doPost(req, resp);
//        this.actionService(req, resp);
//    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("BaseController: service");
        actionService(req, resp);
    }

    private ServiceResponse actionService(HttpServletRequest req, HttpServletResponse resp) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try{
            System.out.println(">>>>>>>>>>"+req.getMethod()+":"+req.getPathInfo());
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

//        try {
//            PrintWriter writer = resp.getWriter();
//            writer.print(serviceResponse);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        return serviceResponse;
    }

    public void actionGet() throws ServiceException {
        System.out.println("called super actionGet");
    };

    public void actionPost() throws ServiceException {
        System.out.println("called super actionPost");
    };
}
