package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
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

    @Autowired
    private MinorDegreeMapper minorDegreeMapper;
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

    @Override
    public List<Course> getCourseSelection(Integer userId) {
        //拿到辅修申请信息
        QueryWrapper<MinorDegree> minorDegreeQueryWrapper = new QueryWrapper<>();
        minorDegreeQueryWrapper.eq("user_id", userId);
        MinorDegree minorDegree = minorDegreeMapper.selectOne(minorDegreeQueryWrapper);
        //拿到专业信息
        Integer majorId = minorDegree.getMajorId();
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("major_id", majorId);
        Major major = majorMapper.selectOne(majorQueryWrapper);
        //拿到辅修课程信息
        String majorCode = major.getMajorCode();
        List<Course> minorCourseList = courseMapper.selectByMajorCode(majorCode);
        //拿到原专业课程
        QueryWrapper<Grade> gradeQueryWrapper = new QueryWrapper<>();
        gradeQueryWrapper.eq("student_id", userId);
        List<Grade> gradeList = gradeMapper.selectList(gradeQueryWrapper);
        Integer courseId = gradeList.get(0).getCourseId();
        Course course = courseMapper.selectById(courseId);
        String formerMajorCode = course.getMajorCode();
        List<Integer> courseIdList = gradeList.stream().map(Grade::getCourseId).collect(Collectors.toList());
        List<Course> formerCourseList = courseMapper.selectCourseInfoByCourseId(courseIdList, formerMajorCode);
        //拿到所有可选选修的课程信息
        List<Course> allCourseList = new ArrayList<>();
        allCourseList.addAll(minorCourseList);
        allCourseList.addAll(formerCourseList);

        return allCourseList;
    }
}
