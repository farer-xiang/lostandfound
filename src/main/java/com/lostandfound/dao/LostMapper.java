package com.lostandfound.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lostandfound.model.Lost;

public interface LostMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(Lost record);

    int insertSelective(Lost record);

    Lost selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(Lost record);

    int updateByPrimaryKey(Lost record);

    List<Lost> getAll();
    List<Lost> selectByPage(@Param("from") Integer from,@Param("count") Integer count);
    List<Lost> admingetAll();
    List<Lost> adminselectByPage(@Param("from") Integer from,@Param("count") Integer count);
    
    List<Lost> getAllSelective(@Param("record") String record);
    List<Lost> selectByPageSelective(@Param("from") Integer from,@Param("count") Integer count,@Param("record") String record);
    
    List<Lost> getAllById(@Param("luid")String luid);
    List<Lost> selectByPageById(@Param("from") Integer from,@Param("count") Integer count,@Param("luid")String luid);
    
    List<Lost> getAllByType(@Param("ltid") Integer ltid);
    List<Lost> selectByPageByType(@Param("from") Integer from,@Param("count") Integer count,@Param("ltid") Integer ltid);
    List<Lost> admingetAllByType(@Param("ltid") Integer ltid);
    List<Lost> adminselectByPageByType(@Param("from") Integer from,@Param("count") Integer count,@Param("ltid") Integer ltid);
    
    List<Lost> selectTOP6();
}