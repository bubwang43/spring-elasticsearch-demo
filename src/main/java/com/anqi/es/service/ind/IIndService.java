package com.anqi.es.service.ind;

import com.anqi.es.entity.ind.IndQueryIn;
import com.anqi.es.vo.EsResultVo;

import java.io.IOException;

public interface IIndService {
    EsResultVo search(IndQueryIn indQueryIn) throws IOException;
}
