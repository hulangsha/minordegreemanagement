package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.service.GradeService;
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
@RequestMapping("/api/grade")
@Api(tags = "成绩管理模块")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/getGradeInfo")
    @ApiOperation(value = "查询成绩", notes = "需要传输自己的user_id")
    public Result<?> getGradeInfo (@RequestParam Integer userId) {
        List<Map<String, Object>> gradeList = gradeService.getGradeInfoList(userId);
        if (gradeList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(gradeList);
    }
}
