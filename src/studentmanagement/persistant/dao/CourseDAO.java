package studentmanagement.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import studentmanagement.persistant.dto.CourseDTO;
import studentmanagement.persistant.dto.CourseResponseDTO;

@Service("CourseDAO")
public class CourseDAO {
	public static Connection con = null;
	static {
		try {
			con = MyConnection.getCon();		
		}catch(Exception e) {
			System.out.println("Connection in course Dao error.");
		}
	}
	
	
	public int insertCourse(CourseDTO dto) {
		int result = 0;
		String sql = "Insert into course_table (id, name) "
				+ "values(?, ?)";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCourseId());
			ps.setString(2, dto.getCourseName());			
			result = ps.executeUpdate();
			System.out.println("Course insert success.");
			
		} catch (SQLException e) {
			System.out.println("Course Insert Fail.");
		}
		return result;
	}
	
	public String selectCourseIdByCourseName(String name) {
		String sql = "SELECT id FROM course_table where name=? ";
		String course="";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				course = rs.getString(1);
			}
				
		} catch (SQLException e) {
			System.out.println("Selection course Id error!");
		}
		return course;
		
	}
	
	public CourseResponseDTO selectCourse(CourseDTO dto) {	
		CourseResponseDTO courseRes = new CourseResponseDTO();
		String sql = "select * from course_table where id=?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCourseId());			
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				courseRes.setCourseId(rs.getString("id"));
				courseRes.setCourseName(rs.getString("name"));
			}			
			
		} catch (SQLException e) {
			System.out.println("Selection with id Fail.");
		}
		return courseRes;
		
	}
	
	public ArrayList<CourseResponseDTO> selectCourseAll() {	
		ArrayList<CourseResponseDTO> list = new ArrayList<>();
		String sql = "select * from course_table ";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);		
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				CourseResponseDTO courseRes = new CourseResponseDTO();
				courseRes.setCourseId(rs.getString("id"));
				courseRes.setCourseName(rs.getString("name"));
				list.add(courseRes);
			}			
			
		} catch (SQLException e) {
			System.out.println("Course Selection all Fail.");
		}
		return list;
	}
	
	
}
	


