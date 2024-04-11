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
@TableName("t_major")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    @TableId(value = "major_id", type = IdType.AUTO)
    private Integer majorId;

    /**
     * 专业名称
     */
    @TableField("major_name")
    private String majorName;

    /**
     * 专业代码
     */
    @TableField("major_code")
    private String majorCode;

    /**
     * 学院代码
     */
    @TableField("college_code")
    private String collegeCode;


}
