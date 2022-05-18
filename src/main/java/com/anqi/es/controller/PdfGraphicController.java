package com.anqi.es.controller;

import com.anqi.es.entity.DocumentEntity;
import com.anqi.es.mapper.DocumentMapper;
import com.anqi.es.service.impl.PdfGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/pdfgraphic")
public class PdfGraphicController {
    @Autowired
    private PdfGraphicService pdfGraphicService;
    @Resource
    private DocumentMapper documentMapper;

    @PutMapping("/graphics")
    public String bulkCreatePdfGraphicDoc() throws IOException {
        return pdfGraphicService.bulkCreatePdfGraphicDoc();
    }

    @GetMapping("/graphics")
    public Map<String, Object> search(@RequestParam("field") String field, @RequestParam("key") String key,
                                      @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws IOException {
        return pdfGraphicService.search(field, key, pageNumber, pageSize);
    }

    @GetMapping("/graphics/should")
    public Map<String, Object> search(@RequestParam("content") String content,
                                      @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws IOException {
        return pdfGraphicService.shouldGroupSearch(content, pageNumber, pageSize);
    }

    @GetMapping("/test")
    public DocumentEntity test() {
        DocumentEntity documentEntity = documentMapper.selectByPrimaryKey(1L);
        return documentEntity;
    }
}
