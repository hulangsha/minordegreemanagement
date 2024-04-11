package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/course")
@Api("课程管理模块")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourseList")
    @ApiOperation(value = "所有课程查询", notes = "无需任何参数")
    public Result<?> getCourseInfoList() {
        List<Course> courseList = courseService.getCourseInfoList();
        JSONObject resultJSON = new JSONObject();
        if (courseList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().put(courseList);
    }

//    @GetMapping("/get")
}
