package com.sicau.minordegreemanagement.facade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_course_sessions")
public class CourseSessions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节次id
     */
    @TableId(value = "session_id", type = IdType.AUTO)
    private Integer sessionId;

    /**
     * 关联课程表
     */
    @TableField("course_id")
    private Integer courseId;

    /**
     * 节次编号
     */
    @TableField("session_number")
    private Integer sessionNumber;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalTime endTime;


}
