package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.CourseSelection;
import com.sicau.minordegreemanagement.facade.service.CourseSelectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/courseSelection")
@Api(tags = "网上选课")
public class CourseSelectionController {

    @Autowired
    private CourseSelectionService courseSelectionService;

    @GetMapping("/getInfo")
    @ApiOperation(value = "网上选课信息", notes = "需要将自己的id传入")
    public Result<?> getCourseSelection(@RequestParam Integer userId) {
        List<Course> courseSelectionList = courseSelectionService.getCourseSelection(userId);
        return null;
    }
}
