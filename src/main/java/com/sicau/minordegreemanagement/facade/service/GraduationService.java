package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.vo.GraduationInfo;

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
public interface GraduationService extends IService<Graduation> {

    List<Student> getPlanCount();

    List<Graduation> getGraduationInfo(String collegeName, String checkState);

    List<Graduation> getThesisCheck();

    boolean changeThesisState(GraduationInfo graduationInfo);
}
