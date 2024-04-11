package com.sicau.minordegreemanagement.facade.vo;

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
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 记录用户账号创建时间
     */
    private LocalDateTime createTime;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 入学时间
     */
    private LocalDateTime timeOfEnrollment;

    /**
     * 用户头像
     */
    private String headPortrait;

    /**
     * 是否删除，0删除，1不删除
     */
    private String isDelete;


}
