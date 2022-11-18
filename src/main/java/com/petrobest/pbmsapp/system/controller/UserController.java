package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.FileResult;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.common.utils.FileuploadUtils;
import com.petrobest.pbmsapp.common.utils.ShiroUtils;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.domain.UserRoleDO;
import com.petrobest.pbmsapp.system.exception.BadUserParametersException;
import com.petrobest.pbmsapp.system.exception.InconsistentPassword;
import com.petrobest.pbmsapp.system.exception.IncorrectOldPasswordException;
import com.petrobest.pbmsapp.system.exception.UserNotFoundException;
import com.petrobest.pbmsapp.system.service.ResourceService;
import com.petrobest.pbmsapp.system.service.RoleService;
import com.petrobest.pbmsapp.system.service.UserRoleService;
import com.petrobest.pbmsapp.system.service.UserService;
import com.petrobest.pbmsapp.system.vo.UserPasswordVO;
import com.petrobest.pbmsapp.system.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Value("${portal.apiPrefix}")
    private String apiPrefix;

    @Value("${portal.avatarPath}")
    private String avatarPath;

    /**
     * 新增
     */
    @Log("新增用户")
    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public Object save(@RequestBody UserDO user) {
        log.info(user.toString());
        UserDO userDO = userService.getOne(new QueryWrapper<UserDO>().eq(UserDO.USERNAME, user.getUsername()));
        if (userDO != null) {
            return ResponseBo.error("用户名已存在！");
        }
        boolean save = userService.saveUser(user);
        boolean saveUserRole = userRoleService.saveUserRole(user.getUserId(), user.getRoleIds());
        if (save && saveUserRole) {
            return ResponseBo.ok("保存用户成功！");
        }
        return ResponseBo.error("保存用户失败！");

    }

    /**
     * 修改
     */
    @Log("修改用户")
    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public Object update(@RequestBody UserDO user) {
        boolean save = userService.updateById(user);
        boolean saveUserRole = userRoleService.saveUserRole(user.getUserId(), user.getRoleIds());
        if (save && saveUserRole) {
            return ResponseBo.ok("修改用户成功");
        }
        return ResponseBo.error("修改用户失败");
    }

    /**
     * 修改个人资料
     */
    @Log("修改个人资料")
    @PostMapping("/updateProfile")
    @ResponseBody
    @Transactional
    public Object updateProfile(@RequestBody UserDO user) {
        boolean update = userService.updateById(user);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @Log("删除用户")
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public Object delete(@RequestBody UserDO userDo) {
        boolean delete = userService.removeById(userDo.getUserId());
        boolean removeUserRole = userRoleService.remove(new QueryWrapper<UserRoleDO>().eq(UserDO.USER_ID, userDo.getUserId()));
        if (delete && removeUserRole) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 批量删除
     */
    @Log("批量删除用户")
    @PostMapping("/deleteBatch")
    @ResponseBody
    @Transactional
    public Object deleteBatch(@RequestBody Map<String, List<Long>> params) {
//        System.out.println(params.get("userIds"));
        Set<Map.Entry<String, List<Long>>> entries = params.entrySet();
        Iterator<Map.Entry<String, List<Long>>> it = entries.iterator();
        List<Long> userIds = it.next().getValue();

        if (userIds == null || userIds.size() <= 0) {
            return ResponseBo.error("参数有误！");
        }
        //删除用户及其关联的角色
        boolean deleteBatch = userService.removeByIds(userIds);
        boolean removeUserRole = userRoleService.remove(new QueryWrapper<UserRoleDO>().in(UserDO.USER_ID, userIds));
        if (deleteBatch && removeUserRole) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
//        return ResponseBo.ok("删除成功");
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    @ResponseBody
    public Object find(@RequestBody UserDO userDo) {
        UserDO user = userService.getOne(new QueryWrapper<UserDO>(userDo));
        if (user != null) {

            List<String> roleIds = userRoleService.listRoleIds(userDo.getUserId());
            user.setRoleIds(roleIds);

//            List<RoleDO> roles = roleService.listRolesByUserId(userDo.getUserId());
//            user.setRoles(roles);

            return ResponseBo.ok(user);
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestBody UserVO userVO) {
        log.info(userVO.toString());
        //分页构造器
        Page<UserDO> page = new Page<>(userVO.getCurrPage(), userVO.getPageSize());
        //条件构造器
        QueryWrapper<UserDO> queryWrapperw = new QueryWrapper<>();
        if (userVO.getEntity().getUsername() != null) {
            queryWrapperw.likeRight(UserDO.USERNAME, userVO.getEntity().getUsername());
        }
        //执行分页
        IPage<UserDO> pageList = userService.page(page, queryWrapperw);
        //返回结果
        return ResponseBo.ok(pageList);
    }


    @PostMapping("/listAll")
    @ResponseBody
    public Object listAll(@RequestBody UserVO userVO) {
        Page<UserDO> page = new Page<>(userVO.getCurrPage(), userVO.getPageSize());
        log.info(userVO.getEntity().toString());
        IPage<Map<String, Object>> list = userService.listUserWithRole(page, userVO.getEntity());
        return ResponseBo.ok(list);
    }

    @PostMapping("/listUserWithRoleDO")
    @ResponseBody
    public Object listUserWithRoleDO(@RequestBody UserVO userVO) {
        Page<UserDO> page = new Page<>(userVO.getCurrPage(), userVO.getPageSize());
        log.info(userVO.getEntity().toString());
        IPage<UserDO> list = userService.listUserWithRoleDO(page, userVO.getEntity());
        return ResponseBo.ok(list);
    }

    @PostMapping("/getInfo")
    @ResponseBody
    public Object getInfo(@RequestBody UserDO userDO) {
        Map<String, Object> resultData = new HashMap<>();

        UserDO user = userService.getOne(new QueryWrapper<UserDO>().eq(UserDO.USERNAME, userDO.getUsername()));
        List<String> roles = roleService.listRolesRemarkByUsername(user.getUsername());
        List<String> perms = resourceService.listPermsByUsername(user.getUsername());

        resultData.put("username", user.getUsername());
        resultData.put("roles", roles);
        resultData.put("perms", perms);
        resultData.put("avatar", user.getAvatar());


        return ResponseBo.ok(resultData);
    }

    @PostMapping("/checkSession")
    @ResponseBody
    public Object checkSession(@RequestBody Map<String, String> params) {
        String id = params.get("id");
//        log.info(id);
        try {
            Session session = SecurityUtils.getSecurityManager().getSession(new DefaultSessionKey(id));
//            log.info(String.format("Method: checkSession - session: {}"), session.toString());
            return ResponseBo.ok(session);
        } catch (UnknownSessionException e) {
//            log.info(String.format("未找到Session:{}"), id);
            return ResponseBo.error();
        } catch (NullPointerException e1) {
            return ResponseBo.error();
        }
    }

    @PostMapping("/getSession")
    @ResponseBody
    public Object getSession() {
        Serializable token = SecurityUtils.getSubject().getSession().getId();
        return ResponseBo.ok(token);
    }

    @Log("修改密码")
    @PostMapping("/modifyPassword")
    @ResponseBody
    public Object modifyPassword(@RequestBody UserPasswordVO userPasswordVO) {
        log.info(String.format("用户{}修改密码为{}."), userPasswordVO.getUsername(), userPasswordVO.getNewPassword());
        try {
            userService.modifyUserPassword(userPasswordVO);
            log.info(String.format("用户{}修改密码成功."), userPasswordVO.getUsername());
            return ResponseBo.ok("修改密码成功！");
        } catch (BadUserParametersException e) {
            return ResponseBo.error("传入参数错误！");
        } catch (UserNotFoundException e0) {
            return ResponseBo.error("用户未找到！");
        } catch (IncorrectOldPasswordException e1) {
            return ResponseBo.error("旧密码错误！");
        } catch (InconsistentPassword e2) {
            return ResponseBo.error("新密码与确认密码不一致！");
        } catch (Exception ex) {
            return ResponseBo.error(ex.getMessage());
        }
    }

    /**
     * 方法描述：图片上传，只能给图片使用，其他文件调用会异常.
     * 创建时间：2018-10-19 14:10:32
     *
     * @param childFile 上传的父目录
     * @param extension 允许上传的文件后缀名
     */

//    @Log("修改头像")
    @PostMapping("/avatar")
    @ResponseBody
    public Object uploadImage(
            MultipartFile multipart,
            @RequestParam(value = "childFile", required = false, defaultValue = "") String childFile,
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension
    ) {
        try {
            FileResult fileResult = FileuploadUtils.saveImage(multipart, childFile, extension);
            UserDO user = ShiroUtils.getUser();

            StringBuilder sb = new StringBuilder();
            sb.append(apiPrefix).append(avatarPath).append(fileResult.getServerPath()); // 前端跨域请求映射url前缀+用户头像映射规则+服务器文件名

            //更新用户头像信息
            user.setAvatar(sb.toString());
            userService.updateById(user);

            return ResponseBo.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBo.error("更换头像失败");
        }
    }

}