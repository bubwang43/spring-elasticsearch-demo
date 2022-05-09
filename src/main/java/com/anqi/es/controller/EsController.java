package com.anqi.es.controller;

import com.alibaba.fastjson.JSON;
import com.anqi.es.highclient.RestHighLevelClientService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anqi
 */
@RestController
public class EsController {
    @Autowired
    RestHighLevelClientService service;

    @GetMapping("/es")
    public String testHigh(HttpServletResponse httpServletResponse) throws IOException{
        String source = "{\n" +
                "  \"name\" : \"耐苦无领运动半袖\",\n" +
                "  \"price\" : 300,\n" +
                "  \"num\" : 800,\n" +
                "  \"date\" : \"2019-07-28\"\n" +
                "}";

        IndexResponse response = service.addDoc("idx_clouthing", source);

        httpServletResponse.getWriter().println();

        return response.toString();
    }

    @GetMapping("/es/search")
    public String search(@RequestParam("field") String field, @RequestParam("key") String key,
                                 @RequestParam("page") int page, @RequestParam("size") int size,
                                 @RequestParam("indexNames") String indexNames) throws IOException {
        SearchResponse response = service.search(field, key, page, size, indexNames);
//        return response.toString();
        List<String> sources = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            sources.add(hit.getSourceAsString());
//            System.out.println(hit.getSourceAsString());
        }
        return JSON.toJSONString(sources);
    }
}
