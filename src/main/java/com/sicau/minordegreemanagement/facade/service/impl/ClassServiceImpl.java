package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.entity.Class;
import com.sicau.minordegreemanagement.facade.mapper.ClassMapper;
import com.sicau.minordegreemanagement.facade.service.ClassService;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public List<java.lang.Class> getClassInfoList() {
        return null;
    }
}
