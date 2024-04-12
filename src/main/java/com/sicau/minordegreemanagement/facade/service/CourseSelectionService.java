package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Course;
import com.sicau.minordegreemanagement.facade.entity.CourseSelection;
import com.sicau.minordegreemanagement.facade.vo.CourseSelectionInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface CourseSelectionService extends IService<CourseSelection> {

    boolean addCourseSelection(CourseSelectionInfo courseSelectionInfo);
}
