package com.sicau.minordegreemanagement.facade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sicau.minordegreemanagement.common.utils.Description;
import com.sicau.minordegreemanagement.common.utils.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 注释: 导入对象
 * @Description: dataIndex 数据的行号,
 * ifNull默认是true 如果有空行就终止导入，false就是忽略空行继续导入
 *
 * @author yangyongzhuo 2022/11/17 15:42
 */
@Data
@Description(dataIndex = 5, ifNull = false)
public class GradeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩id
     */
    @Excel("成绩id")
    private Integer gradeId;

    /**
     * 学生id
     */
    @Excel("学生id")
    private Integer studentId;

    /**
     * 课程id
     */
    @Excel("课程id")
    private Integer courseId;

    /**
     * 成绩
     */
    @Excel("成绩")
    private Integer grade;

    /**
     * 辅修状态：0主修课程，辅修课程
     */
    @Excel("辅修状态，0主修课程，1辅修课程")
    private String minorDegreeState;


}
