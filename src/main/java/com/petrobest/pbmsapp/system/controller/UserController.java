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
     * ??????
     */
    @Log("????????????")
    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public Object save(@RequestBody UserDO user) {
        log.info(user.toString());
        UserDO userDO = userService.getOne(new QueryWrapper<UserDO>().eq(UserDO.USERNAME, user.getUsername()));
        if (userDO != null) {
            return ResponseBo.error("?????????????????????");
        }
        boolean save = userService.saveUser(user);
        boolean saveUserRole = userRoleService.saveUserRole(user.getUserId(), user.getRoleIds());
        if (save && saveUserRole) {
            return ResponseBo.ok("?????????????????????");
        }
        return ResponseBo.error("?????????????????????");

    }

    /**
     * ??????
     */
    @Log("????????????")
    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public Object update(@RequestBody UserDO user) {
        boolean save = userService.updateById(user);
        boolean saveUserRole = userRoleService.saveUserRole(user.getUserId(), user.getRoleIds());
        if (save && saveUserRole) {
            return ResponseBo.ok("??????????????????");
        }
        return ResponseBo.error("??????????????????");
    }

    /**
     * ??????????????????
     */
    @Log("??????????????????")
    @PostMapping("/updateProfile")
    @ResponseBody
    @Transactional
    public Object updateProfile(@RequestBody UserDO user) {
        boolean update = userService.updateById(user);
        if (update) {
            return ResponseBo.ok("????????????");
        }
        return ResponseBo.error("????????????");
    }

    /**
     * ??????
     */
    @Log("????????????")
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public Object delete(@RequestBody UserDO userDo) {
        boolean delete = userService.removeById(userDo.getUserId());
        boolean removeUserRole = userRoleService.remove(new QueryWrapper<UserRoleDO>().eq(UserDO.USER_ID, userDo.getUserId()));
        if (delete && removeUserRole) {
            return ResponseBo.ok("????????????");
        } else {
            return ResponseBo.error("?????????????????????");
        }
    }

    /**
     * ????????????
     */
    @Log("??????????????????")
    @PostMapping("/deleteBatch")
    @ResponseBody
    @Transactional
    public Object deleteBatch(@RequestBody Map<String, List<Long>> params) {
//        System.out.println(params.get("userIds"));
        Set<Map.Entry<String, List<Long>>> entries = params.entrySet();
        Iterator<Map.Entry<String, List<Long>>> it = entries.iterator();
        List<Long> userIds = it.next().getValue();

        if (userIds == null || userIds.size() <= 0) {
            return ResponseBo.error("???????????????");
        }
        //?????????????????????????????????
        boolean deleteBatch = userService.removeByIds(userIds);
        boolean removeUserRole = userRoleService.remove(new QueryWrapper<UserRoleDO>().in(UserDO.USER_ID, userIds));
        if (deleteBatch && removeUserRole) {
            return ResponseBo.ok("????????????");
        } else {
            return ResponseBo.error("?????????????????????");
        }
//        return ResponseBo.ok("????????????");
    }

    /**
     * ??????
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
            return ResponseBo.error("?????????????????????");
        }
    }

    /**
     * ????????????
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestBody UserVO userVO) {
        log.info(userVO.toString());
        //???????????????
        Page<UserDO> page = new Page<>(userVO.getCurrPage(), userVO.getPageSize());
        //???????????????
        QueryWrapper<UserDO> queryWrapperw = new QueryWrapper<>();
        if (userVO.getEntity().getUsername() != null) {
            queryWrapperw.likeRight(UserDO.USERNAME, userVO.getEntity().getUsername());
        }
        //????????????
        IPage<UserDO> pageList = userService.page(page, queryWrapperw);
        //????????????
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
//            log.info(String.format("?????????Session:{}"), id);
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

    @Log("????????????")
    @PostMapping("/modifyPassword")
    @ResponseBody
    public Object modifyPassword(@RequestBody UserPasswordVO userPasswordVO) {
        log.info(String.format("??????{}???????????????{}."), userPasswordVO.getUsername(), userPasswordVO.getNewPassword());
        try {
            userService.modifyUserPassword(userPasswordVO);
            log.info(String.format("??????{}??????????????????."), userPasswordVO.getUsername());
            return ResponseBo.ok("?????????????????????");
        } catch (BadUserParametersException e) {
            return ResponseBo.error("?????????????????????");
        } catch (UserNotFoundException e0) {
            return ResponseBo.error("??????????????????");
        } catch (IncorrectOldPasswordException e1) {
            return ResponseBo.error("??????????????????");
        } catch (InconsistentPassword e2) {
            return ResponseBo.error("????????????????????????????????????");
        } catch (Exception ex) {
            return ResponseBo.error(ex.getMessage());
        }
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????.
     * ???????????????2018-10-19 14:10:32
     *
     * @param childFile ??????????????????
     * @param extension ??????????????????????????????
     */

//    @Log("????????????")
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
            sb.append(apiPrefix).append(avatarPath).append(fileResult.getServerPath()); // ????????????????????????url??????+????????????????????????+??????????????????

            //????????????????????????
            user.setAvatar(sb.toString());
            userService.updateById(user);

            return ResponseBo.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBo.error("??????????????????");
        }
    }

}