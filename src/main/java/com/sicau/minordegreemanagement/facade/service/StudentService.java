package com.sicau.minordegreemanagement.facade.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface StudentService extends IService<Student> {

    Student getStudentInfoById(Integer studentId);

    JSONObject getMinorDegreeInfo();

    List<Map<String, Object>> getStudentInfo(Integer userId);
}
