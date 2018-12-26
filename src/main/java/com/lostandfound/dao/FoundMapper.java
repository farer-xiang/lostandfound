package com.lostandfound.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lostandfound.model.Found;

public interface FoundMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Found record);

    int insertSelective(Found record);

    Found selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Found record);

    int updateByPrimaryKey(Found record);
    
    List<Found> getAll();
    List<Found> selectByPage(@Param("from") Integer from,@Param("count") Integer count);
    List<Found> admingetAll();
    List<Found> adminselectByPage(@Param("from") Integer from,@Param("count") Integer count);
    
    List<Found> getAllSelective(@Param("record") String record);
    List<Found> selectByPageSelective(@Param("from") Integer from,@Param("count") Integer count,@Param("record") String record);
    
    List<Found> getAllById(@Param("fuid")String fuid);
    List<Found> selectByPageById(@Param("from") Integer from,@Param("count") Integer count,@Param("fuid")String fuid);
    
    List<Found> getAllByType(@Param("ftid") Integer ftid);
    List<Found> selectByPageByType(@Param("from") Integer from,@Param("count") Integer count,@Param("ftid") Integer ftid);
    List<Found> admingetAllByType(@Param("ftid") Integer ftid);
    List<Found> adminselectByPageByType(@Param("from") Integer from,@Param("count") Integer count,@Param("ftid") Integer ftid);
    
    List<Found> selectTOP6();
}