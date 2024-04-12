package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@RestController
@RequestMapping("/api/graduation")
@Api(tags = "毕业审核模块")
public class GraduationController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/getGraduationInfo")
    @ApiOperation(value = "毕业审核", notes = "需要传递的参数 必须要的是 学院名称和审核状态")
    public Result<?> getGraduationInfo(){
        return null;
    }
}
