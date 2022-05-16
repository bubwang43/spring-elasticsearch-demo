package com.anqi.es.service;

import com.alibaba.fastjson.JSON;
import com.anqi.es.entity.GraphicsEsEntity;
import com.anqi.es.highclient.RestHighLevelClientService;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfGraphicService implements IPdfGraphicService{
    @Value("${graphic.hostport}")
    private String graphicHostport;

    private String basePath = "D:\\cicc\\report_graphic";

    @Autowired
    RestHighLevelClientService restHighLevelClientService;

    public String bulkCreatePdfGraphicDoc() throws IOException {
        File dir = new File(basePath);
        List<GraphicsEsEntity> list = new ArrayList<>();
        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");
            return "目录不存在";
        }
        File[] reportTitleDirs = dir.listFiles();
        for (File reportTitleDir : reportTitleDirs) {
            getGraphicsEsData(reportTitleDir, list);
        }
        BulkResponse bulkResponse = restHighLevelClientService.bulkCreateDoc("report-graphic", true, JSON.toJSONString(list));
        if (bulkResponse.status().getStatus() == 200) {
            return "写入es成功";
        }
        return "写入es失败";
    }

    @Override
    public Map<String, Object> search(String field, String key, int pageNumber, int pageSize) throws IOException {
        int page = (pageNumber - 1) * pageSize;
        SearchResponse response = restHighLevelClientService.search(field, key, page, pageSize, "report-graphic");
        List<GraphicsEsEntity> graphicsEsEntityList = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            GraphicsEsEntity graphicsEsEntity = JSON.parseObject(hit.getSourceAsString(), GraphicsEsEntity.class);
            graphicsEsEntityList.add(graphicsEsEntity);
        }
        long total = response.getHits().getTotalHits().value;
        Map<String, Object> result = new HashMap<>();
        result.put("list", graphicsEsEntityList);
        result.put("pageNumber", pageNumber);
        result.put("pageSize", pageSize);
        result.put("total", total);
        Long totalPages = (total + pageSize -1) / pageSize;
        result.put("totalPages", totalPages);
        return result;
    }

    private void getGraphicsEsData(File reportTitleDir, List<GraphicsEsEntity> list) {
        // 获取文件列表
        File[] graphicList = reportTitleDir.listFiles();
        assert graphicList != null;
        for (File graphic : graphicList) {
            if (graphic.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
//                    getAllFile(file, allFileList);
            } else {
                // 如果是文件则将其加入到文件数组中
                GraphicsEsEntity graphicsEsEntity = new GraphicsEsEntity();
                graphicsEsEntity.setReportTitle(reportTitleDir.getName());
                String title = graphic.getName();
                title = title.substring(0, title.lastIndexOf("."));
                graphicsEsEntity.setTitle(title);
                graphicsEsEntity.setUrl(graphicHostport + "/report_graphic/" + reportTitleDir.getName() + "/" + graphic.getName());
                list.add(graphicsEsEntity);
            }
        }
    }

}
