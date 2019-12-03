package cap.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cap.userservice.service.ValidateService;
import cap.userservice.beans.User;
import cap.userservice.dao.UserRepo;

@Controller
public class SignupController 
{
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/")
	public String homePage()
	{
		
		return "form";
	}

	
	@PostMapping("/openAccount")
	public String openAccount(@ModelAttribute User u1, @RequestParam String accountType,
			@RequestParam String balance, @RequestParam String confirmPassword)
	{
		if("SAVINGS".equalsIgnoreCase(accountType))
		{
			u1.setBalanceS(Double.parseDouble(balance));
		}
		else if("CHECKING".equalsIgnoreCase(accountType))
		{
			u1.setBalanceC(Double.parseDouble(balance));
		}
		else if("CD".equalsIgnoreCase(accountType))
		{
			u1.setBalanceCD(Double.parseDouble(balance));
		}
		if(!ValidateService.validateService(u1, accountType, confirmPassword))
		{
			return "redirect:/";
		}
		userRepo.save(u1);
		return "redirect:/opened";
	}
	
}


