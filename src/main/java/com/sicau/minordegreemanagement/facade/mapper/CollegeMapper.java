package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.College;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface CollegeMapper extends BaseMapper<College> {

    List<College> getCollegeInfo(Integer collegeId);
}
