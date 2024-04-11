package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.service.ClassService;
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
@RequestMapping("/api/class")
@Api(tags = "班级管理")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/getClassInfo")
    @ApiOperation(value = "查询班级信息", notes = "不需要任何参数")
    public Result<?> getClassInfo () {
        List<Class> classList = classService.getClassInfoList();
        return null;
    }
}
