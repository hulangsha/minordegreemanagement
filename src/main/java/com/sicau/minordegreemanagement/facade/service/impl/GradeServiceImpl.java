package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.mapper.CourseMapper;
import com.sicau.minordegreemanagement.facade.mapper.GradeMapper;
import com.sicau.minordegreemanagement.facade.mapper.StudentMapper;
import com.sicau.minordegreemanagement.facade.mapper.UserMapper;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;
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
}
