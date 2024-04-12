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
@TableName("t_class_table")
public class ClassTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;

    /**
     * 教师编号
     */
    @TableField("teacher_number")
    private String teacherNumber;


}
