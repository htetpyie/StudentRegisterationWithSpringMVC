package studentmanagement.controllers;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.helpers.UserHelper;
import studentmanagement.models.UserBean;
import studentmanagement.persistant.dao.UserDAO;
import studentmanagement.persistant.dto.UserDTO;
import studentmanagement.persistant.dto.UserResponseDTO;

@Controller
public class User {	

	@Autowired
	private UserDAO dao;
		
	@RequestMapping(value="/displayUserRegister", method=RequestMethod.GET)
	public ModelAndView displayUserRegister(ModelMap map) {
		UserBean userBean = new UserBean();
		return new ModelAndView("USR001","userBean",userBean);
	}
	
	@PostMapping("/userRegister")
	public String userRegister(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult br, ModelMap model ) {
		if(br.hasErrors()) {
			return "USR001";
		}
		else if(!userBean.getUserPassword().equals(userBean.getUserCfPassword())) {
			model.addAttribute("passwordError","Password doesn't match!");
			return "USR001";
		}
		else if(UserHelper.isEmailExist(userBean.getUserEmail())) {
			model.addAttribute("error","Email already exists.");
			return "USR001";
		}else {
			userBean.setUserId(UserHelper.idGenerator());
			UserDTO dto = new UserDTO(userBean.getUserId(), userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword(), userBean.getUserRole());
			dao.insertUser(dto);
			model.addAttribute("success","Successfully registered.");
			return "USR001";
		}
		
	}
	
	@GetMapping("/showUser")
	public String showUser(ModelMap model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";			
		}else {
			ArrayList<UserResponseDTO> list = dao.selectUserAll();
			model.addAttribute("userList",list);
			return "USR003";
		}
			
		}
	
	@GetMapping("/showAddUser")
	public ModelAndView showAddUserPage() {
		UserBean userBean = new UserBean();
		return new ModelAndView("USR001-01","userBean", userBean);
	}
	
	@PostMapping("/userAdd")
	public String userAdd(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult br, ModelMap model ) {
		if(br.hasErrors()) {
			return "USR001-01";
		}
		else if(!userBean.getUserPassword().equals(userBean.getUserCfPassword())) {
			model.addAttribute("passwordError","Password doesn't match!");
			return "USR001-01";
		}
		else if(UserHelper.isEmailExist(userBean.getUserEmail())) {
			model.addAttribute("error","Email already exists.");
			return "USR001-01";
		}else {
			userBean.setUserId(UserHelper.idGenerator());
			UserDTO dto = new UserDTO(userBean.getUserId(), userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword(), userBean.getUserRole());
			dao.insertUser(dto);
			return "redirect:/showUser";
		}
		
	}
	
	@GetMapping("/userSearch")
	public String userSearch(ModelMap model, @RequestParam("id") String id, @RequestParam("name") String name) {
		if(name.isBlank() && id.isBlank()) {
			return "redirect:/showUser";
		}
		else {
			UserDTO dto = new UserDTO();
			dto.setUserId(id);
			UserResponseDTO searchById = dao.selectUser(dto);
			ArrayList<UserResponseDTO> list = dao.selectUsersByName(name);		
			list.add(searchById);
			model.addAttribute("userList", list);
			return "USR003";
				
		}
	}
	
	@GetMapping("/showUserUpdate/{id}")
	public String showUserUpdate(ModelMap model, @PathVariable("id") String id) {
		UserDTO dto = new UserDTO();
		dto.setUserId(id);
		UserResponseDTO userRes = new UserDAO().selectUser(dto);
		model.addAttribute("user", userRes);
		return "USR002";
	}

	@PostMapping("/userUpdate")
	public String userUpdate(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password")String password,
			@RequestParam("cfpassword")String cf_password, @RequestParam("role") String role, @RequestParam("id") String id,
			HttpSession session, ModelMap model) {		
			
			UserBean userBean = new UserBean(id, email, name, password, role);
			if(name.isBlank() || email.isBlank() || password.isBlank() ) {
				session.setAttribute("user", userBean);
				model.addAttribute("error", "Field cannot be blank! " );
				return "USR002";
				}
			else if(!password.equals(cf_password)) {
				session.setAttribute("user", userBean);
				model.addAttribute("error", "Password doesn't match." );
				return "USR002";
				}				
			else {
				UserDTO userDTO = new UserDTO(userBean.getUserId(), userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword(), userBean.getUserRole());
				dao.updateUser(userDTO)	;		
				return "redirect:/showUser";
			}
	}
	
	@RequestMapping("/userDelete/{id}")
	public String userDelete(@PathVariable("id") String id) {
		UserDTO dto = new UserDTO();
		dto.setUserId(id);
		dao.deleteUser(dto);		
		return "redirect:/showUser";
	}


}
