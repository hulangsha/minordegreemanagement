package com.sicau.minordegreemanagement.facade.controller;

import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.common.utils.ExcelUtils;
import com.sicau.minordegreemanagement.facade.vo.GradeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/api/grade")
@Api(tags = "导入功能")
public class ImportFile {


    @PostMapping("/gradeImport")
    @ApiOperation(value = "成绩导入", notes = "传入文件的路径")
    public void studentGradeImport(@RequestParam("file") MultipartFile file) {
        List<GradeVo> gradeVoList = ExcelUtils.readExcel(file, GradeVo.class);
        gradeVoList.forEach(System.out::println);
    }

}
