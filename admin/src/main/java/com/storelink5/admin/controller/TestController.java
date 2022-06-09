package com.storelink5.admin.controller;

import com.storelink5.admin.base.BaseController1;
import com.storelink5.core.exception.ServiceExceptionNew;
import com.storelink5.core.response.ServiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController1 {
    @GetMapping("/v1/test/resp")
    public void actionGet(ServiceResponse sr) throws ServiceExceptionNew {
        System.out.println("called actionGet :");
        throw new ServiceExceptionNew("MEM_001");
    }

    @PostMapping("/v1/test/resp")
    public void actionPost() throws ServiceExceptionNew {
        System.out.println("called actionPost :");
        throw new ServiceExceptionNew("MEM_001");
    }
}
