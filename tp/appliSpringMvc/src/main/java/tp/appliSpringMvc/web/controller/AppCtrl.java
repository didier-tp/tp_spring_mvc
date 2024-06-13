package tp.appliSpringMvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 

@RequestMapping("/site/app")
public class AppCtrl {
	
	
	@RequestMapping("/toWelcome")
	String toWelcome(Model model) {
		model.addAttribute("message", "bienvenu(e)");
		model.addAttribute("title","welcome");
	    return "welcome"; 
	}
	
	  // Login form
	  @RequestMapping("/login")
	  public String login() {
	    return "login";
	  }

	  // Login form with error
	  @RequestMapping("/login-error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }
	
}
