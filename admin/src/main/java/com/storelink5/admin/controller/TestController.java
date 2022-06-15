package com.storelink5.admin.controller;

import com.storelink5.core.controller.BaseController;
import com.storelink5.core.exception.ServiceException;
import com.storelink5.core.response.ServiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
    @GetMapping("/v1/test/resp")
    public void actionGet(ServiceResponse sr) throws ServiceException {
        System.out.println("called actionGet :");
        throw new ServiceException("MEM_001");
    }

    @PostMapping("/v1/test/resp")
    public void actionPost() throws ServiceException {
        System.out.println("called actionPost :");
        throw new ServiceException("MEM_001");
    }
}
