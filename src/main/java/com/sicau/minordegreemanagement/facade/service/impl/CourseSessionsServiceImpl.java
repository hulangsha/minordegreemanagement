package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sicau.minordegreemanagement.facade.entity.CourseSelection;
import com.sicau.minordegreemanagement.facade.entity.CourseSessions;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.mapper.CourseSelectionMapper;
import com.sicau.minordegreemanagement.facade.mapper.CourseSessionsMapper;
import com.sicau.minordegreemanagement.facade.mapper.StudentMapper;
import com.sicau.minordegreemanagement.facade.service.CourseSessionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-24
 */
@Service
public class CourseSessionsServiceImpl extends ServiceImpl<CourseSessionsMapper, CourseSessions> implements CourseSessionsService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseSelectionMapper courseSelectionMapper;

    @Autowired
    private CourseSessionsMapper courseSessionsMapper;

    @Override
    public List<CourseSessions> getStudentSession(Integer studentId) {
        Student student = studentMapper.selectById(studentId);
        Integer conditionId = student.getStudentId();
        QueryWrapper<CourseSelection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", conditionId);
        List<CourseSessions> resultList = new ArrayList<>();
        List<CourseSelection> courseSelectionList = courseSelectionMapper.selectList(queryWrapper);
        for (CourseSelection courseSelection : courseSelectionList) {
            Integer courseId = courseSelection.getCourseId();
            CourseSessions courseSessions = courseSessionsMapper.selectById(courseId);
            if (courseSessions != null) {
                resultList.add(courseSessions);
            }

        }
        return resultList;
    }
}
