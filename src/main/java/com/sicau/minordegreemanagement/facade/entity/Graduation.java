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
@TableName("t_graduation")
public class Graduation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 毕业信息id
     */
    @TableId(value = "graduation_id", type = IdType.AUTO)
    private Integer graduationId;

    /**
     * 学生id
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 论文题目
     */
    @TableField("thesis_title")
    private String thesisTitle;

    /**
     * 论文指导教师
     */
    @TableField("thesis_advisor_id")
    private Integer thesisAdvisorId;

    /**
     * 论文状态；0完成，1未完成
     */
    @TableField("thesis_state")
    private String thesisState;

    /**
     * 开题状态：0完成，1为完成
     */
    @TableField("opening_report_state")
    private String openingReportState;

    /**
     * 学分统计
     */
    @TableField("credit_count")
    private BigDecimal creditCount;


}
