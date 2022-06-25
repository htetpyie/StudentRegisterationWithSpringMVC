package studentmanagement.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.helpers.StudentHelper;
import studentmanagement.models.StudentBean;
import studentmanagement.persistant.dao.CourseDAO;
import studentmanagement.persistant.dao.StudentCourseDAO;
import studentmanagement.persistant.dao.StudentDAO;
import studentmanagement.persistant.dto.CourseResponseDTO;
import studentmanagement.persistant.dto.StudentDTO;
import studentmanagement.persistant.dto.StudentResponseDTO;

@Controller
public class Student {
	@Autowired
	private StudentCourseDAO studentCourseDAO;
	@GetMapping("/showStudentRegister")
	public ModelAndView showStudentRegister(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/");			
		}else {
			
			StudentBean studentBean = new StudentBean();
			//studentBean.setStudentCourse("Java");
			return new ModelAndView("STU001","studentBean",studentBean);
		}
		}
	
	@PostMapping("/studentRegister")
	public String studentRegister(@ModelAttribute("studentBean") @Validated StudentBean studentBean, BindingResult br, ModelMap map) {
		if(br.hasErrors()) {
			return "STU001";			
		}
		String id = StudentHelper.idGenerator();
		StudentDTO dto = new StudentDTO(id, studentBean.getStudentName(), studentBean.getStudentDob(), studentBean.getStudentGender(),
				studentBean.getStudentPhone(), studentBean.getStudentEducation(),studentBean.getStudentPhoto());
		new StudentDAO().insertStudent(dto);
		ArrayList<String> courses = studentBean.getStudentCourse();
		for(String course: courses ) {
			String courseId = new CourseDAO().selectCourseIdByCourseName(course);
			studentCourseDAO.insertStudentCourse(dto.getStudentId(), courseId);
		}
		return "redirect:/showStudentAll";
	}

	@GetMapping("/showStudentAll")
	public String showStudentAll(ModelMap model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";			
		}else {
			
			ArrayList<StudentResponseDTO> studentList = new StudentDAO().selectStudentAll();
			Map<String, String> map = new HashMap<>();
			for(StudentResponseDTO student: studentList) {
				String courses = new StudentCourseDAO().selectCoursesByStudentId(student.getStudentId());
				map.put(student.getStudentId(), courses);
			}
			model.addAttribute("map", map);
			model.addAttribute("studentList", studentList);
			return "STU003";
		}
	}
	
	@GetMapping("/searchStudent")
	public String searchStudent(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("course") String course, ModelMap model) {
		
		if(id.isBlank() && name.isBlank() && course.isBlank()) {
			return "redirect:/showStudentAll";
		}else {
			ArrayList<StudentResponseDTO> studentList = new ArrayList<>();
			studentList = new StudentCourseDAO().searchByIdNameCourse(id, name, course);
			Map<String, String> map = new HashMap<>();
			for(StudentResponseDTO student: studentList) {
				String courses = new StudentCourseDAO().selectCoursesByStudentId(student.getStudentId());
				map.put(student.getStudentId(), courses);
			}
			
			model.addAttribute("studentList", studentList);
			model.addAttribute("map",map);
			return "STU003";
			}
	}
	
	@GetMapping("/seeMore/{id}")
	public ModelAndView seeMore(@PathVariable("id") String id) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(id);
		StudentResponseDTO studentDTO = new StudentDAO().selectStudent(dto);
		StudentBean student = new StudentBean(studentDTO.getStudentId(), studentDTO.getStudentName(),studentDTO.getStudentDob(),
				studentDTO.getStudentGender(),studentDTO.getStudentPhone(),studentDTO.getStudentEducation(), 
				studentDTO.getStudentPhoto());
		
		//Selected course
		ArrayList<String> courseList = studentCourseDAO.selectCourseListByStudentId(student.getStudentId());
		student.setStudentCourse(courseList);
		return new ModelAndView("STU002","studentBean",student);
	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") String id) {
		studentCourseDAO.deleteCourseListByStudentId(id);
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(id);
		new StudentDAO().deleteStudent(dto);
		return "redirect:/showStudentAll";
	}
	
	@GetMapping("/showStudentUpdate/{id}")
	public ModelAndView showStudentUpdate(@PathVariable("id") String id) {		
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(id);
		StudentResponseDTO studentDTO = new StudentDAO().selectStudent(dto);
		StudentBean student = new StudentBean(studentDTO.getStudentId(), studentDTO.getStudentName(),studentDTO.getStudentDob(),
				studentDTO.getStudentGender(),studentDTO.getStudentPhone(),studentDTO.getStudentEducation(), 
				studentDTO.getStudentPhoto());
		ArrayList<String> courseList = studentCourseDAO.selectCourseListByStudentId(student.getStudentId());
		student.setStudentCourse(courseList);
		return new ModelAndView("STU002-01","studentBean",student);
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("studentBean") @Validated StudentBean bean, BindingResult br) {
			if(br.hasErrors()) {
				return "STU002-01";				
			}
												
				StudentDTO dto = new StudentDTO(bean.getStudentId(), bean.getStudentName(),bean.getStudentDob(), bean.getStudentGender(),
						bean.getStudentPhone(), bean.getStudentEducation(), bean.getStudentPhoto() );
				new StudentDAO().updateStudent(dto);
				
				//Delete existing course related with the student and add again
				new StudentCourseDAO().deleteCourseListByStudentId(bean.getStudentId());
				
				ArrayList<String> courses = bean.getStudentCourse();
				for(String course: courses ) {
					String courseId = new CourseDAO().selectCourseIdByCourseName(course);
					studentCourseDAO.insertStudentCourse(dto.getStudentId(), courseId);
				}
				
				return "redirect:/showStudentAll";
		
	}
	
	@ModelAttribute(value="courseList")
	public List<String> courseList(){
		ArrayList<String> list = new ArrayList<>();
		ArrayList<CourseResponseDTO> courseLists= new CourseDAO().selectCourseAll();
		for(CourseResponseDTO course: courseLists) {
			list.add(course.getCourseName());
		}
		return list;
		
	}
}
