package com.sicau.minordegreemanagement.facade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicau.minordegreemanagement.facade.entity.Notice;
import com.sicau.minordegreemanagement.facade.vo.NoticeInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
public interface NoticeService extends IService<Notice> {

    List<Notice> getNoticePage();

    boolean addNotice(NoticeInfo noticeInfo);
}
