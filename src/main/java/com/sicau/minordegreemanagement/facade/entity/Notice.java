package com.sicau.minordegreemanagement.facade.entity;

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
@EqualsAndHashCode(callSuper = false)
@TableName("t_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告Id
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告标题
     */
    @TableField("notice_title")
    private String noticeTitle;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 发布人
     */
    @TableField("issue")
    private String issue;

    /**
     * 发布时间
     */
    @TableField("addTime")
    private LocalDateTime addTime;

    /**
     * 状态
     */
    @TableField("state")
    private String state;


}
