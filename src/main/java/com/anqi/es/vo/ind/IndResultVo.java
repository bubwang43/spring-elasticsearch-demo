package com.anqi.es.vo.ind;

import lombok.Data;

import java.util.List;

@Data
public class IndResultVo {
    private String indId;
    private String indCName;
    private String indFrequency;
    private String indScope;
    private String dataSource;
    private String indDescription;
    private String indRaName;
    private Long indRaId;
    private String dataUpdateDate;
    private String dataBeginDate;
    private String indUnit;
    private Long nodeId;
    private Long orderNumber;
    private List<IndValueVo> indValues;
}
