package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import com.sicau.minordegreemanagement.facade.service.GraduationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private GraduationService graduationService;

    @GetMapping("/getGraduationInfo")
    @ApiOperation(value = "毕业审核", notes = "需要传递的参数 必须要的是 学院名称和审核状态")
    public Result<?> getGraduationInfo(@RequestParam String collegeName, String checkState){

        return null;
    }

    @GetMapping("/getPlan")
    @ApiOperation(value = "进度统计", notes = "不要参数")
    public Result<?> getPlanCount() {
        List<Map<Integer, Integer>> result = graduationService.getPlanCount();
        if (!result.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(result);
    }
}
