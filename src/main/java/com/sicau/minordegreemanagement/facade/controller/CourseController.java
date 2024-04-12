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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
@Api(tags = "课程管理模块")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourseList")
    @ApiOperation(value = "所有课程查询", notes = "无需任何参数")
    public Result<?> getCourseInfoList() {
        List<Map<String, Object>> courseList = courseService.getCourseInfoList();
        if (courseList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(courseList);
    }

    @GetMapping("/getTokenCourse")
    @ApiOperation(value = "已修读课程查询",notes = "需要将学生的id传进来，也就是user_id")
    public Result<?> getTokenCourseInfo(@RequestParam Integer userId) {
        List<Map<String, Object>> tokenCourseList = courseService.getTokenCourseInfo(userId);
        if (tokenCourseList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(tokenCourseList);
    }

    @GetMapping("/getUnTokenCourse")
    @ApiOperation(value = "未修读课程查询",notes = "需要将学生的id传进来，也就是user_id")
    public Result<?> getUnTokenCourseInfo(@RequestParam Integer userId) {
        List<Map<String, Object>> unTokenCourseList = courseService.getUnTokenCourseInfo(userId);
        if (unTokenCourseList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(unTokenCourseList);
    }

    @GetMapping("/getCourseSelectionInfo")
    @ApiOperation(tags = "网上选课", value = "网上选课信息", notes = "需要将自己的id传入")
    public Result<?> getCourseSelection(@RequestParam Integer userId) {
        List<Course> courseSelectionList = courseService.getCourseSelection(userId);
        if (courseSelectionList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(courseSelectionList);
    }


}
