package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.mapper.GraduationMapper;
import com.sicau.minordegreemanagement.facade.mapper.StudentMapper;
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

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Map<Integer , Object>> getPlanCount() {
        QueryWrapper<Graduation> queryWrapper = new QueryWrapper<>();
        List<Graduation> graduationList = graduationMapper.selectList(queryWrapper);
        List<Map<Integer, Object>> resultList = new ArrayList<>();
        for (Graduation graduation : graduationList) {
            Student student = studentMapper.selectById(graduation.getStudentId());
            Integer countNumber = 0;
            HashMap<Integer, Object> resultMap = new HashMap<>();
            if (graduation.getThesisState().equals("0")) {
                countNumber++;
            }
            if (graduation.getOpeningReportState().equals("0")) {
                countNumber++;
            }
            int flag = graduation.getCreditCount().compareTo(BigDecimal.valueOf(85));
            if (flag >= 0) {
                countNumber++;
            }
            student.setCheckState(countNumber.toString());
            resultMap.put(student.getStudentId(), student);
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<Graduation> getGraduationInfo(String collegeName, String checkState) {
        QueryWrapper<Graduation> graduationQueryWrapper = new QueryWrapper<>();
        List<Graduation> graduationList = null;
        if (checkState.equals(0)) {
            graduationQueryWrapper.eq("thesis_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                    .eq("opening_report_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                    .le("credit_count", 85)
                    .or(wrapper -> wrapper.eq("college_name", collegeName));
            graduationList = graduationMapper.selectList(graduationQueryWrapper);
        } else {
            graduationQueryWrapper.eq("college_name", collegeName)
                    .or(wrapper -> wrapper.eq("thesis_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                            .eq("opening_report_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                            .ge("credit_count", 85));
            graduationList = graduationMapper.selectList(graduationQueryWrapper);
        }
        return graduationList;
    }
}
