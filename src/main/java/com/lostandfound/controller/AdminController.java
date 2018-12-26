package com.lostandfound.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostandfound.dao.FoundMapper;
import com.lostandfound.dao.LostMapper;
import com.lostandfound.dao.TypeMapper;
import com.lostandfound.model.Admin;
import com.lostandfound.model.Found;
import com.lostandfound.model.Lost;
import com.lostandfound.model.Type;
import com.lostandfound.model.User;
import com.lostandfound.service.AdminService;
import com.lostandfound.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/admin")
@Scope("prototype")
public class AdminController {
	@Resource
	AdminService adminservice;
	@Resource
	UserService userservice;
	@Resource
	LostMapper lostmapper;
	@Resource
	FoundMapper foundmapper;
	@Resource
	TypeMapper typemapper;
	
	@RequestMapping(value = "/adminlost", method = RequestMethod.GET)
	public String getadminlost(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		return "admin/adminlost";
	}
	@RequestMapping(value = "/adminlost", method = RequestMethod.POST)
	public void adminlost(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        String type=request.getParameter("type");
        if(type.equals("0")){
	        List<Lost> losts=lostmapper.admingetAll();
	        
	        int intCount=losts.size()>0?losts.size():1;
	        object.put("dataCount", intCount);
	        int intallPage = 1;
	        // 计算总页数
	        if ((intCount % everyPageDataCount) == 0) {
	            intallPage = intCount / everyPageDataCount;
	        } else {
	            intallPage = intCount / everyPageDataCount + 1;
	        }
	        // 防止页码越界
	        if (pageIndex < 0) {
	            pageIndex = 0;
	        } else if (pageIndex >= intallPage) {
	            pageIndex = intallPage - 1;
	        }
	        object.put("pageIndex", pageIndex);
	        List<Lost> pageresult=lostmapper.adminselectByPage(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
	        Lost[] arr = null;
	        if(pageresult.size()>0){
	            arr=new Lost[pageresult.size()];
	            pageresult.toArray(arr);
	            object.put("myData", arr);
	        }
	        String[] date = new String[pageresult.size()];
			for(int i=0;i<pageresult.size();i++) {
				
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		        String time=format.format(arr[i].getLdate());
		        date[i]=time;
				
			}
			object.put("date", date);
        }
        else{
	        List<Lost> losts=lostmapper.admingetAllByType(Integer.valueOf(type));
	        
	        int intCount=losts.size()>0?losts.size():1;
	        object.put("dataCount", intCount);
	        int intallPage = 1;
	        // 计算总页数
	        if ((intCount % everyPageDataCount) == 0) {
	            intallPage = intCount / everyPageDataCount;
	        } else {
	            intallPage = intCount / everyPageDataCount + 1;
	        }
	        // 防止页码越界
	        if (pageIndex < 0) {
	            pageIndex = 0;
	        } else if (pageIndex >= intallPage) {
	            pageIndex = intallPage - 1;
	        }
	        object.put("pageIndex", pageIndex);
	        List<Lost> pageresult=lostmapper.adminselectByPageByType(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,Integer.valueOf(type));
	        Lost[] arr = null;
	        if(pageresult.size()>0){
	            arr=new Lost[pageresult.size()];
	            pageresult.toArray(arr);
	            object.put("myData", arr);
	        }
	        String[] date = new String[pageresult.size()];
			for(int i=0;i<pageresult.size();i++) {
				
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		        String time=format.format(arr[i].getLdate());
		        date[i]=time;
				
			}
			object.put("date", date);
        }
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/ldetail", method = RequestMethod.POST)
	public void ldetail(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		int lid=Integer.parseInt(request.getParameter("lid"));
        JSONObject object=new JSONObject();
        Lost lost=lostmapper.selectByPrimaryKey(Integer.valueOf(lid));
		object.put("lost", lost);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(lost.getLdate());
        object.put("time", time);
        User u=userservice.getById(lost.getLuid());
        object.put("user", u);
        Type type=typemapper.selectByPrimaryKey(lost.getLtid());
        object.put("type", type);
        PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/dellost/{lid}", method = RequestMethod.GET)
	public String dellost(HttpServletRequest request,HttpServletResponse response,@PathVariable String lid){
		try {
			lostmapper.deleteByPrimaryKey(Integer.valueOf(lid));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/adminlost";
	}
	
	@RequestMapping(value = "/adminfound", method = RequestMethod.GET)
	public String getadminfound(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		return "admin/adminfound";
	}
	@RequestMapping(value = "/adminfound", method = RequestMethod.POST)
	public void adminfound(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        String type=request.getParameter("type");
        if(type.equals("0")){
	        List<Found> founds=foundmapper.admingetAll();
	        
	        int intCount=founds.size()>0?founds.size():1;
	        object.put("dataCount", intCount);
	        int intallPage = 1;
	        // 计算总页数
	        if ((intCount % everyPageDataCount) == 0) {
	            intallPage = intCount / everyPageDataCount;
	        } else {
	            intallPage = intCount / everyPageDataCount + 1;
	        }
	        // 防止页码越界
	        if (pageIndex < 0) {
	            pageIndex = 0;
	        } else if (pageIndex >= intallPage) {
	            pageIndex = intallPage - 1;
	        }
	        object.put("pageIndex", pageIndex);
	        List<Found> pageresult=foundmapper.adminselectByPage(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
	        Found[] arr = null;
	        if(pageresult.size()>0){
	            arr=new Found[pageresult.size()];
	            pageresult.toArray(arr);
	            object.put("myData", arr);
	        }
	        String[] date = new String[pageresult.size()];
			for(int i=0;i<pageresult.size();i++) {
				
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		        String time=format.format(arr[i].getFdate());
		        date[i]=time;
				
			}
			object.put("date", date);
        }
        else{
	        List<Found> founds=foundmapper.admingetAllByType(Integer.valueOf(type));
	        
	        int intCount=founds.size()>0?founds.size():1;
	        object.put("dataCount", intCount);
	        int intallPage = 1;
	        // 计算总页数
	        if ((intCount % everyPageDataCount) == 0) {
	            intallPage = intCount / everyPageDataCount;
	        } else {
	            intallPage = intCount / everyPageDataCount + 1;
	        }
	        // 防止页码越界
	        if (pageIndex < 0) {
	            pageIndex = 0;
	        } else if (pageIndex >= intallPage) {
	            pageIndex = intallPage - 1;
	        }
	        object.put("pageIndex", pageIndex);
	        List<Found> pageresult=foundmapper.adminselectByPageByType(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,Integer.valueOf(type));
	        Found[] arr = null;
	        if(pageresult.size()>0){
	            arr=new Found[pageresult.size()];
	            pageresult.toArray(arr);
	            object.put("myData", arr);
	        }
	        String[] date = new String[pageresult.size()];
			for(int i=0;i<pageresult.size();i++) {
				
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		        String time=format.format(arr[i].getFdate());
		        date[i]=time;
				
			}
			object.put("date", date);
        }
		
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/fdetail", method = RequestMethod.POST)
	public void fdetail(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		int fid=Integer.parseInt(request.getParameter("fid"));
        JSONObject object=new JSONObject();
        Found found=foundmapper.selectByPrimaryKey(Integer.valueOf(fid));
		object.put("found", found);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(found.getFdate());
        object.put("time", time);
        User u=userservice.getById(found.getFuid());
        object.put("user", u);
        Type type=typemapper.selectByPrimaryKey(found.getFtid());
        object.put("type", type);
        PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delfound/{fid}", method = RequestMethod.GET)
	public String delfound(HttpServletRequest request,HttpServletResponse response,@PathVariable String fid){
		try {
			foundmapper.deleteByPrimaryKey(Integer.valueOf(fid));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/adminfound";
	}
	
	
	@RequestMapping(value = "/adminusers", method = RequestMethod.GET)
	public String getadminusers(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		return "admin/adminusers";
	}
	@RequestMapping(value = "/adminusers", method = RequestMethod.POST)
	public void adminusers(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));

        List<User> users=userservice.getAll();
        
        int intCount=users.size()>0?users.size():0;
        object.put("dataCount", intCount);
        int intallPage = 1;
        // 计算总页数
        if ((intCount % everyPageDataCount) == 0) {
            intallPage = intCount / everyPageDataCount;
        } else {
            intallPage = intCount / everyPageDataCount + 1;
        }
        // 防止页码越界
        if (pageIndex < 0) {
            pageIndex = 0;
        } else if (pageIndex >= intallPage) {
            pageIndex = intallPage - 1;
        }
        object.put("pageIndex", pageIndex);
        List<User> pageresult=userservice.selectByPage(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
        User[] arr = null;
        if(pageresult.size()>0){
            arr=new User[pageresult.size()];
            pageresult.toArray(arr);
            object.put("myData", arr);
        }
       
		
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/adminlock/{uid}", method = RequestMethod.GET)
	public String adminlock(HttpServletRequest request,HttpServletResponse response,@PathVariable String uid){
		User user=new User();
		user.setUid(uid);
		user.setStatus("1");
		try {
			userservice.updateByPrimaryKey(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/adminusers";
	}
	@RequestMapping(value = "/adminunlock/{uid}", method = RequestMethod.GET)
	public String adminunlock(HttpServletRequest request,HttpServletResponse response,@PathVariable String uid){
		
		User user=new User();
		user.setUid(uid);
		user.setStatus("0");
		try {
			userservice.updateByPrimaryKey(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/adminusers";
	}
	
	@RequestMapping(value = "/useradd", method = RequestMethod.GET)
	public String getuseradd(HttpServletRequest request,HttpServletResponse response){
		
		return "admin/adminuseradd";
	}
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	public String useradd(HttpServletRequest request,HttpServletResponse response,String stuno,String stuname,String stupass,String stuemail,String stutel){
		User user =new User();
		user.setEmail(stuemail);
		user.setName(stuname);
		user.setStatus("0");
		user.setTel(stutel);
		user.setUid(stuno);
		user.setUpassword(stupass);
		try {
			userservice.insert(user);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "添加失败！");
		}
		return "admin/adminuseradd";
	}
	
	@RequestMapping(value = "/userchange/{uid}", method = RequestMethod.GET)
	public String getuserchange(HttpServletRequest request,HttpServletResponse response,@PathVariable String uid){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		User user=userservice.getById(uid);
		request.setAttribute("user", user);
		return "admin/adminusercge";
	}
	@RequestMapping(value = "/userchange", method = RequestMethod.POST)
	public String userchange(HttpServletRequest request,HttpServletResponse response,String uid,String stuname,String stupass){
		User user=userservice.getById(uid);
		try {
			user.setName(stuname);
			user.setUpassword(stupass);
			userservice.updateByPrimaryKey(user);
			request.setAttribute("msg", "修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "修改失败！");
		}
		return "admin/adminusercge";
	}
	
	@RequestMapping(value = "/userdel/{uid}", method = RequestMethod.GET)
	public String userdel(HttpServletRequest request,HttpServletResponse response,@PathVariable String uid){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		try {
			userservice.deleteByPrimaryKey(uid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/adminusers";
	}
	
	@RequestMapping(value = "/changepsw", method = RequestMethod.GET)
	public String getchangepsw(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		return "admin/admincgpd";
	}
	@RequestMapping(value = "/changepsw", method = RequestMethod.POST)
	public String changepsw(HttpServletRequest request,HttpServletResponse response,String password1){
		String id=(String) request.getSession().getAttribute("no");
		Admin admin=adminservice.getById(id);
		admin.setAdminpass(password1);
		try {
			adminservice.updateByPrimaryKey(admin);
			request.setAttribute("msg", "修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "修改失败！");
		}
		return "admin/admincgpd";
	}
	
	@RequestMapping(value = "/admintop", method = RequestMethod.POST)
	public void getadmintop(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		
        JSONObject object=new JSONObject();
        
        List<Lost> lost=lostmapper.selectTOP6();
        List<Found> found=foundmapper.selectTOP6();
		/*System.out.println(lost.size());*/
		Lost[] arr1 = null;
        if(lost.size()>0){
            arr1=new Lost[lost.size()];
            lost.toArray(arr1);
            object.put("lost", arr1);
        }
		String[] date1 = new String[lost.size()];
		for(int i=0;i<lost.size();i++) {
			
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	        String time=format.format(arr1[i].getLdate());
	        date1[i]=time;
			
		}
		object.put("date1", date1);
		
		Found[] arr2 = null;
        if(lost.size()>0){
            arr2=new Found[found.size()];
            found.toArray(arr2);
            object.put("found", arr2);
        }
		String[] date2 = new String[found.size()];
		for(int i=0;i<found.size();i++) {
			
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	        String time=format.format(arr2[i].getFdate());
	        date2[i]=time;
			
		}
		object.put("date2", date2);
        
        PrintWriter out=null;
		try {
			out = response.getWriter();
			out.write(object.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/admintype", method = RequestMethod.GET)
	public String getadmintype(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		return "admin/admintype";
	}
	@RequestMapping(value = "/typedel", method = RequestMethod.POST)
	public String typedel(HttpServletRequest request,HttpServletResponse response,String type){
		try {
			List<Lost> l=lostmapper.getAllByType(Integer.valueOf(type));
			for(Lost lost:l){
				lost.setLtid(1);
				lostmapper.updateByPrimaryKeySelective(lost);
			}
			List<Found> f=foundmapper.getAllByType(Integer.valueOf(type));
			for(Found found:f){
				found.setFtid(1);
				foundmapper.updateByPrimaryKeySelective(found);
			}
			typemapper.deleteByPrimaryKey(Integer.valueOf(type));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/admin/admintype";
	}
	@RequestMapping(value = "/typeadd", method = RequestMethod.POST)
	public String typeadd(HttpServletRequest request,HttpServletResponse response,String type){
		try {
			Type t=new Type();
			t.setTypename(type);
			typemapper.insertSelective(t);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/admin/admintype";
	}
}
