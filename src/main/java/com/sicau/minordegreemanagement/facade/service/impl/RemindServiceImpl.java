package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Remind;
import com.sicau.minordegreemanagement.facade.mapper.GraduationMapper;
import com.sicau.minordegreemanagement.facade.mapper.RemindMapper;
import com.sicau.minordegreemanagement.facade.service.RemindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.facade.vo.RemindInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-25
 */
@Service
@Slf4j
public class RemindServiceImpl extends ServiceImpl<RemindMapper, Remind> implements RemindService {
    @Autowired
    private GraduationMapper graduationMapper;

    @Override
    public boolean addThesisRemind(RemindInfo remindInfo) {
        Remind remind = new Remind();
        BeanUtils.copyProperties(remindInfo, remind);
        return this.save(remind);
    }

    @Override
    public List<Remind> queryRemindInfo(Integer studentId) {
        QueryWrapper<Remind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_finish", CommonCode.CONST_NUMBER_ZERO.getCode()).eq("student_id", studentId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateThesisAndGraduationState(RemindInfo remindInfo) {
        Remind remind = new Remind();
        BeanUtils.copyProperties(remindInfo, remind);
        boolean result = this.updateById(remind);
        if (result) {
            boolean graduationState =  graduationMapper.updateGraduationById(remindInfo.getGraduationId());
            if (graduationState) {
                log.info("状态更新完成");
            }
        }
        return result;
    }
}
