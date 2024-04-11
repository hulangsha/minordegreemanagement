package com.sicau.minordegreemanagement.facade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("t_grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩id
     */
    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

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
     * 成绩
     */
    @TableField("grade")
    private Integer grade;

    /**
     * 辅修状态：0主修课程，辅修课程
     */
    @TableField("minor_degree_state")
    private String minorDegreeState;


}
