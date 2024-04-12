package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.ClassTable;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.service.ClassTableService;
import com.sicau.minordegreemanagement.facade.service.UserService;
import com.sicau.minordegreemanagement.facade.vo.UserRolePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/class")
@Api(tags = "班级管理")
public class ClassTableController {

    @Autowired
    private ClassTableService classService;

    @Autowired
    private UserService userService;

    @GetMapping("/getClassInfo")
    @ApiOperation(value = "查询班级信息", notes = "不需要参数,此接口只能管理员和教师访问，如果学生访问那么就会报错 服务器出错")
    public Result<?> getClassInfo () {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return new Result<>().fail();
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<UserRolePermission> userRoleAndPermission = userService.getUserRoleAndPermission(user.getUserId());
        String roleCode = userRoleAndPermission.get(0).getRoleCode();
        List<ClassTable> classList = classService.getClassInfoList(roleCode, user.getUserId());
        if (classList.isEmpty()) {
            return new Result<>().fail().put(new JSONObject().put("msg", "没有查询到班级信息"));
        }
        return new Result<>().success().put(classList);
    }
}
