package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @GetMapping("/getMinorDegreeInfo")
    @ApiOperation(value = "辅修学位申请", notes = "不用参数,返回了两个集合，一个是可以选择的学院的集合，另一个是可以选择的专业的集合")
    public Result<?> getMinorDegreeInfo () {
        JSONObject resultJSON = null;
        try {
            resultJSON = studentService.getMinorDegreeInfo();
        } catch (Exception e) {
            log.info("什么报错：{}", e);
            throw new RuntimeException(e);
        }
        return new Result<>().success().put(resultJSON);
    }
}