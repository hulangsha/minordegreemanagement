package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Teacher;
import com.sicau.minordegreemanagement.facade.vo.TeacherInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface TeacherService extends IService<Teacher> {

    Teacher getTeacherInfoById(Integer id);

    boolean getUpdateTeacher(TeacherInfo teacherInfo);
}
