package com.storelink5.admin.controller;

import com.storelink5.core.dao.MemberDao;
import com.storelink5.core.security.JwtTokenProvider;
import com.storelink5.core.service.MemberService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class ServletConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final MemberDao memberDao;

    public ServletConfig(JwtTokenProvider jwtTokenProvider, MemberService memberService, MemberDao memberDao) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
        this.memberDao = memberDao;
    }

    @Bean
    public ServletRegistrationBean getRestApiController()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new RestApiController(jwtTokenProvider, memberService, memberDao));
        registrationBean.addUrlMappings("/v1/signup");
        registrationBean.addUrlMappings("/v1/login");
        registrationBean.addUrlMappings("/v1/me");

        return registrationBean;
    }

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
