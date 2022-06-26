package com.anqi.es.vo.ind;

import lombok.Data;

@Data
public class IndItemFilterObj {
    private String key;
    private long value;

    public IndItemFilterObj(String key, long value) {
        this.key = key;
        this.value = value;
    }
}
