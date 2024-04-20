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
public class QueryMinorDegree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 辅修id
     */
    private Integer minorDegreeId;

    /**
     * 学院id
     */
    private Integer collegeId;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 课程id
     */
    private Integer majorId;

    /**
     * 课程名称
     */
    private String majorName;


    /**
     * 课程名称
     */
    private Integer userId;

    /**
     * 辅修审核状态，0同意，1不同意，默认为1
     */
    private String minorCheckState;
    /**
     * 当前页
     */
    private Long currentPage;

    /**
     * 每页显示条数
     */
    private Long pageSize;






}
