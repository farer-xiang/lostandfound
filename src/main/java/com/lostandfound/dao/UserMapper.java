package com.lostandfound.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lostandfound.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAll();
    List<User> selectByPage(@Param("from") Integer from,@Param("count") Integer count);
}