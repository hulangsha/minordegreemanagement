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
@TableName("t_course_selection")
public class CourseSelection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 选课id
     */
    @TableId(value = "selection_id", type = IdType.AUTO)
    private Integer selectionId;

    /**
     * 学生id
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 课程id
     */
    @TableField("course_id")
    private Integer courseId;

    /**
     * 选课时间
     */
    @TableField("selection_time")
    private LocalDateTime selectionTime;


}
