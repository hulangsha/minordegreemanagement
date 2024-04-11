package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.service.MinorDegreeService;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;
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
@RequestMapping("/api/minorDegree")
@Api(tags = "辅修学位管理")
public class MinorDegreeController {

    @Autowired
    private MinorDegreeService minorDegreeService;

    @PostMapping("/addMinorDegree")
    @ApiOperation(value = "辅修学位添加", notes = "申请辅修学位之后，点击确定就将所申请的辅修学位的信息加入到数据库")
    public Result<?> addMinorDegree(@RequestBody MinorDegreeInfo minorDegreeInfo) {
        boolean result = minorDegreeService.addMinorDegree(minorDegreeInfo);
        if (result) {
            return new Result<>().success().put(result);
        }

        return new Result<>().fail();
    }

}
