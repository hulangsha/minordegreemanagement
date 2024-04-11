package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Grade;
import com.sicau.minordegreemanagement.facade.mapper.GradeMapper;
import com.sicau.minordegreemanagement.facade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public List<Grade> getGradeInfoList(Integer userId) {
        List<Grade> gradeList = gradeMapper.queryGradeInfoList(userId);
        if (gradeList.isEmpty()) {
            return null;
        }
        return gradeList;
    }
}
