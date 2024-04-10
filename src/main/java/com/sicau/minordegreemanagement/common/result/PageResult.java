package com.sicau.minordegreemanagement.common.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 */
@Data
public class PageResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private List<T> list;

    private Long currentPage;
    private Long pageSize;
    private Long total;
    private Long pages;

    public PageResult(Long currentPage,Long pageSize,Long total,Long pages,List<T> list){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

}
