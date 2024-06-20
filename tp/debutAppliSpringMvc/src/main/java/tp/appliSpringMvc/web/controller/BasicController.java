package tp.appliSpringMvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site/basic")
public class BasicController {
	
	@RequestMapping("helloWorld")
	public String helloWorld(Model model) {
		model.addAttribute("message", "hello world");
		return "displayBasicMessage" ;//.jsp ou .html(thymeleaf)
	}
	

}
