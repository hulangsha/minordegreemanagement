package com.sicau.minordegreemanagement.facade.vo;

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
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 专业
     */
    private String majorName;

    /**
     * 班级
     */
    private Integer classId;

    /**
     * 班级
     */
    private String className;

    /**
     * 学院id
     */
    private Integer collegeId;

    /**
     * 学分
     */
    private BigDecimal credits;

    /**
     * 年级
     */
    private String grade;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学制
     */
    private Integer lengthOfSchooling;

    /**
     * 培养层次
     */
    private String levelOfCultivation;

    /**
     * 学籍状态
     */
    private String schoolStatus;

    /**
     * 入学日期
     */
    private LocalDateTime timeOfEnrollment;

    /**
     * 民族
     */
    private String nation;

    /**
     * 通讯地址
     */
    private String mailingAddress;

    /**
     * 家庭电话
     */
    private String homeTelephone;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * qq号
     */
    private Integer qqNumber;


}
