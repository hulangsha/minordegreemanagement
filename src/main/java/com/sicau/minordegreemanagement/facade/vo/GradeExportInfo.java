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
public class GradeExportInfo implements Serializable {

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
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程名称
     */
    private String courseName;




}
