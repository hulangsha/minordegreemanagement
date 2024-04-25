package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Remind;
import com.sicau.minordegreemanagement.facade.service.RemindService;
import com.sicau.minordegreemanagement.facade.vo.RemindInfo;
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
 * @since 2024-04-25
 */
@RestController
@RequestMapping("/api/remind")
@Api(tags = "提醒模块")
public class RemindController {

    @Autowired
    private RemindService remindService;

    @PostMapping("/remindThesis")
    @ApiOperation(value = "论文查重提醒", notes = "传入要提醒的内容，学生id必不可少,去数据库看每个字段的意思")
    public Result<?> getThesisRemind(@RequestBody RemindInfo remindInfo) {
        boolean result = remindService.addThesisRemind(remindInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }

    @PostMapping("/remindGraduation")
    @ApiOperation(value = "毕业审核不过提醒", notes = "传入要提醒的内容，学生id必不可少,去数据库看每个字段的意思")
    public Result<?> getGraduationRemind(@RequestBody RemindInfo remindInfo) {
        boolean result = remindService.addThesisRemind(remindInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }

    @GetMapping("/getRemindPage")
    @ApiOperation(value = "提醒查看", notes = "这个是学生的提醒，就比如说他又被提醒的内容，就可以看到毕业不达标或者论文重复")
    public Result<?> getRemindResult(@RequestParam Integer studentId) {
        List<Remind> resultList = remindService.queryRemindInfo(studentId);
        if (resultList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(resultList);
    }

    @GetMapping("/deleteRemind")
    @ApiOperation(value = "改变提醒状态", notes = "这个功能是学生被提醒之后修改了不足之处，以及论文查重之后修改自己的一个状态。也就是说将is_finish从0改为1其他不变")
    public Result<?> getRemindUpdate(@RequestBody RemindInfo remindInfo) {
        boolean result = remindService.updateThesisAndGraduationState(remindInfo);
        if (!result) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(result);
    }





}
