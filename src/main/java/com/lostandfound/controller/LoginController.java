package com.lostandfound.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
//import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostandfound.model.Admin;
import com.lostandfound.model.User;
import com.lostandfound.service.AdminService;
import com.lostandfound.service.UserService;
import com.lostandfound.tools.SaveCode;
import com.lostandfound.tools.Data;

import net.sf.json.JSONObject;
import java.util.Date;

@Controller

@Scope("prototype")
public class LoginController {
	@Resource
	AdminService adminservice;
	
	@Resource
	UserService userservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response,String userNo,String password){
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter pw = null;
		
		
		try {
			boolean log1 = adminservice.login(userNo, password);
			boolean log2 = userservice.login(userNo, password);
			
			if (log1) {
				Admin Admin = adminservice.getById(userNo);
				request.getSession().setAttribute("no", userNo);
				request.getSession().setAttribute("Aname", Admin.getAdminname());
				try {
					pw = response.getWriter();
					pw.print('1');
				} catch (IOException e) {

				} finally {
					pw.close();
				}

				return;
			}
			if (log2) {
				User user = userservice.getById(userNo);
				if(user.getStatus().equals("0")){
					request.getSession().setAttribute("no", userNo);
					request.getSession().setAttribute("Uname", user.getName());
					
					try {
						pw = response.getWriter();
						pw.print('2');
					} catch (IOException e) {
	
					} finally {
						pw.close();
					}
				}
				else{
					try {
						pw = response.getWriter();
						pw.print('3');
					} catch (IOException e) {
	
					} finally {
						pw.close();
					}
				}
				return;
			} else {

				
				try {
					pw = response.getWriter();
					pw.print('4');
				} catch (IOException e) {

				} finally {
					pw.close();
				}
				return;
			}
		}catch (Exception e) {
			System.out.println(e);

        }
	}
	@RequestMapping("/user")
	public String loginstd(HttpServletRequest request,Model model) {
		if((request.getSession().getAttribute("Uname"))==""||request.getSession().getAttribute("Uname")==null)
		{
			return "login";
		}

		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return "redirect:" + basePath;
	}
	@RequestMapping("/admin")
	public String loginadm(HttpServletRequest request,Model model) {
		if((request.getSession().getAttribute("Aname"))==""||request.getSession().getAttribute("Aname")==null)
		{
			return "login";
		}
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        model.addAttribute("time", time);
		return "admin/adminmain"; 
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request) {
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		request.getSession().invalidate();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return "redirect:" + basePath;
		
	}
	
	
	@RequestMapping(value ="/SaveCodeServlet", method = RequestMethod.GET)
	public void SaveCodeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SaveCode saveCode=new SaveCode("abcdefghijklmnopqrstuvwxyz123456789".toUpperCase().toCharArray(),
				100, 25, 6);
		
		response.setHeader("Pragma", "No-cache");
		
		response.setHeader("Cache-Control", "no-cache");
		
		response.setDateHeader("Expires", 0);
		
		response.setContentType("image/jpeg");
		
		saveCode.createSaveCodeImage();
		
		BufferedImage img = saveCode.getImage();
		
		String codeString = saveCode.getCodeString();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("checkCode", codeString);
		try {
			
			ImageIO.write(img, "JPEG", response.getOutputStream());
			
		} catch (Exception e) {

			// TODO: handle exception
		}
	}
	@RequestMapping(value ="/CheckCodeServlet", method = RequestMethod.GET)
	public void CheckCodeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strCode=request.getParameter("code");
		
		String sessionCode = request.getSession().getAttribute("checkCode").toString();
		
		JSONObject object = new JSONObject();
		if(strCode.equalsIgnoreCase(sessionCode)){
			object.put("strMessage", "1");
		}else{
			object.put("strMessage", "0");
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}
	@RequestMapping(value ="/FileUploadServlet", method = RequestMethod.POST)
	public void FileUploadServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		//json对象

		JSONObject json = new JSONObject();
		//返回休息设置为空字符串（如果最后返回的是空字符串，说明上传成功）
		json.put("message","");
		json.put("url","");
		//最终结果为true说明上传完成
		boolean glStrError = true;
		// 文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + "img/faces";
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/img/faces/";
		System.out.println(savePath);
		System.out.println(saveUrl);
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		// 最大文件大小
		long maxSize = 4000000;
		File uploadDir = new File(savePath);
		
		// 检查目录，没有就创建目录
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		//String dirName="image";
		if (!ServletFileUpload.isMultipartContent(request)) {//判断是否选择了上传的头像
			json.put("message", "请选择头像。");
			
		}  else if (!uploadDir.canWrite()) {// 检查目录写权限
			json.put("message", "上传目录没有写权限。");
		} 
		else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置编码避免中文乱码
			upload.setHeaderEncoding("UTF-8");
			try {
				List<?> items = upload.parseRequest(request);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();
					if (!item.isFormField()) {
						// 检查扩展名
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						// 检查文件大小
						if (item.getSize() > maxSize) {
							json.put("message", "上传文件大小超过限制。");
						} else if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
							json.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
						} else {
							//对上传文件重新命名（防止文件名重复）（同时也是对时间类型的处理）
							/**
							 * //对获取的时间进行格式化
							 *SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 *String newFileName11 = df11.format(new Date());
							 *System.out.println(newFileName11);
							 */
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
							String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
									+ fileExt;
							try {
								//上传文件
								File uploadedFile = new File(savePath, newFileName);
								item.write(uploadedFile);
							} catch (Exception e) {
								glStrError = false;
								json.put("message", "上传文件失败。");
							}
							if (glStrError) {

								/*把url插入用户对应的数据库表（略 自行处理）
								 * 当用户再次登录的时候，判断是否有头像
								 * 如果有头像取出响应给客户端
								 * 客户端把URL放入到img的src属性显示头像
								 * */
								/*System.out.println(saveUrl);
								System.out.println(savePath);*/
								String url= saveUrl + newFileName;
								json.put("url",url);
							}
						}
					}
				}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.getStackTrace();
			}
		}
		//由服务器向浏览器传递数据
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping(value ="/XLSFileUploadServlet", method = RequestMethod.POST)
	public void XLSFileUploadServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		//json对象

		JSONObject json = new JSONObject();
		//返回休息设置为空字符串（如果最后返回的是空字符串，说明上传成功）
		json.put("message","");
		
		//最终结果为true说明上传完成
		boolean glStrError = true;
		// 文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + "xls";
	
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("format", "xls,xlsx");

		// 最大文件大小
		long maxSize = 4000000;
		File uploadDir = new File(savePath);
		
		// 检查目录，没有就创建目录
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		//String dirName="image";
		if (!ServletFileUpload.isMultipartContent(request)) {//判断是否选择了上传的头像
			json.put("message", "请选择文件。");
			
		}  else if (!uploadDir.canWrite()) {// 检查目录写权限
			json.put("message", "上传目录没有写权限。");
		} 
		else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置编码避免中文乱码
			upload.setHeaderEncoding("UTF-8");
			try {
				List<?> items = upload.parseRequest(request);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();
					if (!item.isFormField()) {
						// 检查扩展名
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						// 检查文件大小
						if (item.getSize() > maxSize) {
							json.put("message", "上传文件大小超过限制。");
						} else if (!Arrays.<String> asList(extMap.get("format").split(",")).contains(fileExt)) {
							json.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("format") + "格式。");
						} else {
							//对上传文件重新命名（防止文件名重复）（同时也是对时间类型的处理）
							
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
							String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
									+ fileExt;
							try {
								//上传文件
								File uploadedFile = new File(savePath, newFileName);
								item.write(uploadedFile);
							} catch (Exception e) {
								glStrError = false;
								json.put("message", "上传文件失败。");
							}
							if (glStrError) {

								String url= savePath + "/" + newFileName;
								try {
									List<User> list = Data.getByexcel(url);
									User student = null;
									for(int i = 0; i < list.size(); i++) {
										student = list.get(i);
										User stu = userservice.getById(student.getUid());
										if(stu == null) {
											//student.setStupass(MD5.getInstance().md5(student.getStupass()));
											userservice.insert(student);
										} else {
											json.put("message", "The Record was Exist : No. = " + student.getUid()+ " , Name = " + student.getName() + " , has been throw away!");
											System.out.println("The Record was Exist : No. = " + student.getUid()+ " , Name = " + student.getName() + " , has been throw away!");
										}
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									json.put("message", "列表格式错误。");
								}
								
							}
						}
					}
				}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.getStackTrace();
			}
		}
		//由服务器向浏览器传递数据
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
