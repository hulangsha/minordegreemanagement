package com.sicau.minordegreemanagement.facade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicau.minordegreemanagement.common.component.CommonCode;
import com.sicau.minordegreemanagement.facade.entity.Graduation;
import com.sicau.minordegreemanagement.facade.entity.Student;
import com.sicau.minordegreemanagement.facade.mapper.GraduationMapper;
import com.sicau.minordegreemanagement.facade.mapper.StudentMapper;
import com.sicau.minordegreemanagement.facade.service.GraduationService;
import com.sicau.minordegreemanagement.facade.vo.GraduationInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Service
public class GraduationServiceImpl extends ServiceImpl<GraduationMapper, Graduation> implements GraduationService {

    @Autowired
    private GraduationMapper graduationMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getPlanCount() {
        QueryWrapper<Graduation> queryWrapper = new QueryWrapper<>();
        List<Graduation> graduationList = graduationMapper.selectList(queryWrapper);
        List<Student> studentList = new ArrayList<>();
        for (Graduation graduation : graduationList) {
            Student student = studentMapper.selectById(graduation.getStudentId());
            Integer countNumber = 0;
            HashMap<Integer, Object> resultMap = new HashMap<>();
            if (graduation.getThesisState().equals("0")) {
                countNumber++;
            }
            if (graduation.getOpeningReportState().equals("0")) {
                countNumber++;
            }
            int flag = graduation.getCreditCount().compareTo(BigDecimal.valueOf(85));
            if (flag >= 0) {
                countNumber++;
            }
            student.setCheckState(countNumber.toString());
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public List<Graduation> getGraduationInfo(String collegeName, String checkState) {
        QueryWrapper<Graduation> graduationQueryWrapper = new QueryWrapper<>();
        BigDecimal bigDecimal = BigDecimal.valueOf(85.00);
        List<Graduation> graduationList = null;
        if (checkState.equals("0")) {
            graduationQueryWrapper.eq("thesis_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                    .eq("opening_report_state", CommonCode.CONST_NUMBER_ZERO.getCode())
                    .ge("credit_count", 85)
                    .eq("college_name", collegeName);
            graduationList = graduationMapper.selectList(graduationQueryWrapper);
        } else {
            graduationQueryWrapper.eq("college_name", collegeName)
                    .and(wrapper -> wrapper.or(wra -> wra.eq("thesis_state", CommonCode.CONST_NUMBER_ONE.getCode()))
                            .or(w -> w.eq("opening_report_state", CommonCode.CONST_NUMBER_ONE.getCode()))
                            .or(p -> p.le("credit_count", 85.00)));
            graduationList = graduationMapper.selectList(graduationQueryWrapper);
        }
        return graduationList;
    }

    @Override
    public List<Graduation> getThesisCheck() {
        QueryWrapper<Graduation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repetition_state", "1");
        //拿到所有论文
        List<Graduation> graduationList = graduationMapper.selectList(queryWrapper);
        //拿到所有文论内容的集合
        List<String> thesisTitleList = graduationList.stream().map(Graduation::getThesisTitle).collect(Collectors.toList());
        //用动态规划算法计算编辑距离，也就是两个字符串的相似度，这里返回了相似度30%以上的论文的一个set集合
        Set<String> repetitionList = calculateSimilarity(thesisTitleList);
        Iterator<String> iterator = repetitionList.iterator();
        List<String> conditionList = new ArrayList<>();
        while (iterator.hasNext()) {
            conditionList.add(iterator.next());
        }
        //拿到所有重复的论文的列表
        List<Graduation> resultList = graduationMapper.getRepetitionThesisList(conditionList);
        return resultList;
    }

    @Override
    public boolean changeThesisState(GraduationInfo graduationInfo) {
        Graduation graduation = new Graduation();
        BeanUtils.copyProperties(graduationInfo, graduation);
        return this.updateById(graduation);
    }

    /**
    * 用动态规划算法计算两个字符串之间的编辑距离
    * */
    public static int editDistance(String str1, String str2) {
        //获取两个字符串的长度
        int m = str1.length();
        int n = str2.length();
        //创建一个二维数组来存储编辑距离
        int[][] dp = new int[m + 1][n + 1];
        //初始化边间条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        //计算编辑距离
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果当前字符相同，则编辑距离等于左上角的值
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 否则编辑距离等于左边、上边、左上角三个位置的最小值加一
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
    * 计算相似度百分比
    * */
    public static double similarityPercentage(String str1, String str2) {
        int distance = editDistance(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());
        return 1.0 - ((double) distance / maxLength);
    }

    /**
    * 计算字符串集合中每对字符串的相似度，返回重复率达到50%以上的论文
    * */
    public static Set<String> calculateSimilarity(List<String> stringSet) {
        Set<String> resultSet = new HashSet<>();
        int n = stringSet.size();
        for (int i = 0; i < n; i++) {
            double similarity = 0;
            for (int j = i + 1; j < n; j++) {
                String str1 = stringSet.get(i);
                String str2 = stringSet.get(j);
                similarity = similarityPercentage(str1, str2);
                if (similarity >= 0.5 ) {
                    resultSet.add(str1);
                    resultSet.add(str2);
                }
            }
        }
        return resultSet;
    }


}
