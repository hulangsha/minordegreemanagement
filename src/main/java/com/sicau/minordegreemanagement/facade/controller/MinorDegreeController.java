package com.sicau.minordegreemanagement.facade.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.common.result.PageResult;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.MinorDegree;
import com.sicau.minordegreemanagement.facade.service.MinorDegreeService;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;
import com.sicau.minordegreemanagement.facade.vo.QueryMinorDegree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/api/minorDegree")
@Api(tags = "辅修学位管理")
public class MinorDegreeController {

    @Autowired
    private MinorDegreeService minorDegreeService;

    @PostMapping("/addMinorDegree")
    @ApiOperation(value = "辅修学位添加", notes = "不改变之前的接口，也就是学生点击申请的时候使用的接口，申请辅修学位之后，管理员会收到辅修学位申请，点击管理员同意之后就将所申请的辅修学位的信息加入到数据库,记得将辅修审核状态设置为1")
    public Result<?> addMinorDegree(@RequestBody MinorDegreeInfo minorDegreeInfo) {
        boolean result = minorDegreeService.addMinorDegree(minorDegreeInfo);
        if (result) {
            return new Result<>().success().put(result);
        }

        return new Result<>().fail();
    }

    @PostMapping("/minorCheckPage")
    @ApiOperation(value = "辅修学位审核查询", notes = "辅修学位审核查询，管理员才有权限，其他人没有权限，不需要参数")
    public Result<?> minorCheckPage() {
        List<Map<String, Object>> result = minorDegreeService.getMinorCheckPage();
        if (result.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(result);

    }

    @PostMapping("/minorCheck")
    @ApiOperation(value = "辅修申请审核", notes = "辅修申请审核只有管理员才有权限，审核的时候应该把")
    public Result<?> getMinorCheck(@RequestBody MinorDegreeInfo minorDegreeInfo) {
        boolean result = minorDegreeService.minorCheck(minorDegreeInfo);

        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }

    @DeleteMapping("/refuseMinorApply")
    @ApiOperation(value = "退回辅修申请", notes = "需要将该条记录的id传过来")
    public Result<?> refuseMinorApply(@RequestParam Integer minorDegreeId) {
        boolean result = minorDegreeService.refuseMinor(minorDegreeId);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }


}
