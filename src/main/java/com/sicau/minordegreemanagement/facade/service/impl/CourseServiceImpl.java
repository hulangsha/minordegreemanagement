package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.entity.Major;
import com.sicau.minordegreemanagement.facade.entity.Teacher;
import com.sicau.minordegreemanagement.facade.mapper.CourseMapper;
import com.sicau.minordegreemanagement.facade.mapper.GradeMapper;
import com.sicau.minordegreemanagement.facade.mapper.MajorMapper;
import com.sicau.minordegreemanagement.facade.mapper.TeacherMapper;
import com.sicau.minordegreemanagement.facade.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public List<Map<String, Object>> getCourseInfoList() {
        QueryWrapper<Course> courseQueryWrapperWrapper = new QueryWrapper<>();
        List<Course> courseList = courseMapper.selectList(courseQueryWrapperWrapper);

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Course course : courseList) {
            HashMap<String , Object> resultMap = new HashMap<>();
            Major major = majorMapper.selectByMajorCode(course.getMajorCode());
            if (major != null) {
                resultMap.put("majorName", major.getMajorName());
            }
            Teacher teacher = teacherMapper.selectById(course.getTeacherId());
            if (teacher != null) {
                resultMap.put("teacherName", teacher.getTeacherName());
            }
            resultMap.put("courseId", course.getCourseId());
            resultMap.put("courseName", course.getCourseName());
            resultMap.put("majorCode", course.getMajorCode());
            resultMap.put("courseCode", course.getCourseCode());
            resultMap.put("teacherId", course.getTeacherId());
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getTokenCourseInfo(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryTokenCourse(userId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (gradeList.isEmpty()) {
            return null;
        }
        List<Integer> courseIdList = gradeList.stream().map(Grade::getCourseId).collect(Collectors.toList());
        if (courseIdList.isEmpty()) {
            return null;
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        try {
            queryWrapper.in("course_id", courseIdList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        for (Course course : courseList) {
            HashMap<String , Object> resultMap = new HashMap<>();
            Major major = majorMapper.selectByMajorCode(course.getMajorCode());
            if (major != null) {
                resultMap.put("majorName", major.getMajorName());
            }
            Teacher teacher = teacherMapper.selectById(course.getTeacherId());
            if (teacher != null) {
                resultMap.put("teacherName", teacher.getTeacherName());
            }
            resultMap.put("courseId", course.getCourseId());
            resultMap.put("courseName", course.getCourseName());
            resultMap.put("majorCode", course.getMajorCode());
            resultMap.put("courseCode", course.getCourseCode());
            resultMap.put("teacherId", course.getTeacherId());
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getUnTokenCourseInfo(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryTokenCourse(userId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (gradeList.isEmpty()) {
            return null;
        }
        List<Integer> courseIdList = gradeList.stream().map(Grade::getCourseId).collect(Collectors.toList());
        if (courseIdList.isEmpty()) {
            return null;
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        try {
            queryWrapper.notIn("course_id", courseIdList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        for (Course course : courseList) {
            HashMap<String , Object> resultMap = new HashMap<>();
            Major major = majorMapper.selectByMajorCode(course.getMajorCode());
            if (major != null) {
                resultMap.put("majorName", major.getMajorName());
            }
            Teacher teacher = teacherMapper.selectById(course.getTeacherId());
            if (teacher != null) {
                resultMap.put("teacherName", teacher.getTeacherName());
            }
            resultMap.put("courseId", course.getCourseId());
            resultMap.put("courseName", course.getCourseName());
            resultMap.put("majorCode", course.getMajorCode());
            resultMap.put("courseCode", course.getCourseCode());
            resultMap.put("teacherId", course.getTeacherId());
            resultList.add(resultMap);
        }
        return resultList;
    }
}
