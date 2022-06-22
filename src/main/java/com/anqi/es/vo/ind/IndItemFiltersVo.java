package com.anqi.es.vo.ind;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class IndItemFiltersVo {
    private Set<IndItemFilterObj> indScope = new HashSet<>();

    private Set<IndItemFilterObj> dataBeginDate = new HashSet<>();

    private Set<IndItemFilterObj> indFrequency = new HashSet<>();

    private Set<IndItemFilterObj> indUnit = new HashSet<>();

    private Set<IndItemFilterObj> dataSource = new HashSet<>();
}
