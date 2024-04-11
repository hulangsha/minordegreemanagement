package com.sicau.minordegreemanagement.facade.vo;

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
public class MinorDegreeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 辅修id
     */
    private Integer minorDegreeId;

    /**
     * 学院id
     */
    private Integer collegeId;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 课程id
     */
    private Integer majorId;

    /**
     * 课程名称
     */
    private String majorName;


    /**
     * 课程名称
     */
    private Integer userId;



}
