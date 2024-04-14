package com.sicau.minordegreemanagement.common.utils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * </p>
 *
 * @author Wang mengna：2024/3/20
 * @since 2024-04-11
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    String description() default "";

    /** 读到空白行处理方式 true 结束此sheet页导入，false继续导入 */
    boolean ifNull() default true;

    /** 表头的位置 */
    int headerIndex() default 0;

    /** 数据行的下表位置 */
    int dataIndex() default 1;

    /** 起始sheet页的下标 */
    int startSheetIndex() default 0;
}
