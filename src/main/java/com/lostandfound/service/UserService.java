package com.lostandfound.service;

import java.util.List;

import com.lostandfound.model.User;

public interface UserService {
	public User getById(String no);
	public boolean login(String no, String password);
	public int updateByPrimaryKey(User record);
	public List<User> getAll();
	public List<User> selectByPage(Integer from, int count);
	public int deleteByPrimaryKey(String uid);
	public int insert(User record);
}
