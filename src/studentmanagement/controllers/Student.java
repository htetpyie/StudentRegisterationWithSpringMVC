package studentmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.models.StudentBean;
import studentmanagement.persistant.dao.CourseDAO;
import studentmanagement.persistant.dto.CourseResponseDTO;

@Controller
public class Student {
	@GetMapping("/showStudentRegister")
	public ModelAndView showStudentRegister() {
		StudentBean studentBean = new StudentBean();
		studentBean.setStudentCourse("Java");
		return new ModelAndView("STU001","studentBean",studentBean);
	}
	
	@PostMapping("/studentRegister")
	public String studentRegister(@ModelAttribute("studentBean") @Validated StudentBean studentBean, ModelMap map, BindingResult br) {
		if(br.hasErrors()) {
			return "STU001";
		}
		return "STU001";
		
	}
	
	@ModelAttribute(value="courseList")
	public List<String> courseList(){
		ArrayList<String> list = new ArrayList<>();
		ArrayList<CourseResponseDTO> courseLists= new CourseDAO().selectCourseAll();
		System.out.println(courseLists.size());
		for(CourseResponseDTO course: courseLists) {
			list.add(course.getCourseName());
		}
		return list;
		
	}
}
