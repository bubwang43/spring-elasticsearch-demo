package com.anqi.es.controller;

import com.anqi.es.service.PdfGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/pdfgraphic")
public class PdfGraphicController {
    @Autowired
    private PdfGraphicService pdfGraphicService;

    @PutMapping("/graphics")
    public String bulkCreatePdfGraphicDoc() throws IOException {
        return pdfGraphicService.bulkCreatePdfGraphicDoc();
    }

    @GetMapping("/graphics")
    public Map<String, Object> search(@RequestParam("field") String field, @RequestParam("key") String key,
                                      @RequestParam("pageNumber") int pageNumber, @RequestParam("pagerSize") int pagerSize) throws IOException {
        return pdfGraphicService.search(field, key, pageNumber, pagerSize);
    }
}
