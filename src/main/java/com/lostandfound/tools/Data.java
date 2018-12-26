package com.lostandfound.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lostandfound.model.User;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Data {
	
	public static List<User> getByexcel(String file) throws Exception{
		
		List<User> list = new ArrayList<User>();
		
		try {
			InputStream instream = new FileInputStream(file);
			
			Workbook readwb = Workbook.getWorkbook(instream); 
			
			//Sheet的下标是从0开始   
            //获取第一张Sheet表   
            Sheet readsheet = readwb.getSheet(0); 
            //获取Sheet表中所包含的总列数   
            int rsColumns = readsheet.getColumns();   
            //获取Sheet表中所包含的总行数   
            int rsRows = readsheet.getRows();   
            //获取指定单元格的对象引用   
            for (int i = 0; i < rsRows; i++)   
            {      
            	int j = 0;
            	User student = new User();
                Cell cell = readsheet.getCell(j++, i);
                Cell cell2 = readsheet.getCell(j++, i);
                Cell cell3 = readsheet.getCell(j++, i);
                Cell cell4 = readsheet.getCell(j++, i);
                Cell cell5 = readsheet.getCell(j++, i);
                student.setUid(cell.getContents());
                student.setUpassword(cell2.getContents());
                student.setName(cell3.getContents());  
                student.setEmail(cell4.getContents());
                student.setTel(cell5.getContents());
                list.add(student);
            }   
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
//	public static void insert(Student student) {
//		String sql = "insert into student values(?, ?, ?)";
//		Base b = new Base();
//		Connection conn = b.getConn();
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, student.getId());
//			ps.setString(2, student.getName());
//			ps.setString(3, student.getSex());
//			int flag = ps.executeUpdate();
//			if(flag > 0) {
//				System.out.println("Save data : id = " + student.getId() + " , Name = " 
//						+ student.getName() + ", sex = " 
//						+ student.getSex() + " succeed!");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static boolean isExist(Student student) {
//		
//		String sql = "select * from student where id=" + student.getId();
//		Base b = new Base();
//		List<Student> list = new ArrayList<Student>();
//		Connection conn = b.getConn();
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				Student stu = new Student();
//				stu.setId(rs.getString(1));
//				stu.setStuname(rs.getString(2));
//				stu.setSex(rs.getString(3));
//				list.add(stu);
//			}
//			if(list.isEmpty()) {
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

}
