package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.MinorDegree;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;

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
}
