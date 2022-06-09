package com.storelink5.springmvc.controller;

import com.storelink5.springmvc.base.BaseController1;
import com.storelink5.springmvc.exception.ServiceExceptionNew;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController1 extends BaseController1 {
    @GetMapping("/v2/test/resp")
    public void actionGet() throws ServiceExceptionNew {
        System.out.println("called actionGet2 :");
        throw new ServiceExceptionNew("MEM_001");
    }

    @PostMapping("/v2/test/resp")
    public void actionPost() throws ServiceExceptionNew {
        System.out.println("called actionPost2 :");
        throw new ServiceExceptionNew("MEM_001");
    }
}
