package com.sicau.minordegreemanagement.facade.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.*;
import com.sicau.minordegreemanagement.facade.mapper.*;
import com.sicau.minordegreemanagement.facade.service.CourseSelectionService;
import com.sicau.minordegreemanagement.facade.vo.CourseSelectionInfo;
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
public class CourseSelectionServiceImpl extends ServiceImpl<CourseSelectionMapper, CourseSelection> implements CourseSelectionService {

    @Override
    public boolean addCourseSelection(CourseSelectionInfo courseSelectionInfo) {
        CourseSelection courseSelection = new CourseSelection();
        BeanUtils.copyProperties(courseSelectionInfo, courseSelection);
        return this.save(courseSelection);
    }
}
