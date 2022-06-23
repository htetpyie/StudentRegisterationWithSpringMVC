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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.models.UserBean;
import studentmanagement.persistant.dto.UserDTO;

@Controller
public class User {
	
	
	//@RequestParam("name")

	//@RequestMapping(value="/", method=RequestMethod.GET)
	@GetMapping("/")
	public String displayLogin(ModelMap model) {
		return "LGN001";
	}
	
	@RequestMapping(value="/displayUserRegister", method=RequestMethod.GET)
	public ModelAndView displayUserRegister(ModelMap map) {
		UserBean userBean = new UserBean();
		return new ModelAndView("USR001","userBean",userBean);
	}
	
	@PostMapping("/userRegister")
	public String userRegister(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult br, ModelMap model) {
		if(br.hasErrors()) {
			return "USR001";
		}
		
		UserDTO dto = new UserDTO()

		return "USR001";
	}
	
	
	
}
