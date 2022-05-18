package com.anqi.es.mapper;

import com.anqi.es.entity.DocumentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DocumentEntity record);

    int insertSelective(DocumentEntity record);

    DocumentEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DocumentEntity record);

    int updateByPrimaryKey(DocumentEntity record);

    List<DocumentEntity> selectByRange(@Param("start") int start, @Param("size") int size);
}