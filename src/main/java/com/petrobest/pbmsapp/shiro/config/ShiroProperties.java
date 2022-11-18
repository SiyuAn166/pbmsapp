package com.petrobest.pbmsapp.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "security.shiro")
public class ShiroProperties {
    /**
     * 登录Url
     */
    private String loginUrl;
    /**
     * 没权限访问时的转发Url(做未登录提示信息用)
     */
    private String unauthorizedUrl;
    /**
     * 登出url
     */
    private String logoutUrl;
    /**
     * 登录成功跳转
     */
    private String successUrl;
    /**
     * Shiro Session 过期时间（分钟）
     */
    private Long globalSessionTimeout = 30L;
    /**
     * Shiro请求拦截规则配置(Shiro的拦截器规则，常用的anon和authc)
     */
    private Map<String, List<String>> filter;

}
