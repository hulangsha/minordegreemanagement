package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import com.sicau.minordegreemanagement.facade.service.GraduationService;
import com.sicau.minordegreemanagement.facade.vo.GraduationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "毕业审核", notes = "需要传递的参数 必须要的是 学院名称和审核状态,checkState只能是0,1；0合格，1不合格")
    public Result<?> getGraduationInfo(@RequestParam String collegeName, String checkState){
        List<Graduation> graduationList = graduationService.getGraduationInfo(collegeName, checkState);
        if (!graduationList.isEmpty()) {
            return new Result<>().success().put(graduationList);
        }
        return new Result<>().fail();
    }

    @GetMapping("/getPlan")
    @ApiOperation(value = "进度统计", notes = "不要参数")
    public Result<?> getPlanCount() {
        List<Student> result = graduationService.getPlanCount();
        if (result.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(result);
    }


    @PostMapping("/thesisCheck")
    @ApiOperation(value = "论文查重", notes = "论文查重，管理员权限，不需要参数")
    public Result<?> getThesisCheck() {
        List<Graduation> graduationList = graduationService.getThesisCheck();
        if (graduationList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(graduationList);
    }

    @PostMapping("/changeThesisState")
    @ApiOperation(value = "更改论文查重状态", notes = "只需要改变一个参数，其他的内容不变，repetitionState状态设置为0")
    public Result<?> getChangeThesisState(@RequestBody GraduationInfo graduationInfo) {
        boolean result = graduationService.changeThesisState(graduationInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }
}
