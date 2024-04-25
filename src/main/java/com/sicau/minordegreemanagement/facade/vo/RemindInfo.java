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
 * @since 2024-04-25
 */
@Data
public class RemindInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提醒id
     */
    private Integer remindId;

    /**
     * 提醒内容
     */
    private String remindContent;

    /**
     * 提醒类型；0毕业审核信息不达标，三不满足；1论文重复
     */
    private String remindType;

    /**
     * 学生id，被提醒的对象
     */
    private Integer studentId;

    /**
     * 学生id，被提醒的对象
     */
    @TableField("graduation_id")
    private Integer graduationId;



}
