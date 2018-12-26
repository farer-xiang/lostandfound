package com.lostandfound.service;

import com.lostandfound.model.Admin;

public interface AdminService {
	public Admin getById(String no);
	public boolean login(String no, String password);
	public int updateByPrimaryKey(Admin record);
}
