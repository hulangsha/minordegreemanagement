package com.sicau.minordegreemanagement.facade.vo;

import lombok.Data;

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
public class StudentGradeExportInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩id
     */
    private Integer gradeId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 成绩
     */
    private Integer grade;

    /**
     * 辅修状态：0主修课程，辅修课程
     */
    private String minorDegreeState;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 课程名称
     */
    private String courseName;





}
