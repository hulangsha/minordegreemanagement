package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
@Slf4j
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public List<Map<String, Object>> getGradeInfoList(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryGradeInfoList(userId);
        if (gradeList.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Grade grade : gradeList) {
            HashMap<String, Object> resultMap = new HashMap<>();
            Student student = studentMapper.selectById(userId);
            if (student != null) {
                resultMap.put("studentName", student.getStudentName());
            }
            Course course = courseMapper.selectById(grade.getCourseId());
            if (course != null) {
                resultMap.put("courseName", course.getCourseName());
            }
            resultMap.put("gradeId", grade.getGradeId());
            resultMap.put("studentId", grade.getStudentId());
            resultMap.put("courseId", grade.getCourseId());
            resultMap.put("grade", grade.getGrade());
            resultMap.put("minorDegreeState", grade.getMinorDegreeState());
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getClassGradeInfo(Integer userId) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        List<Grade> gradeList = this.list(queryWrapper);
        if (gradeList.isEmpty()) {
            return new ArrayList<>();
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Grade grade : gradeList) {
            Course course = courseMapper.selectById(grade.getCourseId());
            Integer teacherId = course.getTeacherId();
            HashMap<String, Object> resultMap = new HashMap<>();
            Teacher teacher = teacherMapper.selectById(userId);
            if (null != teacher && teacher.getTeacherId().equals(teacherId)) {
                resultMap.put("teacherName", teacher.getTeacherName());
                resultMap.put("gradeId", grade.getGradeId());
                resultMap.put("studentId", grade.getStudentId());
                resultMap.put("courseId", grade.getCourseId());
                resultMap.put("grade", grade.getGrade());
                resultMap.put("minorDegreeState", grade.getMinorDegreeState());
            }
            if (course != null && teacher.getTeacherId().equals(teacherId)) {
                resultMap.put("courseName", course.getCourseName());
            }
            if (!resultMap.isEmpty()) {
                resultList.add(resultMap);
            }

        }
        return resultList;
    }

    @Override
    public List<Grade> importStudentGrade(MultipartFile file) {
        Workbook workbook = null;
        List<Grade> gradeList = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                i++;
                Row row1 = sheet.getRow(i);
                if(row1 != null){
                    Grade grade = new Grade();
                    Cell cell0 = row1.getCell(0);
                    cell0.setCellType(CellType.STRING);
                    grade.setGradeId(Integer.valueOf(row1.getCell(0).getStringCellValue()));
                    Cell cell1 = row1.getCell(1);
                    cell1.setCellType(CellType.STRING);
                    grade.setStudentId(Integer.valueOf(row1.getCell(1).getStringCellValue()));
                    Cell cell2 = row1.getCell(2);
                    cell2.setCellType(CellType.STRING);
                    grade.setCourseId(Integer.valueOf(row1.getCell(2).getStringCellValue()));
                    Cell cell3 = row1.getCell(3);
                    cell3.setCellType(CellType.STRING);
                    grade.setGrade(Integer.valueOf(row1.getCell(3).getStringCellValue()));
                    Cell cell4 = row1.getCell(4);
                    cell4.setCellType(CellType.STRING);
                    grade.setMinorDegreeState(row1.getCell(4).getStringCellValue());
                    gradeList.add(grade);
                }
            }
            workbook.close();
        } catch (Exception e) {
            log.info("毛病{}", e);
            throw new RuntimeException(e);
        }
        return gradeList;
    }

    @Override
    public boolean addStudentGrade(List<Grade> gradeList) {
        try {
            boolean resutl = gradeMapper.addStudentGrade(gradeList);
            return  resutl;
        } catch (Exception e) {
            log.info("你又怎么了，大哥{}", e);
            throw new RuntimeException(e);
        }
    }
}
