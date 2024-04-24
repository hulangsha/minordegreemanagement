package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Notice;
import com.sicau.minordegreemanagement.facade.mapper.NoticeMapper;
import com.sicau.minordegreemanagement.facade.service.NoticeService;
import com.sicau.minordegreemanagement.facade.vo.NoticeInfo;
import org.springframework.beans.BeanUtils;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public List<Notice> getNoticePage() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDelete", CommonCode.CONST_NUMBER_ONE.getCode());
        return this.list(queryWrapper);
    }

    @Override
    public boolean addNotice(NoticeInfo noticeInfo) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeInfo, notice);
        return this.save(notice);
    }
}
