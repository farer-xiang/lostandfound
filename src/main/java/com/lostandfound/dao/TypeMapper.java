package com.lostandfound.dao;

import java.util.List;

import com.lostandfound.model.Type;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
    
    List<Type> getAll();
}