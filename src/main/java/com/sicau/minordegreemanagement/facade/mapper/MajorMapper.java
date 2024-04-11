package com.sicau.minordegreemanagement.facade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicau.minordegreemanagement.facade.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface MajorMapper extends BaseMapper<Major> {

    List<Major> getMajorInfo(Integer majorId);


    List<Major> selectMajorListInfoByCode(@Param("collectCodeList") List<String> collectCodeList);
}
