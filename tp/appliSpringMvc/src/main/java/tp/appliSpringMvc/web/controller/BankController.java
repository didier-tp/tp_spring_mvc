package tp.appliSpringMvc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
	
	@RequestMapping("toVirement")
	public String toVirement(Model model) {
		Long numClient=(Long)model.getAttribute("numClient");
		if(numClient==null) 
			return "clientLogin";
		List<CompteDto> listeComptes = serviceCompte.searchCustomerAccounts(numClient);
		model.addAttribute("listeComptes", listeComptes);  //pour listes déroulantes (choix numCptDeb et numCptCred)
       return "virement";
	}

	
	@RequestMapping("doVirement")
	public String doVirement(Model model,
			@Valid @ModelAttribute("virement") VirementForm virement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// form validation error
			System.out.println("form validation error: " + bindingResult.toString());
			return "virement";	
		} 
	   try {
		serviceCompte.transfert(virement.getMontant(), virement.getNumCptDeb(), virement.getNumCptCred());
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
	
	@RequestMapping("/logout")
	 public String clientLogout(Model model,
			        HttpSession httpSession,
			        SessionStatus sessionStatus) {
		httpSession.invalidate();
		sessionStatus.setComplete();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
		return "welcome";
	}
	
	@RequestMapping("/clientLoginWithSecurity")
	 public String clientLoginWithSecurity() {
		 //avec "navigation hook" géré automatiquement par spring-security
		 return "welcome";
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
