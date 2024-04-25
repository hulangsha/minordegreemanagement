package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
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
public interface GraduationMapper extends BaseMapper<Graduation> {

    List<Graduation> getRepetitionThesisList(@Param("conditionList") List<String> conditionList);

    boolean updateGraduationById(@Param("graduationId") Integer graduationId);
}
