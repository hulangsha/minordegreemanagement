package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.component.JwtUtil;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.service.UserService;
import com.sicau.minordegreemanagement.facade.vo.UserInfo;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "登录接口必须传login_name和password")
    public Result<?> login (@RequestBody UserInfo userInfo) {
        User user = userService.getUserInfo(userInfo);
        if (null == user) {
            throw new RuntimeException("没有查询到用户，输入不正确");
        }
        String token = jwtUtil.sign(user.getUserName(), user.getPassword());
        List<UserRolePermission> userRolePermissionList = userService.getUserRoleAndPermission(user.getUserId());
        String roleCode = userRolePermissionList.get(0).getRoleCode();
        String permissionUrl = userRolePermissionList.get(0).getPermissionUrl();
        String roleName = userRolePermissionList.get(0).getRoleName();
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("user", user);
        resultJSON.put("token", token);
        resultJSON.put("roleCode", roleCode);
        resultJSON.put("roleName", roleName);
        resultJSON.put("permissionUrl", permissionUrl);
        return new Result<>().success().put(resultJSON);
    }

}
