package com.anqi.es.service;

import com.anqi.es.vo.Result;

import java.io.IOException;

public interface IPdfService {
    Result download(int start, int size) throws IOException;
}
