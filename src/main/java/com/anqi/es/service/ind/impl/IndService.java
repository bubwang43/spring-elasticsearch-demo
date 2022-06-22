package com.anqi.es.service.ind.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anqi.es.entity.ind.IndQueryIn;
import com.anqi.es.highclient.RestHighLevelClientService;
import com.anqi.es.service.ind.IIndService;
import com.anqi.es.vo.EsResultVo;
import com.anqi.es.vo.ind.IndItemFilterObj;
import com.anqi.es.vo.ind.IndItemFiltersVo;
import com.anqi.es.vo.ind.IndResultVo;
import com.anqi.es.vo.ind.IndValueVo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IndService implements IIndService {
    @Autowired
    RestHighLevelClientService restHighLevelClientService;

    private String indexName = "ind_der_base_value";

    @Override
    public EsResultVo search(IndQueryIn in) throws IOException {
        int page = (in.getPageIndex() - 1) * in.getPageSize();
        Map<String, String> filedKeyMap = getFiledKeyMap(in);
        SearchResponse response = restHighLevelClientService.mustGroupSearch(filedKeyMap, page, in.getPageSize(), indexName);
        List<IndResultVo> indResultVoList = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            IndResultVo indResultVo = JSON.parseObject(hit.getSourceAsString(), IndResultVo.class);
            //格式化valueList
            indResultVo.setIndValues(new ArrayList<>());
            JSONObject jsonObject = JSON.parseObject(hit.getSourceAsString());
            String valueList = jsonObject.getString("valueList");
            if (!StringUtils.isEmpty(valueList)) {
                List<IndValueVo> indValueVos = JSON.parseArray(valueList, IndValueVo.class);
                indResultVo.setIndValues(indValueVos);
            }
            indResultVoList.add(indResultVo);
        }
        long total = response.getHits().getTotalHits().value;
        Long totalPages = (total + in.getPageSize() -1) / in.getPageSize();
        EsResultVo esResultVo = new EsResultVo(indResultVoList, in.getPageIndex(), in.getPageSize(),total, totalPages);

        return esResultVo;
    }

    @Override
    public IndItemFiltersVo getIndItemFiltersVo(String indCName) throws IOException {
        IndItemFiltersVo result = new IndItemFiltersVo();
        if (StringUtils.isEmpty(indCName)) {
            return  result;
        }
        SearchResponse response = restHighLevelClientService.search("indCName", indCName, indexName);
        Set<String> indScopeSet = new HashSet<>();
        Set<String> dataBeginDateSet = new HashSet<>();
        Set<String> indFrequencySet = new HashSet<>();
        Set<String> indUnitSet = new HashSet<>();
        Set<String> dataSourceSet = new HashSet<>();
        for (SearchHit hit : response.getHits()) {
            IndResultVo indResultVo = JSON.parseObject(hit.getSourceAsString(), IndResultVo.class);
            indScopeSet.add(indResultVo.getIndScope());
            dataBeginDateSet.add(indResultVo.getDataBeginDate());
            indFrequencySet.add(indResultVo.getIndFrequency());
            indUnitSet.add(indResultVo.getIndUnit());
            dataSourceSet.add(indResultVo.getDataSource());
        }
        Set<IndItemFilterObj> indScope = indScopeSet.stream().filter(v -> v != null).map(v -> {
            IndItemFilterObj indItemFilterObj = new IndItemFilterObj();
            indItemFilterObj.setId(v);
            indItemFilterObj.setName(v);
            return indItemFilterObj;
        }).collect(Collectors.toSet());
        result.setIndScope(indScope);

        Set<IndItemFilterObj> dataBeginDate = dataBeginDateSet.stream().filter(v -> v != null).map(v -> {
            IndItemFilterObj indItemFilterObj = new IndItemFilterObj();
            indItemFilterObj.setId(v);
            indItemFilterObj.setName(v);
            return indItemFilterObj;
        }).collect(Collectors.toSet());
        result.setDataBeginDate(dataBeginDate);

        Set<IndItemFilterObj> indFrequency = indFrequencySet.stream().filter(v -> v != null).map(v -> {
            IndItemFilterObj indItemFilterObj = new IndItemFilterObj();
            indItemFilterObj.setId(v);
            indItemFilterObj.setName(v);
            return indItemFilterObj;
        }).collect(Collectors.toSet());
        result.setIndFrequency(indFrequency);

        Set<IndItemFilterObj> indUnit = indUnitSet.stream().filter(v -> v != null).map(v -> {
            IndItemFilterObj indItemFilterObj = new IndItemFilterObj();
            indItemFilterObj.setId(v);
            indItemFilterObj.setName(v);
            return indItemFilterObj;
        }).collect(Collectors.toSet());
        result.setIndUnit(indUnit);

        Set<IndItemFilterObj> dataSource = dataSourceSet.stream().filter(v -> v != null).map(v -> {
            IndItemFilterObj indItemFilterObj = new IndItemFilterObj();
            indItemFilterObj.setId(v);
            indItemFilterObj.setName(v);
            return indItemFilterObj;
        }).collect(Collectors.toSet());
        result.setDataSource(dataSource);

        return result;
    }

    private Map<String, String> getFiledKeyMap(IndQueryIn in) {
        Map<String, String> filedKeyMap = new HashMap<>();
        if (in.getIndCName() != null) {
            filedKeyMap.put("indCName", in.getIndCName());
        }
        if (!StringUtils.isEmpty(in.getIndScope())) {
            filedKeyMap.put("indScope", in.getIndScope());
        }
        if (!StringUtils.isEmpty(in.getDataBeginDate())) {
            filedKeyMap.put("dataBeginDate", in.getDataBeginDate());
        }
        if (!StringUtils.isEmpty(in.getIndFrequency())) {
            filedKeyMap.put("indFrequency", in.getIndFrequency());
        }
        if (!StringUtils.isEmpty(in.getIndUnit())) {
            filedKeyMap.put("indUnit", in.getIndUnit());
        }
        if (!StringUtils.isEmpty(in.getDataSource())) {
            filedKeyMap.put("dataSource", in.getDataSource());
        }
        return filedKeyMap;
    }
}
