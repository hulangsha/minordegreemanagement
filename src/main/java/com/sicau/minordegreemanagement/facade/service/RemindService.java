package com.sicau.minordegreemanagement.facade.service;

import com.sicau.minordegreemanagement.facade.entity.Remind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.vo.RemindInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-25
 */
public interface RemindService extends IService<Remind> {

    boolean addThesisRemind(RemindInfo remindInfo);

    List<Remind> queryRemindInfo(Integer studentId);

    boolean updateThesisAndGraduationState( RemindInfo remindInfo);
}
