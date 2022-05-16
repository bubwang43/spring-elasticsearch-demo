package com.anqi.es.service;

import java.io.IOException;
import java.util.Map;

public interface IPdfGraphicService {
    String bulkCreatePdfGraphicDoc() throws IOException;

    Map<String, Object> search(String field, String key, int pageNumber, int pageSize) throws IOException;

    Map<String, Object> shouldGroupSearch(String content, int pageNumber, int pageSize) throws IOException;
}
