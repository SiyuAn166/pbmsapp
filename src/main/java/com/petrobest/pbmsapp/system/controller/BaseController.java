package com.petrobest.pbmsapp.system.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {

    //处理没权限的请求
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/403";
    }
}
