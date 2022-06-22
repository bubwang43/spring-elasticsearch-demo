package com.anqi.es.entity.ind;

import lombok.Data;

@Data
public class IndQueryIn {
    /**
     * 指标中文名
     */
    private String indCName;
    /**
     * 统计方式
     */
    private String indScope;
    /**
     * 起始时间
     */
    private String dataBeginDate;
    /**
     * 频率
     */
    private String indFrequency;
    /**
     * 单位
     */
    private String indUnit;
    /**
     * 数据来源
     */
    private String dataSource;

    private int pageIndex;

    private int pageSize;
}
