package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.common.utils.HttpContextUtils;
import com.petrobest.pbmsapp.common.utils.MD5Utils;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;  //websocket推送客户端

    //    @Log("登录系统")
    @PostMapping("/doLogin")
    public Object login(@RequestBody UserDO user) {
        log.info(user.toString());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        try {
            clearSession(user.getUsername());//清理当前用户已登录的session，如果被清理，则会掉线

            // 登录
            subject.login(token);
            // 登录成功后，获取菜单权限信息
            if (subject.isAuthenticated()) {
                //设置最近登录时间
                user.setLastLoginTime(new Date());
                user.setPassword(null);// 去掉密码，否则会修改密码
                userService.update(user, new UpdateWrapper<UserDO>().eq(UserDO.USERNAME, user.getUsername()));

                Session session = subject.getSession();//登录session，作为token传给前台系统，校验是否登录已过期

                return ResponseBo.ok(session);
            }
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error("密码错误");
        } catch (LockedAccountException e) {
            return ResponseBo.error("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            return ResponseBo.error("该用户不存在");
        } catch (Exception e) {
            return e.getMessage();
        }
        return ResponseBo.ok("登录失败");
    }

    //    @Log("退出系统")
    @PostMapping("/doLogout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        redisSessionDAO.delete(subject.getSession()); //删除redis中的session纪录
        subject.logout();
//        log.info(String.format("用户{}退出登录。"), subject.getSession());
        return ResponseBo.ok("注销成功");
    }

    @GetMapping("/getActSessionNum")
    public Object getActiveSession() {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        return ResponseBo.ok(sessions.size());
    }

    private void clearSession(String username) {
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions) {
            SimplePrincipalCollection primaryPrinciple = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (ObjectUtils.allNotNull(primaryPrinciple)) {

                UserDO principal = (UserDO) primaryPrinciple.getPrimaryPrincipal();
                //清除该用户以前登录时保存的session
                if (ObjectUtils.allNotNull(principal) && username.equals(principal.getUsername())) {   //用户已登录
                    //通知前端被踢出
                    notifyKickout(session.getId().toString());

                    sessionManager.getSessionDAO().delete(session);
                }
            }
        }
    }

    private void notifyKickout(String user) {
        String destination = "/duplicateLogin";
        String payload = "您的账号已在另一设备登录，如非本人操作，请立即修改密码！";
        simpMessagingTemplate.convertAndSendToUser(user, destination, payload);
    }
}
