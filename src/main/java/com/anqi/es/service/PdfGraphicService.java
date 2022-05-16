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
import java.util.List;

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
    public List<GraphicsEsEntity> search(String field, String key, int page, int size) throws IOException {
        SearchResponse response = restHighLevelClientService.search(field, key, page, size, "report-graphic");
//        return response.toString();
        List<GraphicsEsEntity> sources = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            GraphicsEsEntity graphicsEsEntity = JSON.parseObject(hit.getSourceAsString(), GraphicsEsEntity.class);
            sources.add(graphicsEsEntity);
//            System.out.println(hit.getSourceAsString());
        }
        return sources;
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
