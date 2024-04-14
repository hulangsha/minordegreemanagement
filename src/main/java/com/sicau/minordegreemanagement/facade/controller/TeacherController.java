package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Teacher;
import com.sicau.minordegreemanagement.facade.service.TeacherService;
import com.sicau.minordegreemanagement.facade.vo.TeacherInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@RestController
@RequestMapping("/api/teacher")
@Api(tags = "教师管理模块")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeacherInfo")
    @ApiOperation(value = "查询教师个人信息", notes = "查询教师个人信息必须传入教师自己的id")
    public Result<?> getTeacherInfo (@RequestParam("teacherId") Integer id) {
        Teacher teacher = teacherService.getTeacherInfoById(id);
        if (null == teacher) {
            throw new RuntimeException("没有查到教师信息");
        }

        JSONObject resultJSON = new JSONObject();
        resultJSON.put("teacher", teacher);
        return new Result<>().success().put(resultJSON);

    }

    @PostMapping("/updateTeacher")
    @ApiOperation(value = "教师个人信息更新", notes = "传入需要更新的字段信息")
    public Result<?> getTeacherUpdate(@RequestBody TeacherInfo teacherInfo) {
        boolean result = teacherService.getUpdateTeacher(teacherInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }
}
