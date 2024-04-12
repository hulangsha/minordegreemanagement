package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
import com.sicau.minordegreemanagement.facade.mapper.GraduationMapper;
import com.sicau.minordegreemanagement.facade.service.GraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class GraduationServiceImpl extends ServiceImpl<GraduationMapper, Graduation> implements GraduationService {

    @Autowired
    private GraduationMapper graduationMapper;
    @Override
    public List<Map<Integer, Integer>> getPlanCount() {
        QueryWrapper<Graduation> queryWrapper = new QueryWrapper<>();
        List<Graduation> graduationList = graduationMapper.selectList(queryWrapper);
        List<Map<Integer, Integer>> resultList = new ArrayList<>();
        for (Graduation graduation : graduationList) {
            Integer countNumber = 0;
            HashMap<Integer, Integer> resultMap = new HashMap<>();
            if (graduation.getThesisState().equals(0)) {
                countNumber++;
            }
            if (graduation.getOpeningReportState().equals(0)) {
                countNumber++;
            }
            int flag = graduation.getCreditCount().compareTo(BigDecimal.valueOf(85));
            if (flag >= 0) {
                countNumber++;
            }
            resultMap.put(graduation.getStudentId(), countNumber);
            resultList.add(resultMap);
        }
        return resultList;
    }
}
