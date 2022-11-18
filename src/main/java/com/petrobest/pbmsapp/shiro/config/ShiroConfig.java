package com.petrobest.pbmsapp.shiro.config;

import com.petrobest.pbmsapp.shiro.filter.EnhanceUserFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroConfig {
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private ShiroProperties shiroProperties;
    @Value("${spring.redis.timeout}")
    private int redisTimeout;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //获取filters
        //shiroFilterFactoryBean.getFilterChainDefinitionMap() //拦截url的配置，配置什么url交给什么拦截器处理
        //shiroFilterFactoryBean.getFilters();//shiro的拦截器集合，如果需要自定义拦截规则，可以继承默认拦截器，重写方法实现

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
//        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
//        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("user", new EnhanceUserFilter()); //支持跨域options请求

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        Map<String, List<String>> filterMap = shiroProperties.getFilter();
//        System.out.println(filterMap);
        filterMap.forEach((filter, urls) -> {
            urls.forEach(url -> {
                filterChainDefinitionMap.put(url, filter);
            });
        });

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器(密码需要加密时，可使用)
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法 Md5Hash
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列加密次数 如：2=md5(md5(aaa))
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public SecurityManager securityManager(
            AuthorizingRealm authorizingRealm,
            SessionManager sessionManager,
            RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authorizingRealm);
        // 自定义的Session管理
        securityManager.setSessionManager(sessionManager);
        // 自定义的缓存实现
        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }

    /**
     * 自定义的SessionManager
     *
     * @param redisSessionDAO
     * @return
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setGlobalSessionTimeout(shiroProperties.getGlobalSessionTimeout() * 60 * 1000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);// 去掉每次登出之后 URL中自动添加的JSESSIONID

        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPort(redisProperties.getPort());
        redisManager.setTimeout(redisTimeout);
        if (!ObjectUtils.isEmpty(redisProperties.getPassword())) {
            redisManager.setPassword(redisProperties.getPassword());
        }
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @param redisManager
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @param redisManager
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
