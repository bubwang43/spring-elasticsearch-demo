package com.anqi.es.controller;

import com.anqi.es.service.IPdfService;
import com.anqi.es.vo.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/pdf")
public class PdfController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private IPdfService pdfService;

    @RequestMapping("/downloadPdf")
    public Result download(@RequestParam("start") int start, @RequestParam("size") int size) throws IOException {
        return pdfService.download(start,size);

    }

    @RequestMapping("/download")
    public ResponseEntity download(@RequestParam("url") String url) throws IOException {
//        byte[] body = getForEntity(path);
//        ResponseEntity responseEntity = ResponseEntity.ok()
//                .header("Content-Disposition", "attachment;filename=test.pdf")//path可以获取文件名
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(body.length)
//                .body(body);
//        byte2file("D:\\test.pdf", body);
//        return responseEntity;

        ResponseEntity<byte[]> responseEntity = getResponseEntity(url);
        byte[] body = responseEntity.getBody();
        byte2file("D:\\test.pdf", body);
        return responseEntity;
    }

    public byte[] getForEntity(String path) throws IOException {
        return restTemplate.getForEntity("http://static.cninfo.com.cn/finalpage/2008-03-27/38334292.PDF", byte[].class).getBody();
    }

    public ResponseEntity<byte[]> getResponseEntity(String url) throws IOException {
        try {
            //        String url = "http://static.cninfo.com.cn/finalpage/2008-03-27/38334292.PDF";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie", "");
            HttpEntity<String> entity = new HttpEntity<>("", headers);//将请求头传入请求体种
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("url = " + url + "下载失败");
        }

        return null;
    }

    public static void byte2file(String path, byte[] data) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            outputStream.write(data);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}