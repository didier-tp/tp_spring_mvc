package tp.appliSpringMvc.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tp.appliSpringMvc.core.service.ServiceClientWithDto;

import tp.appliSpringMvc.dto.ClientDto;



@Controller
@RequestMapping("/site/bank")
@SessionAttributes({"client" , "numClient" , "tempPassword" })
public class BankController {
	@Autowired
	private ServiceClientWithDto serviceClient;
	
	@ModelAttribute("client")
	public ClientDto addDefaultClientAttributeInModel() {
		//NB: new ClientDto(Long numero, String prenom, String nom, String email, String adresse)
		return new ClientDto(null,"prenom?", "nom?" ,"ici_ou_la@xyz.com" , "adresse ?");
	}

	@RequestMapping("/clientLogin")
	 public String clientLogin(Model model,
			 @RequestParam(name="numClient", required = false)  Long numClient,
			 @RequestParam(name="tempPassword", required = false)  String tempPassword) {
		System.out.println("/site/compte/clientLogin with numClient="+numClient + " and tempPassword=" + tempPassword);
		String message="";
		if(numClient==null )
			message="numClient is required";
		else {
			if(tempPassword==null || tempPassword.isEmpty())
				message="tempPassword is required";
			else {
				if(!tempPassword.equals("pwd")) {
					message="wrong tempPassword";
				}
				else {
					message="successful login";
				}
			}
		}
		if(numClient!=null) {
			ClientDto client = serviceClient.searchById(numClient);
			model.addAttribute("client", client);
		}
	    
		model.addAttribute("message", message);
		model.addAttribute("numClient", numClient);
		model.addAttribute("tempPassword", tempPassword);
	    return "client_login"; //aiguiller sur la vue "client_login"
	 }

}
