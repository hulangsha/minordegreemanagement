package com.sicau.minordegreemanagement.facade.controller;

import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.common.utils.ExcelUtils;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import com.sicau.minordegreemanagement.facade.vo.GradeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/import")
@Api(tags = "导入模块")
@Slf4j
public class ImportGrade {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/gradeImport")
    @ApiOperation(value = "学生成绩导入", notes = "选择文件上传即可，仅限于excel文件")
    public Result<?> getGradeImport(@RequestParam("file") MultipartFile file) {
        List<Grade> gradeList = gradeService.importStudentGrade(file);
        boolean result = gradeService.addStudentGrade(gradeList);
        if (result) {
            return new Result<>().success().put(result);
        }
        return new Result<>().fail();

    }
}
