package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Major;
import com.sicau.minordegreemanagement.facade.entity.MinorDegree;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.mapper.MajorMapper;
import com.sicau.minordegreemanagement.facade.mapper.MinorDegreeMapper;
import com.sicau.minordegreemanagement.facade.mapper.StudentMapper;
import com.sicau.minordegreemanagement.facade.service.MinorDegreeService;
import com.sicau.minordegreemanagement.facade.vo.MinorDegreeInfo;
import com.sicau.minordegreemanagement.facade.vo.QueryMinorDegree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MinorDegreeServiceImpl extends ServiceImpl<MinorDegreeMapper, MinorDegree> implements MinorDegreeService {

    @Autowired
    private MinorDegreeMapper minorDegreeMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean addMinorDegree(MinorDegreeInfo minorDegreeInfo) {
        MinorDegree minorDegree = new MinorDegree();
        BeanUtils.copyProperties(minorDegreeInfo, minorDegree);
        return this.save(minorDegree);
    }

    @Override
    public boolean minorCheck(MinorDegreeInfo minorDegreeInfo) {
        MinorDegree minorDegree = new MinorDegree();
        BeanUtils.copyProperties(minorDegreeInfo, minorDegree);
        return this.updateById(minorDegree);
    }

    @Override
    public List<Map<String, Object>> getMinorCheckPage() {
        List<Map<String, Object>> resultList = new ArrayList<>();

        QueryWrapper<MinorDegree> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("minor_check_state", CommonCode.CONST_NUMBER_ONE.getCode());
        List<MinorDegree> minorDegreeList = minorDegreeMapper.selectList(queryWrapper);
        for (MinorDegree minorDegree : minorDegreeList) {
            Map<String, Object> resultMap = new HashMap<>();
            Integer userId = minorDegree.getUserId();
            Student student = studentMapper.selectById(userId);
            if (null != student) {
                resultMap.put("studentName", student.getStudentName());
            }
            resultMap.put("minorDegreeId", minorDegree.getMinorDegreeId());
            resultMap.put("collegeId", minorDegree.getCollegeId());
            resultMap.put("collegeName", minorDegree.getCollegeName());
            resultMap.put("majorName", minorDegree.getMajorName());
            resultMap.put("userId", minorDegree.getUserId());
            resultMap.put("minorCheckState", minorDegree.getMinorCheckState());
            resultList.add(resultMap);

        }
        return resultList;
    }

    @Override
    public boolean refuseMinor(Integer minorDegreeId) {
        return this.removeById(minorDegreeId);
    }
}
