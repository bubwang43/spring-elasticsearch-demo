package com.anqi.es.service.ind.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anqi.es.entity.ind.IndQueryIn;
import com.anqi.es.highclient.RestHighLevelClientService;
import com.anqi.es.service.ind.IIndService;
import com.anqi.es.vo.EsResultVo;
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
import java.util.List;
import java.util.Map;

@Service
public class IndService implements IIndService {
    @Autowired
    RestHighLevelClientService restHighLevelClientService;

    @Override
    public EsResultVo search(IndQueryIn in) throws IOException {
        int page = (in.getPageIndex() - 1) * in.getPageSize();
        Map<String, String> filedKeyMap = getFiledKeyMap(in);
        SearchResponse response = restHighLevelClientService.mustGroupSearch(filedKeyMap, page, in.getPageSize(), "ind_der_base_value");
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
