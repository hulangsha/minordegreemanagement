package com.sicau.minordegreemanagement.facade.controller;


import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.Notice;
import com.sicau.minordegreemanagement.facade.service.NoticeService;
import com.sicau.minordegreemanagement.facade.vo.NoticeInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/notice")
@Api(tags = "通知")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/noticePage")
    @ApiOperation(value = "查询通知", notes = "不需要参数")
    public Result<?> getNoticePage() {
        List<Notice> result = noticeService.getNoticePage();
        if (!result.isEmpty()) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }

    @PostMapping("/addNotice")
    @ApiOperation(value = "新增通知", notes = "除了Id和时间不用穿 其他的都需要")
    public Result<?> getNoticeAdd(@RequestBody NoticeInfo noticeInfo) {
        boolean result = noticeService.addNotice(noticeInfo);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();
    }
}
