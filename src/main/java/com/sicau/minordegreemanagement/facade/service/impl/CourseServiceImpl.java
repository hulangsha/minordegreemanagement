package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.mapper.CourseMapper;
import com.sicau.minordegreemanagement.facade.service.CourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public List<Course> getCourseInfoList() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        return this.list(queryWrapper);
    }
}
