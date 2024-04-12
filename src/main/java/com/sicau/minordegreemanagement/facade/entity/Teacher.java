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
@TableName("t_teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 教师姓名
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 教师编号
     */
    @TableField("teacher_number")
    private String teacherNumber;

    /**
     * 所授专业
     */
    @TableField("major")
    private String major;



}
