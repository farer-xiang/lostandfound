package com.lostandfound.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostandfound.dao.AdminMapper;
import com.lostandfound.model.Admin;
import com.lostandfound.tools.JDK_MD5;

@Service("adminservice")
public class AdminServiceImp implements AdminService{
	@Resource
	AdminMapper Adminmapper;
	@Override
	public boolean login(String no, String password) {
		// TODO 自动生成的方法存根
		password=JDK_MD5.jdkMD5(password);
		Admin a=Adminmapper.selectByPrimaryKey(no);
		if(a!=null){
			if(a.getAdminid().equals(no)&&a.getAdminpass().equals(password))
				return true;
		}
		return false;
	}
	@Override
	public Admin getById(String no) {
		// TODO 自动生成的方法存根
		
		return Adminmapper.selectByPrimaryKey(no);
	}
	@Override
	public int updateByPrimaryKey(Admin record) {
		// TODO 自动生成的方法存根
		String psw=record.getAdminpass();
		psw=JDK_MD5.jdkMD5(psw);
		record.setAdminpass(psw);
		return Adminmapper.updateByPrimaryKey(record);
	}
	
}
