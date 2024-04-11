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
@TableName("t_college")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学院id
     */
    @TableId(value = "college_id", type = IdType.AUTO)
    private Integer collegeId;

    /**
     * 学院名称
     */
    @TableField("college_name")
    private String collegeName;

    /**
     * 学院代码
     */
    @TableField("college_code")
    private String collegeCode;


}
