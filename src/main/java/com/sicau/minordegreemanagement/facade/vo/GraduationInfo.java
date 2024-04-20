package com.sicau.minordegreemanagement.facade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Data
public class GraduationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 毕业信息id
     */
    private Integer graduationId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 论文题目
     */
    private String thesisTitle;

    /**
     * 论文指导教师
     */
    private Integer thesisAdvisorId;

    /**
     * 论文状态；0完成，1未完成
     */
    private String thesisState;

    /**
     * 开题状态：0完成，1为完成
     */
    private String openingReportState;

    /**
     * 学分统计
     */
    private BigDecimal creditCount;


    /**
     * 学分统计
     */
    private String collegeName;

    /**
     * 论文查重状态，0已经查重完成，1没有查重完成
     */
    private String repetitionState;


}
