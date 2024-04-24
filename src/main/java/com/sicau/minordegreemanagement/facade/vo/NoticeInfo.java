package com.sicau.minordegreemanagement.facade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Data
public class NoticeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告Id
     */
    private Integer noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private String issue;

    /**
     * 发布时间
     */
    private LocalDateTime addTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 删除
     */
    private String isDelete;




}
