package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
import com.sicau.minordegreemanagement.facade.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CourseSelectionServiceImpl extends ServiceImpl<CourseSelectionMapper, CourseSelection> implements CourseSelectionService {

    @Autowired
    private CourseSelectionMapper courseSelectionMapper;

    @Autowired
    private MinorDegreeMapper minorDegreeMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Course> getCourseSelection(Integer userId) {
        //MinorDegree minorDegree = minorDegreeMapper.selectByStudentId(userId);
        //Major major = majorMapper.selectById(majorId);
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
        List<Integer> courseIdList = gradeList.stream().map(Grade::getCourseId).collect(Collectors.toList());
        List<Course> formerCourseList = courseMapper.selectCourseInfoByCourseId(courseIdList);
        //拿到所有可选选修的课程信息
        List<Course> allCourseList = new ArrayList<>();
        allCourseList.addAll(minorCourseList);
        allCourseList.addAll(formerCourseList);

        return allCourseList;
    }
}
