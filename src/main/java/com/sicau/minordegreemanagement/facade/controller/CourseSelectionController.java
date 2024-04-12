package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.CourseSelection;
import com.sicau.minordegreemanagement.facade.service.CourseSelectionService;
import com.sicau.minordegreemanagement.facade.vo.CourseSelectionInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addCourseSelection")
    @ApiOperation(value = "添加选课", notes = "必须要课程id和学生id，也只需要这两个就够够了，学生id就是Userid")
    public Result<?> getCourseSelection(@RequestBody CourseSelectionInfo courseSelectionInfo) {
        boolean result = courseSelectionService.addCourseSelection(courseSelectionInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }
}
