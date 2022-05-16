package com.anqi.es.service;

import com.anqi.es.entity.GraphicsEsEntity;

import java.io.IOException;
import java.util.List;

public interface IPdfGraphicService {
    String bulkCreatePdfGraphicDoc() throws IOException;

    List<GraphicsEsEntity> search(String field, String key, int page, int size) throws IOException;
}
