package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.MinorDegree;
import com.sicau.minordegreemanagement.facade.mapper.MinorDegreeMapper;
import com.sicau.minordegreemanagement.facade.service.MinorDegreeService;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class MinorDegreeServiceImpl extends ServiceImpl<MinorDegreeMapper, MinorDegree> implements MinorDegreeService {

    @Override
    public boolean addMinorDegree(MinorDegreeInfo minorDegreeInfo) {
        MinorDegree minorDegree = new MinorDegree();
        BeanUtils.copyProperties(minorDegreeInfo, minorDegree);
        return this.save(minorDegree);
    }
}
