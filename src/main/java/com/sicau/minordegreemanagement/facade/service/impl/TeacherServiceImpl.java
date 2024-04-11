package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Teacher;
import com.sicau.minordegreemanagement.facade.entity.User;
import com.sicau.minordegreemanagement.facade.mapper.TeacherMapper;
import com.sicau.minordegreemanagement.facade.mapper.UserMapper;
import com.sicau.minordegreemanagement.facade.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Teacher getTeacherInfoById(Integer id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", id);
        return this.getOne(queryWrapper);

    }
}
