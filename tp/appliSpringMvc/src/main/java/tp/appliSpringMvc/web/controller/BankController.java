package tp.appliSpringMvc.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.ClientDto;
import tp.appliSpringMvc.dto.CompteDto;
import tp.appliSpringMvc.web.form.VirementForm;

@Controller
@RequestMapping("/site/bank")
@SessionAttributes({"client" , "numClient" , "tempPassword" })
public class BankController {
	
	@Autowired
	private ServiceCompteWithDto serviceCompte;
	
	@Autowired
	private ServiceClientWithDto serviceClient;

	
	@ModelAttribute("client")
	public ClientDto addDefaultClientAttributeInModel() {
		//NB: new ClientDto(Long numero, String prenom, String nom, String email, String adresse)
		return new ClientDto(null,"prenom?", "nom?" ,"ici_ou_la@xyz.com" , "adresse ?");
	}
	
	@ModelAttribute("compte")
	public CompteDto addDefaultCompteAttributeInModel() {
		//NB: new CompteDto( numero,  label, solde)
		return new CompteDto(null,"", 0.0);
	}
	
	@ModelAttribute("virement")
	public VirementForm addDefaultVirementAttributeInModel() {
		//NB: new VirementForm(montant, numCptDeb, numCptCred)
		return new VirementForm(null,null, null);
	}
   
	@RequestMapping("toAddCompte")
	public String toAddCompte(Model model) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null) 
			return "client_login";
       return "add_compte";
	}
	
	//NB: un @ModelAttribute("xxx") défini dans un controller 1 
	//semble être accessible depuis un controller 2 .

	
	@RequestMapping("doAddCompte")
	public String doAddCompte(Model model,
			@Valid @ModelAttribute("compte") CompteDto compte,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// form validation error
			System.out.println("form validation error: " + bindingResult.toString());
			return "add_compte";	
		} 
		/*else*/
	   try {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null) 
				return "clientLogin";
		compte = serviceCompte.saveNew(compte);
		serviceCompte.fixerProprietaireCompte(compte.getNumero(), numClient);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			return "add_compte";
		}
       return comptesDuClient(model); //réactualiser et afficher nouvelle liste des comptes
	}
	
	public String newUniqueActionToken() {
		return UUID.randomUUID().toString();
	}
	
	@RequestMapping("toVirement")
	public String toVirement(Model model,HttpSession httpSession) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null) 
			return "clientLogin";
		List<CompteDto> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
		model.addAttribute("listeComptes", listeComptes);  //pour listes déroulantes (choix numCptDeb et numCptCred)
		String uniqueActionToken = newUniqueActionToken();
		model.addAttribute("unique_action_token",uniqueActionToken );
		httpSession.setAttribute("unique_action_token", uniqueActionToken);
       return "virement";
	}

	
	@RequestMapping("doVirement")
	public String doVirement(Model model,
			HttpSession httpSession,
			@RequestParam(name="unique_action_token", required = false) String uniqueActionTokenParam,
			@Valid @ModelAttribute("virement") VirementForm virement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// form validation error
			System.out.println("form validation error: " + bindingResult.toString());
			return "virement";	
		} 
	   String uniqueActionTokenInSession = (String) httpSession.getAttribute("unique_action_token");
	   System.out.println("doVirement() , uniqueActionTokenInSession="+uniqueActionTokenInSession+ " uniqueActionTokenParam="+uniqueActionTokenParam);
	   if(uniqueActionTokenParam==null || uniqueActionTokenInSession==null
			   || !uniqueActionTokenParam.equals(uniqueActionTokenInSession)) {
		   //NE PAS FAIRE DEUX FOIS LE VIREMENT (si refresh ou autre par erreur)!!!
		   model.addAttribute("message", "chaque virement doit etre unique et distinct");
		   return toVirement(model,httpSession);
	   }
	   try {
		serviceCompte.transfert(virement.getMontant(), virement.getNumCptDeb(), virement.getNumCptCred());
		String uniqueActionToken = newUniqueActionToken();
		httpSession.setAttribute("unique_action_token", uniqueActionToken);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			Long numClient=(Long)model.getAttribute("numClient");
			List<CompteDto> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
			model.addAttribute("listeComptes", listeComptes);
			return "virement";
		}
       return comptesDuClient(model); //réactualiser et afficher nouvelle liste des comptes
	}
	
	
	
	@RequestMapping("comptesDuClient")
	public String comptesDuClient(Model model) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null) 
			return "clientLogin";
		/*else*/
		List<CompteDto> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
		//System.out.println("listeComptes="+listeComptes);
		model.addAttribute("listeComptes", listeComptes);
		return "comptes";
    }
	
	/*
	@RequestMapping("/logout")
	 public String clientLogout(Model model,
			        HttpSession httpSession,
			        SessionStatus sessionStatus) {
		httpSession.invalidate();
		sessionStatus.setComplete();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
		return "welcome";
	}*/
	
	//maintenant dans AppCtrl.logout avec spring_security
	
	@RequestMapping("/clientLoginWithSecurity")
	 public String clientLoginWithSecurity(Model model) {
		 //avec "navigation hook" géré automatiquement par spring-security (redirection automatique vers login.html , ...)
		
   		 //on récupère le username de l'utilisateur loggé avec spring security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	       String username =auth.getName();
	       System.out.println("clientLoginWithSecurity , username="+username);
	       //on considère que username vaut (par convention dans ce Tp) "client_" + numClient
	       //et on extrait donc le numero du client authentifié:
	       Long numClient= Long.parseLong(username.substring(7));
	       System.out.println("clientLoginWithSecurity , numClient="+numClient);
	       if(numClient!=null) {
				ClientDto client = serviceClient.searchById(numClient);
				model.addAttribute("client", client);
				
				model.addAttribute("tempPassword", "pwd");//cas d'école (tp)
				model.addAttribute("message", "successful login");
				model.addAttribute("numClient", numClient);
			}
	       return "client_login";
	    }
		return "welcome";
	}
	
	//Ancienne version (sans spring security)
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
