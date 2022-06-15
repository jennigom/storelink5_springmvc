package com.storelink5.admin.controller;

import com.storelink5.core.controller.BaseController;
import com.storelink5.core.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController1 extends BaseController {
    @GetMapping("/v2/test/resp")
    public void actionGet() throws ServiceException {
        System.out.println("called actionGet2 :");
        throw new ServiceException("MEM_001");
    }

    @PostMapping("/v2/test/resp")
    public void actionPost() throws ServiceException {
        System.out.println("called actionPost2 :");
        throw new ServiceException("MEM_001");
    }
}
