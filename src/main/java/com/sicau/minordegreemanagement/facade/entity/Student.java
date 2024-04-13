package com.sicau.minordegreemanagement.facade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    /**
     * 姓名
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 学号
     */
    @TableField("student_number")
    private String studentNumber;

    /**
     * 专业
     */
    @TableField("major_name")
    private String majorName;

    /**
     * 班级
     */
    @TableField("class_id")
    private Integer classId;

    /**
     * 班级
     */
    @TableField("class_name")
    private String className;

    /**
     * 学院id
     */
    @TableField("college_id")
    private Integer collegeId;

    /**
     * 学分
     */
    @TableField("credits")
    private BigDecimal credits;

    /**
     * 年级
     */
    @TableField("grade")
    private String grade;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 学制
     */
    @TableField("length_of_schooling")
    private Integer lengthOfSchooling;

    /**
     * 培养层次
     */
    @TableField("level_of_cultivation")
    private String levelOfCultivation;

    /**
     * 学籍状态
     */
    @TableField("school_status")
    private String schoolStatus;

    /**
     * 入学日期
     */
    @TableField("time_of_enrollment")
    private LocalDateTime timeOfEnrollment;

    /**
     * 民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 通讯地址
     */
    @TableField("mailing_address")
    private String mailingAddress;

    /**
     * 家庭电话
     */
    @TableField("home_telephone")
    private String homeTelephone;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;

    /**
     * qq号
     */
    @TableField("qq_number")
    private Integer qqNumber;



    /**
     * 毕业状态
     */
    @TableField("check_state")
    private String checkState;


}
