package com.sicau.minordegreemanagement.facade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
import com.sicau.minordegreemanagement.facade.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseSelectionMapper courseSelectionMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public Student getStudentInfoById(Integer studentId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school_status", CommonCode.CONST_NUMBER_ONE.getCode())
                .eq("student_id", studentId);
        return this.getOne(queryWrapper);
    }

    @Override
    public JSONObject getMinorDegreeInfo() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return null;
        }
        User student = (User) subject.getPrincipal();
        //选课信息
        CourseSelection courseSelection = null;
        try {
            courseSelection = courseSelectionMapper.selectById(student.getUserId());
        } catch (Exception e) {
            log.info("你又有什么毛病：{}", e);
            throw new RuntimeException(e);
        }
        //课程信息
        Course course = courseMapper.selectById(courseSelection.getCourseId());
        //专业信息
        String majorCode = course.getMajorCode();
        Integer majorId = Integer.valueOf(majorCode.substring(majorCode.length() - 1));
        Major major = majorMapper.selectById(majorId);
        //学院信息，并拿到能辅修的学位
        String collegeCode = major.getCollegeCode();
        Integer collegeId = Integer.valueOf(collegeCode.substring(majorCode.length() - 1));
        List<College> collegeList = collegeMapper.getCollegeInfo(collegeId);
        //拿到能申请辅修的专业
        List<String> collectCodeList = collegeList.stream().map(College::getCollegeCode).collect(Collectors.toSet()).stream().collect(Collectors.toList());
        List<Major> majorList = majorMapper.selectMajorListInfoByCode(collectCodeList);
        //封装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("collegeList", collegeList);
        jsonObject.put("majorList", majorList);
        return jsonObject;
    }


}
