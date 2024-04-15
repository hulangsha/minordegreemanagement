package com.sicau.minordegreemanagement.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static <T> List<T> importExcel(Workbook workbook, Class<?> c){
        List<T> dataList = new ArrayList<>();
        try {
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            T o = null;
            for (Row row : sheet) {
                Row row1 = sheet.getRow(i + 1);
                if(row1 != null){
                    o = (T) c.newInstance();
                    Field[] declaredFields = c.getDeclaredFields();
                    for (int i1 = 0; i1 < declaredFields.length; i1++) {
                        String name = declaredFields[i1].getName();
                        Field declaredField1 = o.getClass().getDeclaredField(name);
                        declaredField1.setAccessible(true);
                        Cell cell = row1.getCell(i1);
                        String type = declaredFields[i1].getType().getName();
                        String value = String.valueOf(cell);
                        if(StringUtils.equals(type,"int") || StringUtils.equals(type,"Integer")){
                            declaredField1.set(o,Integer.parseInt(value));
                        } else if(StringUtils.equals(type,"java.lang.String") || StringUtils.equals(type,"char") || StringUtils.equals(type,"Character") ||
                                StringUtils.equals(type,"byte") || StringUtils.equals(type,"Byte")){
                            declaredField1.set(o,value);
                        } else if(StringUtils.equals(type,"boolean") || StringUtils.equals(type,"Boolean")){
                            declaredField1.set(o,Boolean.valueOf(value));
                        } else if(StringUtils.equals(type,"double") || StringUtils.equals(type,"Double")){
                            declaredField1.set(o,Double.valueOf(value));
                        } else if (StringUtils.equals(type,"long") || StringUtils.equals(type,"Long")) {
                            declaredField1.set(o,Long.valueOf(value));
                        } else if(StringUtils.equals(type,"short") || StringUtils.equals(type,"Short")){
                            declaredField1.set(o,Short.valueOf(value));
                        } else if(StringUtils.equals(type,"float") || StringUtils.equals(type,"Float")){
                            declaredField1.set(o,Float.valueOf(value));
                        }
                    }
                }
                dataList.add(o);
            }
            workbook.close();
            return dataList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
}
