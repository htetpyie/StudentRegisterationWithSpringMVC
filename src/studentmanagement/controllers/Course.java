package studentmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.helpers.CourseHelper;
import studentmanagement.models.CourseBean;
import studentmanagement.persistant.dao.CourseDAO;
import studentmanagement.persistant.dto.CourseDTO;
import studentmanagement.persistant.dto.CourseResponseDTO;

@Controller
public class Course {
	@Autowired
	private CourseDAO courseDAO;	
	
	@GetMapping("/showCourseRegister")
	public ModelAndView showCourseRegister() {	
		CourseBean bean = new CourseBean();
		return new ModelAndView("BUD003","courseBean",bean);
		
	}
	
	@PostMapping("/courseRegister")
	public String courseRegister(@ModelAttribute("courseBean") @Validated CourseBean courseBean, BindingResult br, ModelMap model) {
		if(br.hasErrors()) {
			return "BUD003";
		}else if(CourseHelper.isCourseExist(courseBean.getCourseName())) {
			model.addAttribute("error",courseBean.getCourseName()+" already exists! ");
			return "BUD003";
		}
		else {	
			String courseId = CourseHelper.idGenerator();
			String courseName = courseBean.getCourseName();
			courseBean.setCourseId(courseId);
			CourseDTO dto = new CourseDTO();
			dto.setCourseId(courseId);
			dto.setCourseName(courseName);
			courseDAO.insertCourse(dto);
			model.addAttribute("success",courseName+" is successfully added!");
			return "BUD003";
		}
		
	}
	
}
