package com.lostandfound.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostandfound.dao.FoundMapper;
import com.lostandfound.dao.LostMapper;
import com.lostandfound.dao.TypeMapper;
import com.lostandfound.dao.UserMapper;
import com.lostandfound.model.Found;
import com.lostandfound.model.Lost;
import com.lostandfound.model.Type;
import com.lostandfound.model.Found;
import com.lostandfound.model.User;
import com.lostandfound.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/user")
@Scope("prototype")
public class UserController {
	@Resource
	UserService userservice;
	@Resource
	LostMapper lostmapper;
	@Resource
	FoundMapper foundmapper;
	@Resource
	TypeMapper typemapper;
	
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	public void gettype(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        JSONObject object=new JSONObject();
        List<Type> types=typemapper.getAll();
        Type[] arr = null;
        if(types.size()>0){
            arr=new Type[types.size()];
            types.toArray(arr);
            object.put("types", arr);
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
	
	@RequestMapping(value = "/userlost", method = RequestMethod.GET)
	public String getuserlost(HttpServletRequest request,HttpServletResponse response){
		return "user/userlost";
	}
	@RequestMapping(value = "/userlost", method = RequestMethod.POST)
	public void userlost(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        String type=request.getParameter("type");
        if(type.equals("0")){
	        List<Lost> losts=lostmapper.getAll();
	        
	        int intCount=losts.size();
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
	        List<Lost> pageresult=lostmapper.selectByPage(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
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
	        List<Lost> losts=lostmapper.getAllByType(Integer.valueOf(type));
	        
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
	        List<Lost> pageresult=lostmapper.selectByPageByType(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,Integer.valueOf(type));
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
	@RequestMapping(value = "/ldetail/{lid}", method = RequestMethod.GET)
	public String getldetail(@PathVariable String lid,HttpServletRequest request,HttpServletResponse response){
		Lost lost=lostmapper.selectByPrimaryKey(Integer.valueOf(lid));
		request.setAttribute("lost", lost);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(lost.getLdate());
        request.setAttribute("time", time);
        User u=userservice.getById(lost.getLuid());
        request.setAttribute("user", u);
        Type type=typemapper.selectByPrimaryKey(lost.getLtid());
        request.setAttribute("type", type);
		return "user/ldetail";
	}
	@RequestMapping(value = "/userfound", method = RequestMethod.GET)
	public String getuserfound(HttpServletRequest request,HttpServletResponse response){
		return "user/userfound";
	}
	@RequestMapping(value = "/userfound", method = RequestMethod.POST)
	public void userfound(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        String type=request.getParameter("type");
        if(type.equals("0")){
	        List<Found> founds=foundmapper.getAll();
	        
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
	        List<Found> pageresult=foundmapper.selectByPage(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
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
	        List<Found> founds=foundmapper.getAllByType(Integer.valueOf(type));
	        
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
	        List<Found> pageresult=foundmapper.selectByPageByType(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,Integer.valueOf(type));
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
	@RequestMapping(value = "/fdetail/{fid}", method = RequestMethod.GET)
	public String getfdetail(@PathVariable String fid,HttpServletRequest request,HttpServletResponse response){
		Found found=foundmapper.selectByPrimaryKey(Integer.valueOf(fid));
		request.setAttribute("found", found);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(found.getFdate());
        request.setAttribute("time", time);
        User u=userservice.getById(found.getFuid());
        request.setAttribute("user", u);
        Type type=typemapper.selectByPrimaryKey(found.getFtid());
        request.setAttribute("type", type);		
		return "user/fdetail";
	}
	
	@RequestMapping(value = "/newlost", method = RequestMethod.GET)
	public String getnewlost(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Uname"))==""||request.getSession().getAttribute("Uname")==null)
		{
			return "login";
		}
		List<Type> types=typemapper.getAll();
		request.setAttribute("types", types);
		String no = (String) request.getSession().getAttribute("no");
		User user=userservice.getById(no);
		request.setAttribute("user", user);
		return "user/newlost";
	}
	@RequestMapping(value = "/newlost", method = RequestMethod.POST)
	public String newlost(HttpServletRequest request,HttpServletResponse response,String describe,String date,String place,String lostimg,String type){
		
		
		String uid=(String) request.getSession().getAttribute("no");
		/*System.out.println(uid);
		System.out.println(describe);
		System.out.println(date);
		System.out.println(place);
		System.out.println(lostimg);*/
		
		Lost l = new Lost();
		l.setLname(describe);
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd日"); 
		try {
			Date dt = sdf.parse(date);
			/*System.out.println(dt);*/
			l.setLdate(dt);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(l.getLdate().toString());
		l.setLpos(place);
		l.setLuid(uid);
		//用空字符串替换/img/dishes/
		lostimg=lostimg.replace("/img/faces/","");
		//返回指明请求context的请求URL的部分
		String saveUrl = request.getContextPath();
		//用空字符串替换saveUr1
		lostimg=lostimg.replace(saveUrl,"");
		l.setLimg(lostimg);
		l.setLtid(Integer.valueOf(type));
		try {
			lostmapper.insertSelective(l);
			request.setAttribute("msg", "发布成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			request.setAttribute("msg", "发布失败");
		}
		
		return "user/newlost";
	}
	
	@RequestMapping(value = "/newfound", method = RequestMethod.GET)
	public String getnewfound(HttpServletRequest request,HttpServletResponse response){
		if((request.getSession().getAttribute("Uname"))==""||request.getSession().getAttribute("Uname")==null)
		{
			return "login";
		}
		List<Type> types=typemapper.getAll();
		request.setAttribute("types", types);
		String no = (String) request.getSession().getAttribute("no");
		User user=userservice.getById(no);
		request.setAttribute("user", user);
		return "user/newfound";
	}
	@RequestMapping(value = "/newfound", method = RequestMethod.POST)
	public String newfound(HttpServletRequest request,HttpServletResponse response,String describe,String date,String place,String foundimg,String type){
		
		
		String uid=(String) request.getSession().getAttribute("no");
		/*System.out.println(uid);
		System.out.println(describe);
		System.out.println(date);
		System.out.println(place);
		System.out.println(foundimg);*/
		
		Found f = new Found();
		f.setFname(describe);
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd日"); 
		try {
			Date dt = sdf.parse(date);
			/*System.out.println(dt);*/
			f.setFdate(dt);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(f.getFdate().toString());
		f.setFpos(place);
		f.setFuid(uid);
		//用空字符串替换/img/dishes/
		foundimg=foundimg.replace("/img/faces/","");
		//返回指明请求context的请求URL的部分
		String saveUrl = request.getContextPath();
		//用空字符串替换saveUr1
		foundimg=foundimg.replace(saveUrl,"");
		f.setFimage(foundimg);
		f.setFtid(Integer.valueOf(type));
		try {
			foundmapper.insertSelective(f);
			request.setAttribute("msg", "发布成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			request.setAttribute("msg", "发布失败");
		}
		
		return "user/newfound";
	}
	
	@RequestMapping(value = "/usersearch", method = RequestMethod.GET)
	public String getusersearch(HttpServletRequest request,HttpServletResponse response,String mod,String scontent){
		request.getSession().setAttribute("mod", mod);
		request.getSession().setAttribute("scontent", scontent);
		return "user/usersearch";
	}
	@RequestMapping(value = "/usersearch", method = RequestMethod.POST)
	public void usersearch(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		String mod=(String) request.getSession().getAttribute("mod");
		String scontent=(String) request.getSession().getAttribute("scontent");
		/*System.out.println(mod);System.out.println(scontent);*/
	    if (mod.equals("lost")) {
				
			
			JSONObject object=new JSONObject();
			object.put("mod", "1");
	        object.put("myData", "");
	        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
	        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
	
	        List<Lost> losts=lostmapper.getAllSelective(scontent);
	        
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
	        List<Lost> pageresult=lostmapper.selectByPageSelective(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,scontent);
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
	    if (mod.equals("found")) {
	    	JSONObject object=new JSONObject();
	    	object.put("mod", "2");
	        object.put("myData", "");
	        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
	        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));

	        List<Found> founds=foundmapper.getAllSelective(scontent);
	        
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
	        List<Found> pageresult=foundmapper.selectByPageSelective(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,scontent);
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
	}
	
	@RequestMapping(value = "/userlostinfor", method = RequestMethod.GET)
	public String getuserlinfor(HttpServletRequest request,HttpServletResponse response){
		return "user/userlinfor";
	}
	@RequestMapping(value = "/userlostinfor", method = RequestMethod.POST)
	public void userlinfor(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        String uid=(String) request.getSession().getAttribute("no");
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));

        List<Lost> losts=lostmapper.getAllById(uid);
        
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
        List<Lost> pageresult=lostmapper.selectByPageById(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,uid);
        Lost[] arr = null;
        if(pageresult.size()>0){
            arr=new Lost[pageresult.size()];
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
	@RequestMapping(value = "/cglostinfor/{lid}", method = RequestMethod.GET)
	public String cglinfor(@PathVariable String lid,HttpServletRequest request,HttpServletResponse response){
		Lost lost=lostmapper.selectByPrimaryKey(Integer.valueOf(lid));
		lost.setLstatus("1");
		try {
			lostmapper.updateByPrimaryKey(lost);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/user/userlostinfor";
	}
	
	@RequestMapping(value = "/userfoundinfor", method = RequestMethod.GET)
	public String getuserfinfor(HttpServletRequest request,HttpServletResponse response){
		return "user/userfinfor";
	}
	@RequestMapping(value = "/userfoundinfor", method = RequestMethod.POST)
	public void userfinfor(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
        String uid=(String) request.getSession().getAttribute("no");      
		JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));

        List<Found> founds=foundmapper.getAllById(uid);
        
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
        List<Found> pageresult=foundmapper.selectByPageById(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,uid);
        Found[] arr = null;
        if(pageresult.size()>0){
            arr=new Found[pageresult.size()];
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
	@RequestMapping(value = "/cgfoundinfor/{fid}", method = RequestMethod.GET)
	public String cgfinfor(@PathVariable String fid,HttpServletRequest request,HttpServletResponse response){
		Found found=foundmapper.selectByPrimaryKey(Integer.valueOf(fid));
		found.setFstatus("1");
		try {
			foundmapper.updateByPrimaryKey(found);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/user/userfoundinfor";
	}
	
	@RequestMapping(value = "/userchangeif", method = RequestMethod.GET)
	public String getusercgif(HttpServletRequest request,HttpServletResponse response){
		return "user/usercgif";
	}
	@RequestMapping(value = "/userchangeif", method = RequestMethod.POST)
	public String usercgif(HttpServletRequest request,HttpServletResponse response,String tel,String email){
		String uid=(String) request.getSession().getAttribute("no");
		User user=userservice.getById(uid);
		user.setTel(tel);
		user.setEmail(email);
		try {
			userservice.updateByPrimaryKey(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/user/userchangeif";
	}
	
	@RequestMapping(value = "/userchangeps", method = RequestMethod.GET)
	public String getusercgps(HttpServletRequest request,HttpServletResponse response){
		return "user/usercgps";
	}
	@RequestMapping(value = "/userchangeps", method = RequestMethod.POST)
	public String usercgps(HttpServletRequest request,HttpServletResponse response,String password1){
		String uid=(String) request.getSession().getAttribute("no");
		System.out.println(password1);
		User user=userservice.getById(uid);
		user.setUpassword(password1);
		try {
			userservice.updateByPrimaryKey(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/user/userchangeps";
	}
	
}
