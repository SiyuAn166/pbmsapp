package com.petrobest.pbmsapp.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.petrobest.pbmsapp.common.config.ApplicationContextRegister;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import com.petrobest.pbmsapp.system.domain.RoleDO;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.service.ResourceService;
import com.petrobest.pbmsapp.system.service.RoleService;
import com.petrobest.pbmsapp.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDO user = (UserDO) principalCollection.getPrimaryPrincipal();
        String username = user.getUsername();
        String userId = user.getUserId();
        //角色
        List<RoleDO> roles = roleService.listRolesByUsername(username);
        Set<String> roleSet = roles.stream().map(RoleDO::getRoleName).collect(Collectors.toSet());
        //权限
        List<ResourceDO> resourcesList = resourceService.listByUserId(userId);
        Set<String> perms = resourcesList.stream().map(ResourceDO::getPerms).collect(Collectors.toSet());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(perms);

        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        //clearSession(username);//清理用户之前的session
        String password = new String((char[]) token.getCredentials());

        UserService userService = ApplicationContextRegister.getBean(UserService.class);
        UserDO user = userService.getOne(new QueryWrapper<UserDO>().eq("username", username));

        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(user, password, getName());
        return authorizationInfo;
    }


}
