package cap.userservice.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cap.userservice.beans.User;
import cap.userservice.dao.UserRepo;

@Controller
@SessionAttributes("user")
public class LoginController 
{
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/login")
	public String loginPage(Model model)
	{
		User u1 = new User();
		model.addAttribute("user", u1);
		return "login";
	}
	
	@PostMapping("processLogin")
	@ResponseBody
	public User processLogin(@RequestParam String username, @RequestParam String password,
			@ModelAttribute("user")User u1, Model model, HttpServletResponse response) throws IOException
	{
		u1 = userRepo.findByUsername(username);
		if(u1 == null)
		{
			model.addAttribute("user", u1);
			response.sendRedirect("/login");
			return null;
		}
		if(u1.getPassword().equals(password))
		{
			return u1;
		}
		model.addAttribute("user", u1);
		response.sendRedirect("/login");
		return null;
		
	}
}


