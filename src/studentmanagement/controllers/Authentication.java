package studentmanagement.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import studentmanagement.helpers.CurrentDate;
import studentmanagement.persistant.dao.UserDAO;
import studentmanagement.persistant.dto.UserResponseDTO;

@Controller
public class Authentication {
	@Autowired
	private UserDAO userDAO ;
	
	@GetMapping("/")
	public String displayLogin() {
		return "LGN001";
	}
	
	@GetMapping("/showMenu")
	public String showMenu(){
		return "MNU001";
	}
	
	@PostMapping("/Login")
	public String login(ModelMap model, @RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		String current_date = CurrentDate.now();
		
		ArrayList<UserResponseDTO> list = userDAO.selectUserAll();
		if(list != null) {			
			for(UserResponseDTO user: list) {
				if( (user.getUserId().equals("id") || user.getUserEmail().equals(id))
						&& user.getUserPassword().equals(password) ) {
					session.setAttribute("user", user);
					session.setAttribute("isLogin","Okay");
					session.setAttribute("date", current_date);
					return "MNU001";
				}
			}
		}		
		model.addAttribute("error", "Please check your data again! ");
		return "LGN001";		
	}
	
	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", "");
		session.setAttribute("isLogin","");
		session.setAttribute("date", "");
		session.invalidate();
		return "LGN001";
	}
}
