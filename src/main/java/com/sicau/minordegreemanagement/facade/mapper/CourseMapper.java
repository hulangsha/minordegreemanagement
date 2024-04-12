package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.Course;
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
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectByCourseIdList(@Param("courseIdList") List<Integer> courseIdList);

    List<Course> selectByMajorCode(String majorCode);

    List<Course> selectCourseInfoByCourseId(@Param("courseIdList") List<Integer> courseIdList, String majorCode);
}
