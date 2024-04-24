package com.sicau.minordegreemanagement.facade.service;

import com.sicau.minordegreemanagement.facade.entity.CourseSessions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-24
 */
public interface CourseSessionsService extends IService<CourseSessions> {

    List<CourseSessions> getStudentSession(Integer studentId);
}
