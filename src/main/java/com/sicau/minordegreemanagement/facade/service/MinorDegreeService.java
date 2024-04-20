package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.MinorDegree;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;
import com.sicau.minordegreemanagement.facade.vo.QueryMinorDegree;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface MinorDegreeService extends IService<MinorDegree> {

    boolean addMinorDegree(MinorDegreeInfo minorDegreeInfo);

    boolean minorCheck(MinorDegreeInfo minorDegreeInfo);

    Page<MinorDegree> getMinorCheckPage(QueryMinorDegree queryMinorDegree);

    boolean refuseMinor(Integer minorDegreeId);
}
