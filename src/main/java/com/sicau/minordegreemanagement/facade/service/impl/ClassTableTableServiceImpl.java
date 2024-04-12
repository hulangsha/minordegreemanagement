package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.ClassTable;
import com.sicau.minordegreemanagement.facade.entity.Teacher;
import com.sicau.minordegreemanagement.facade.mapper.ClassTableMapper;
import com.sicau.minordegreemanagement.facade.mapper.TeacherMapper;
import com.sicau.minordegreemanagement.facade.service.ClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ClassTableTableServiceImpl extends ServiceImpl<ClassTableMapper, ClassTable> implements ClassTableService {
    @Autowired
    private ClassTableMapper classTableMapper;

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public List<ClassTable> getClassInfoList(String roleCode, Integer userId) {
        QueryWrapper<ClassTable> queryWrapper = new QueryWrapper<>();
        List<ClassTable> classTable = null;
        if (roleCode.equals("teacher")) {
            Teacher teacher = teacherMapper.selectById(userId);
            String teacherNumber = teacher.getTeacherNumber();
            classTable = classTableMapper.selectByteacherNumber(teacherNumber);
            return classTable;
        }
        if (roleCode.equals("manager")) {
            return this.list(queryWrapper);
        }
        return new ArrayList<>();
    }
}
