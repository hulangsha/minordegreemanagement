package com.sicau.minordegreemanagement.facade.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 记录用户账号创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;

    /**
     * 入学时间
     */
    @TableField("time_of_enrollment")
    private LocalDateTime timeOfEnrollment;

    /**
     * 用户头像
     */
    @TableField("head_portrait")
    private String headPortrait;

    /**
     * 是否删除，0删除，1不删除
     */
    @TableField("isDelete")
    private String isDelete;


}
