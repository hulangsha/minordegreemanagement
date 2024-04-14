package com.sicau.minordegreemanagement.facade.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.sicau.minordegreemanagement.common.result.Result;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.service.CourseService;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.*;


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

    @Autowired
    private CourseService courseService;

    @GetMapping("/getGradeInfo")
    @ApiOperation(value = "查询成绩", notes = "需要传输自己的user_id")
    public Result<?> getGradeInfo (@RequestParam Integer userId) {
        List<Map<String, Object>> gradeList = gradeService.getGradeInfoList(userId);
        if (gradeList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(gradeList);
    }

    @GetMapping("/getClassGrade")
    @ApiOperation(tags = "班级管理", value = "查询成绩", notes = "不需要参数")
    public Result<?> getClassGradeInfo () {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return new Result<>().fail();
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Map<String, Object>> gradeList = gradeService.getClassGradeInfo(user.getUserId());
        if (gradeList.isEmpty()) {
            return new Result<>().fail();
        }
        return new Result<>().success().put(gradeList);
    }

    @PostMapping("/classGradeExport")
    @ApiOperation(tags = "导出模块", value = "班级成绩信息导出", notes = "不需要参数")
    public void exportClassGrade(HttpServletResponse response) throws ClassNotFoundException, IOException {
        ExcelWriter writer = ExcelUtil.getWriter();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return;
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Map<String, Object>> classGradeInfo = gradeService.getClassGradeInfo(user.getUserId());
//        List<Map<String, Object>> rows = bedInfs.stream().map(item -> {
//            Map<String, Object> maps = new HashMap<>();
//            maps.put("id", item.getId().toString());
//            maps.put("floor", item.getFloor());
//            maps.put("room", item.getRoom());
//            maps.put("code", item.getCode());
//            maps.put("type", item.getType());
//            maps.put("status", item.getStatus());
//            maps.put("¥price", item.getPrice());
//            maps.put("remark", item.getRemark());
//            // 格式化日期createTime
//            maps.put("createTime", DateUtil.format(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
//            maps.put("updateTime", DateUtil.format(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
//            maps.put("deleted", item.getDeleted());
//            return maps;
//        }).collect(Collectors.toList());

        // Title
        int columns = Class.forName("com.sicau.minordegreemanagement.facade.vo.GradeExportInfo").getDeclaredFields().length;
        writer.merge(columns - 1, "班级成绩");

        // Header
        writer.addHeaderAlias("gradeId", "成绩id");
        writer.addHeaderAlias("studentId", "学生id");
        writer.addHeaderAlias("courseId", "课程id");
        writer.addHeaderAlias("courseName", "课程名称");
        writer.addHeaderAlias("grade", "成绩");
        writer.addHeaderAlias("minorDegreeState", "辅修状态");
        writer.addHeaderAlias("teacherName", "教师名称");

        // Body 设置表格列的宽度
        //writer.setColumnWidth(0, 30);
        //writer.setColumnWidth(1, 30);
        //writer.setColumnWidth(2, 30);
        //writer.setColumnWidth(3, 30);
        //writer.setColumnWidth(4, 30);
        writer.write(classGradeInfo, true);

        // 设置浏览器响应头
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("班级成绩信息表-" + DateUtil.today() + ".xls", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers","Content-disposition");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/studentGradeExport")
    @ApiOperation(tags = "导出模块", value = "个人成绩信息导出", notes = "不需要参数")
    public void exportStudentGrade(HttpServletResponse response) throws ClassNotFoundException, IOException {
        ExcelWriter writer = ExcelUtil.getWriter();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return;
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Map<String, Object>> studentGradeListInfo = gradeService.getGradeInfoList(user.getUserId());

        int columns = Class.forName("com.sicau.minordegreemanagement.facade.vo.StudentGradeExportInfo").getDeclaredFields().length;
        writer.merge(columns - 1, "个人成绩信息");

        // Header
        writer.addHeaderAlias("gradeId", "成绩id");
        writer.addHeaderAlias("studentId", "学生id");
        writer.addHeaderAlias("courseId", "课程id");
        writer.addHeaderAlias("courseName", "课程名称");
        writer.addHeaderAlias("grade", "成绩");
        writer.addHeaderAlias("minorDegreeState", "辅修状态");
        writer.addHeaderAlias("studentName", "学生姓名");

        writer.write(studentGradeListInfo, true);

        // 设置浏览器响应头
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("个人成绩信息表-" + DateUtil.today() + ".xls", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers","Content-disposition");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/courseGradeExport")
    @ApiOperation(tags = "导出模块", value = "已修课程信息导出", notes = "不需要参数")
    public void exportCourseGrade(HttpServletResponse response) throws ClassNotFoundException, IOException {
        ExcelWriter writer = ExcelUtil.getWriter();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return;
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Map<String, Object>> studentCourseInfoList = courseService.getTokenCourseInfo(user.getUserId());


        int columns = Class.forName("com.sicau.minordegreemanagement.facade.vo.CourseGradeInfo").getDeclaredFields().length;
        writer.merge(columns - 1, "个人成绩信息");

        // Header
        writer.addHeaderAlias("courseId", "课程ID");
        writer.addHeaderAlias("courseName", "课程名称");
        writer.addHeaderAlias("courseCode", "课程代码");
        writer.addHeaderAlias("majorCode", "专业代码");
        writer.addHeaderAlias("majorName", "专业名称");
        writer.addHeaderAlias("teacherId", "科任教师ID");
        writer.addHeaderAlias("teacherName", "教师姓名");


        writer.write(studentCourseInfoList, true);

        // 设置浏览器响应头
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("已修课程表-" + DateUtil.today() + ".xls", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers","Content-disposition");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IOUtils.closeQuietly(out);
    }
}
