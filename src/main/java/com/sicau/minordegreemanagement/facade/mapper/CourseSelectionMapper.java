package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.CourseSelection;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface CourseSelectionMapper extends BaseMapper<CourseSelection> {

    List<CourseSelection> getCourseSelectionInfo(Integer userId);

    List<CourseSelection> selectByUserId(Integer userId);
}
