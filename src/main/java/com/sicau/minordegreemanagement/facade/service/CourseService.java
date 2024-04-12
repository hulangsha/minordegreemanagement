package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Course;

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
public interface CourseService extends IService<Course> {

    List<Map<String, Object>> getCourseInfoList();

    List<Map<String, Object>> getTokenCourseInfo(Integer userId);

    List<Map<String, Object>> getUnTokenCourseInfo(Integer userId);

    List<Course> getCourseSelection(Integer userId);
}
