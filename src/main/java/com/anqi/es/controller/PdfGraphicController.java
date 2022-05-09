package com.anqi.es.controller;

import com.anqi.es.service.PdfGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfGraphicController {
    @Autowired
    private PdfGraphicService pdfGraphicService;

    @PutMapping("/es/pdfgraphic")
    public String bulkCreatePdfGraphicDoc() throws IOException {
        return pdfGraphicService.bulkCreatePdfGraphicDoc();
    }
}
