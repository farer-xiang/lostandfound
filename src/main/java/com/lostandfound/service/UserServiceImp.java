package com.lostandfound.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostandfound.dao.UserMapper;
import com.lostandfound.model.User;
import com.lostandfound.tools.JDK_MD5;

@Service("userservice")
public class UserServiceImp implements UserService {
	@Resource
	UserMapper Usermapper;
	@Override
	public boolean login(String no, String password) {
		// TODO 自动生成的方法存根
		password=JDK_MD5.jdkMD5(password);
		User u=Usermapper.selectByPrimaryKey(no);
		if(u!=null){
			if(u.getUid().equals(no)&&u.getUpassword().equals(password))
				return true;
		}
		return false;
	}
	@Override
	public User getById(String no) {
		// TODO 自动生成的方法存根
		return Usermapper.selectByPrimaryKey(no);
	}
	public int updateByPrimaryKey(User record){
		if(record.getUpassword()!=null){
			String psw=record.getUpassword();
			psw=JDK_MD5.jdkMD5(psw);
			record.setUpassword(psw);
		}
		return Usermapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<User> getAll() {
		// TODO 自动生成的方法存根
		return Usermapper.getAll();
	}
	@Override
	public List<User> selectByPage(Integer from, int count) {
		// TODO 自动生成的方法存根
		return Usermapper.selectByPage(from, count);
	}
	@Override
	public int deleteByPrimaryKey(String uid) {
		// TODO 自动生成的方法存根
		return Usermapper.deleteByPrimaryKey(uid);
	}
	@Override
	public int insert(User record) {
		// TODO 自动生成的方法存根
		String psw=record.getUpassword();
		psw=JDK_MD5.jdkMD5(psw);
		record.setUpassword(psw);
		return Usermapper.insertSelective(record);
	}

}
