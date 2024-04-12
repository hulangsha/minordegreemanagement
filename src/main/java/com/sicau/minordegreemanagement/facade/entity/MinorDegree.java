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
@TableName("t_minor_degree")
public class MinorDegree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 辅修id
     */
    @TableId(value = "minor_degree_id", type = IdType.AUTO)
    private Integer minorDegreeId;

    /**
     * 学院id
     */
    @TableField("college_id")
    private Integer collegeId;

    /**
     * 学院名称
     */
    @TableField("college_name")
    private String collegeName;

    /**
     * 课程id
     */
    @TableField("major_id")
    private Integer majorId;

    /**
     * 课程名称
     */
    @TableField("major_name")
    private String majorName;


    /**
     * 申请辅修人
     */
    @TableField("user_id")
    private Integer userId;



}
