package studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.models.StudentBean;

@Controller
public class Student {
	@GetMapping("showStudentRegister")
	public ModelAndView showStudentRegister() {
		StudentBean studentBean = new StudentBean();
		
		return new ModelAndView("STU001","studentBean",studentBean);
	}
}
