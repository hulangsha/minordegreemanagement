package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Grade;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface GradeService extends IService<Grade> {

    List<Map<String, Object>> getGradeInfoList(Integer userId);

    List<Map<String, Object>> getClassGradeInfo(Integer userId);
}
