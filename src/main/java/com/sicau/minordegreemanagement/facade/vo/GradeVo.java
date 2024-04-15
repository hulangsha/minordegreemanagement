package com.sicau.minordegreemanagement.facade.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 注释: 导入对象
 * @Description: dataIndex 数据的行号,
 * ifNull默认是true 如果有空行就终止导入，false就是忽略空行继续导入
 *
 * @author yangyongzhuo 2022/11/17 15:42
 */
@Data
public class GradeVo implements Serializable {

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


}
