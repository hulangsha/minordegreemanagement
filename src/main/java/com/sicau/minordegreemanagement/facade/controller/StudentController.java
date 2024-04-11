package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.service.StudentService;
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
@RequestMapping("/api/student")
@Api(tags = "学生模块")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudentInfo")
    @ApiOperation(value = "查询学生个人信息", notes = "查询学生个人信息，必须传入学生的id")
    public Result<?> getStudentInfo (@RequestParam("studentId") Integer studentId) {
        Student student = studentService.getStudentInfoById(studentId);
        if (null == student) {
            throw new RuntimeException("没有查询到学生个人信息");
        }
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("student", student);
        return new Result<>().success().put(resultJSON);
    }
}
