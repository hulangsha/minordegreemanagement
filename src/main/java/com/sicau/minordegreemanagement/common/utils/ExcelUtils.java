package com.sicau.minordegreemanagement.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
//import com.study.test.util.note.Description;
//import com.study.test.util.note.Excel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 注释: excel工具类
 *
 * @author yangyongzhuo 2022/11/17 19:59
 */
@Slf4j
public class ExcelUtils {

    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    /**
     * 通过clsss，读取excel里面的数据，只要表头与Excel里面的notes一致就可以，不要关注顺序
     *
     * @param file  文件
     * @param clsss vo
     * @return vo集合
     * @return java.util.List<T>
     * @author yangyongzhuo 2022/11/17 19:59
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> clsss) {
        //开始执行时间
        long start = System.currentTimeMillis();

        Workbook workbook = null;
        //返回数据对象
        List<T> dataList = null;

        //先判断文件命名是否正确，再判断文件是哪种类型
        String fileName = file.getName();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
            throw new RuntimeException("Excel命名格式不正确!");
        }
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(EXCEL2007)) {
                workbook = new XSSFWorkbook(is);
            }
            if (fileName.endsWith(EXCEL2003)) {
                workbook = new HSSFWorkbook(is);
            }
            if (ObjectUtil.isEmpty(workbook)) {
                throw new RuntimeException("Excel格式不正确，未获取工作空间!");
            }

            dataList = new ArrayList<>();
            //通过反射获取注释类上的数据下标值
            Description annotation = clsss.getAnnotation(Description.class);
            //数据位置因为poi读取下标是从0开始，所以要减1
            int dataIndex = annotation.dataIndex() - 1;
            //是否忽略空行
            boolean ifNull = annotation.ifNull();

            // 类映射 注解，拿到文件字段名称
            Map<String, List<Field>> classMap = new HashMap<>();
            List<Field> fields = Stream.of(clsss.getDeclaredFields()).collect(Collectors.toList());
            fields.forEach(field -> {
                //
                Excel excel = field.getAnnotation(Excel.class);
                if (ObjectUtil.isEmpty(excel)) {
                    return;
                }
                String notes = excel.notes();
                if (StrUtil.isEmpty(notes)) {
                    return;
                }
                if (!classMap.containsKey(notes)) {
                    classMap.put(notes, new ArrayList<>());
                }
                field.setAccessible(true);
                classMap.get(notes).add(field);
            });
            // 获取字段对应的列号
            Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
            // 默认读取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            //
            boolean firstRow = true;
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                // 表头区域获取字段对应的列
                if (i < dataIndex) {
                    for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        String cellValue = getCellValue(cell);
                        if (classMap.containsKey(cellValue)) {
                            reflectionMap.put(j, classMap.get(cellValue));
                        }
                    }
                    firstRow = false;
                } else {
                    // 忽略空白行
                    if (row == null) {
                        continue;
                    }
                    try {
                        T t = clsss.newInstance();
                        // 判断是否为空白行
                        boolean allBlank = true;
                        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                            if (reflectionMap.containsKey(j)) {
                                Cell cell = row.getCell(j);
                                String cellValue = getCellValue(cell);
                                if (StringUtils.isNotBlank(cellValue)) {
                                    allBlank = false;
                                }
                                List<Field> fieldList = reflectionMap.get(j);
                                fieldList.forEach(x -> {
                                    try {
                                        handleField(t, cellValue, x);
                                    } catch (Exception e) {
                                        log.error(String.format("reflect field:%s value:%s exception!", x.getName(),
                                                cellValue), e);
                                    }
                                });
                            }
                        }
                        if (!allBlank) {
                            dataList.add(t);
                        } else {
                            //if is null return this import code block
                            if (ifNull) {
                                return dataList;
                            }
                            log.warn(String.format("row:%s is blank ignore!", i));
                        }
                    } catch (Exception e) {
                        log.error(String.format("parse row:%s exception!", i), e);
                    }
                }
            }
        } catch (Exception e) {
            log.error(String.format("parse excel exception!"), e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error(String.format("parse excel exception!"), e);
                }
            }
        }
        long end = System.currentTimeMillis();
        log.info("read excel cost {}s", (end - start) / 1000);
        return dataList;
    }

    /**
     * 注释: 获取数据原始类型
     *
     * @param t
     * @param value
     * @param field
     * @return void
     * @author yangyongzhuo 2022/11/25 13:25
     */
    private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        if (type == null || type == void.class || StringUtils.isBlank(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            // 数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, NumberUtils.toInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, NumberUtils.toLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, NumberUtils.toByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, NumberUtils.toShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, NumberUtils.toDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, NumberUtils.toFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, CharUtils.toChar(value));
            } else if (type == boolean.class) {
                field.set(t, BooleanUtils.toBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, BooleanUtils.toBoolean(value));
        } else if (type == Date.class) {
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }

    /**
     * 注释:  获取数据类型
     *
     * @param cell
     * @return java.lang.String
     * @author yangyongzhuo 2022/11/25 13:26
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_FORMULA) { // 表达式类型
            cellType = cell.getCachedFormulaResultType();
        }

        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                return DateUtil.format(date, "yyyy-MM-dd");
            } else {
                return new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimToEmpty(cell.getRichStringCellValue() + "");
        } else if (cellType == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }
    }

}


