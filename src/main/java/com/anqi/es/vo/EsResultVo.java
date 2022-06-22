package com.anqi.es.vo;

import lombok.Data;

import java.util.List;

@Data
public class EsResultVo {
    private List list;

    private int pageIndex;

    private int pageSize;

    private long total;

    private long totalPages;

    public EsResultVo(List list, int pageIndex, int pageSize, long total, long totalPages) {
        this.list = list;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPages = totalPages;
    }

}
