package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.CourseSessions;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.service.CourseSessionsService;
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
 * @since 2024-04-24
 */
@RestController
@RequestMapping("/api/courseSessions")
@Api(tags = "课程表模块")
public class CourseSessionsController {
    @Autowired
    private CourseSessionsService courseSessionsService;

    @GetMapping("/searchCreditTeacher")
    @ApiOperation(tags = "课程表模块", value = "学生查询课程", notes = "传入自己的id即可")
    public Result<?> getSearchSession(@RequestParam("studentId") Integer studentId) {
        List<CourseSessions> studentInfo = courseSessionsService.getStudentSession(studentId);
        if (!studentInfo.isEmpty()) {
            return new Result<>().success().put(studentInfo);
        }
        return new Result<>().fail();
    }
}
