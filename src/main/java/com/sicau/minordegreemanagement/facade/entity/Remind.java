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
 * @since 2024-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_remind")
public class Remind implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提醒id
     */
    @TableId(value = "remind_id", type = IdType.AUTO)
    private Integer remindId;

    /**
     * 提醒内容
     */
    @TableField("remind_content")
    private String remindContent;

    /**
     * 提醒类型；0毕业审核信息不达标，三不满足；1论文重复
     */
    @TableField("remind_type")
    private String remindType;

    /**
     * 学生id，被提醒的对象
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 是否完成，0未完成，1完成
     */
    @TableField("is_finish")
    private String isFinish;

    /**
     * 学生id，被提醒的对象
     */
    @TableField("graduation_id")
    private Integer graduationId;


}
