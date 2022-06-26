package com.anqi.es.vo.ind;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IndItemFiltersVo {
    private List<IndItemFilterObj> indScope = new ArrayList<>();

    private List<IndItemFilterObj> dataBeginDate = new ArrayList<>();

    private List<IndItemFilterObj> indFrequency = new ArrayList<>();

    private List<IndItemFilterObj> indUnit = new ArrayList<>();

    private List<IndItemFilterObj> dataSource = new ArrayList<>();
}
