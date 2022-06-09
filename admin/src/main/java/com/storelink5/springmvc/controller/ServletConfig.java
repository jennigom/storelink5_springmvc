package com.storelink5.springmvc.controller;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class ServletConfig {

    @Bean
    public ServletRegistrationBean getTestController()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new TestController());
        registrationBean.addUrlMappings("/v1/test/resp");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getTestController1()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new TestController1());
        registrationBean.addUrlMappings("/v2/test/resp");

        return registrationBean;
    }
}
