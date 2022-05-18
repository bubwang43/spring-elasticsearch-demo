package com.anqi.es.mapper;

import com.anqi.es.entity.DocumentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DocumentEntity record);

    int insertSelective(DocumentEntity record);

    DocumentEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DocumentEntity record);

    int updateByPrimaryKey(DocumentEntity record);
}