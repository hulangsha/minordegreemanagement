package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface GradeMapper extends BaseMapper<Grade> {

    List<Grade> queryTokenCourse(Integer userId);

    List<Grade> queryGradeInfoList(Integer userId);

    boolean addStudentGrade(@Param("gradeList") List<Grade> gradeList);
}
