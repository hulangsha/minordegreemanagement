package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.mapper.CourseMapper;
import com.sicau.minordegreemanagement.facade.mapper.GradeMapper;
import com.sicau.minordegreemanagement.facade.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Override
    public List<Course> getCourseInfoList() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        return this.list(queryWrapper);
    }

    @Override
    public List<Course> getTokenCourseInfo(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryTokenCourse(userId);
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
        return this.list(queryWrapper);
    }

    @Override
    public List<Course> getUnTokenCourseInfo(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryTokenCourse(userId);
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
        return this.list(queryWrapper);
    }
}
