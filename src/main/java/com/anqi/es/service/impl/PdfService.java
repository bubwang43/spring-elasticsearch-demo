package com.anqi.es.service.impl;

import com.anqi.es.entity.DocumentEntity;
import com.anqi.es.mapper.DocumentMapper;
import com.anqi.es.service.IPdfService;
import com.anqi.es.vo.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService implements IPdfService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Result download(int start, int size) throws IOException {
        List<DocumentEntity> documentEntities = documentMapper.selectByRange(start, size);
        for (DocumentEntity documentEntity : documentEntities) {
            String fileUrl = documentEntity.getFileUrl();
            if (!StringUtils.isEmpty(fileUrl)) {
                fileUrl = fileUrl.replace("getPdf", "downloadPdf");
                fileUrl = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
                fileUrl  = fileUrl + "/" + documentEntity.getId();
                String fileURI = "https://117.78.18.175/portal-dev" + fileUrl;
                documentEntity.setFileUrl(fileURI);
            }
        }
        List<Long> failIds = new ArrayList<>();
        documentEntities.parallelStream().forEach(documentEntity -> {
            String url = documentEntity.getFileUrl();
            try {
                ResponseEntity<byte[]> responseEntity = getResponseEntity(url);
                String savePath = "D:\\pdfdownload\\" + documentEntity.getTitle() + ".pdf";
                byte2file(savePath, responseEntity.getBody());
            }catch (Exception e) {
                failIds.add(documentEntity.getId());
                System.out.println("url = " + url + "下载失败");
                e.printStackTrace();
            }
        });

        if (failIds.size() == 0) {
            return Result.SUCCESS;
        }
        if (failIds.size() == documentEntities.size()) {
            return Result.DOWNLOAD_ERROR;
        }
        return new Result(0, "部分下载失败", failIds);
    }

    public ResponseEntity<byte[]> getResponseEntity(String url) throws IOException {
        //        String url = "http://static.cninfo.com.cn/finalpage/2008-03-27/38334292.PDF";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "");
        HttpEntity<String> entity = new HttpEntity<>("", headers);//将请求头传入请求体种
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        return response;
    }

    public static void byte2file(String savePath, byte[] data) throws Exception{
        FileOutputStream outputStream = new FileOutputStream(new File(savePath));
        outputStream.write(data);
        outputStream.close();
    }
}
