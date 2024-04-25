package com.sicau.minordegreemanagement.facade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
import com.sicau.minordegreemanagement.facade.service.StudentService;
import com.sicau.minordegreemanagement.facade.vo.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassTableMapper classTableMapper;


    @Override
    public Student  getStudentInfoById(Integer studentId) {
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
        List<CourseSelection> courseSelection = null;
        try {
            courseSelection = courseSelectionMapper.selectByUserId(student.getUserId());
        } catch (Exception e) {
            log.info("你又有什么毛病：{}", e);
            throw new RuntimeException(e);
        }
        //拿到课已选修课程id
        List<Integer> courseIdList = courseSelection.stream().map(CourseSelection::getCourseId).collect(Collectors.toList());
        //课程信息
        List<Course> courseList = courseMapper.selectByCourseIdList(courseIdList);
        //专业信息
        String majorCode = courseList.get(CommonCode.CONST_NUMBER_ZERO.getCode()).getMajorCode();
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

    @Override
    public List<Map<String, Object>> getStudentInfo(Integer userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Student> studentList = null;
        try {
            studentList = studentMapper.selectList(queryWrapper);
        } catch (Exception e) {
            log.info("你又怎么了大哥，{}", e);
            throw new RuntimeException(e);
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Student student : studentList) {
            Teacher teacher = teacherMapper.selectById(userId);
            ClassTable classTable = classTableMapper.selectById(student.getClassId());
            HashMap<String , Object> resultMap = new HashMap<>();
            if (teacher != null && teacher.getTeacherNumber().equals(classTable.getTeacherNumber())) {
                resultMap.put("studentId", student.getStudentId());
                resultMap.put("studentName", student.getStudentName());
                resultMap.put("studentNumber", student.getStudentNumber());
                resultMap.put("majorName", student.getMajorName());
                resultMap.put("classId", student.getClassId());
                resultMap.put("className", student.getClassName());
                resultMap.put("collegeId", student.getCollegeId());
                resultMap.put("credits", student.getCredits());
                resultMap.put("grade", student.getGrade());
                resultMap.put("sex", student.getSex());
                resultMap.put("lengthOfSchooling", student.getLengthOfSchooling());
                resultMap.put("levelOfCultivation", student.getLevelOfCultivation());
                resultMap.put("schoolStatus", student.getSchoolStatus());
                resultMap.put("timeOfEnrollment", student.getTimeOfEnrollment());
                resultMap.put("nation", student.getNation());
                resultMap.put("mailingAddress", student.getMailingAddress());
                resultMap.put("homeTelephone", student.getHomeTelephone());
                resultMap.put("idCard", student.getIdCard());
                resultMap.put("qqNumber", student.getQqNumber());
            }
            if (classTable.getClassId().equals(student.getClassId()) && classTable != null && teacher.getTeacherNumber().equals(classTable.getTeacherNumber())) {
                resultMap.put("className", classTable.getClassName());
            }
            College college = collegeMapper.selectById(student.getCollegeId());
            if (college != null && teacher.getTeacherNumber().equals(classTable.getTeacherNumber())) {
                resultMap.put("collegeName", college.getCollegeName());
            }
            if (!resultMap.isEmpty()) {
                resultList.add(resultMap);
            }

        }
        return resultList;
    }

    @Override
    public boolean getUpdateStudent(StudentInfo studentInfo) {
        Student student = new Student();
        BeanUtils.copyProperties(studentInfo, student);
        return this.updateById(student);
    }

    @Override
    public List<Student> getStudentInfoByTeacherId(Integer teacherId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        List<Student> resultStudent = new ArrayList<>();
        Teacher teacher = teacherMapper.selectById(teacherId);
        for (Student student : studentList) {
            Integer classId = student.getClassId();
            ClassTable classTable = classTableMapper.selectById(classId);
            if (classTable.getTeacherNumber().equals(teacher.getTeacherNumber())) {
                resultStudent.add(student);
            }
        }
        return resultStudent;
    }
}
