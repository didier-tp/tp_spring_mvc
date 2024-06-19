package tp.appliSpringMvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.CompteDto;

@RestController
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompteWithDto serviceCompte;
	
	@GetMapping("/{id}")
	public CompteDto getCompteById(@PathVariable("id") long numeroCompte) {
			return serviceCompte.searchById( numeroCompte);
	}
	
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria(
			 @RequestParam(value="soldeMini",required=false) Double soldeMini ) {
		if(soldeMini!=null)
			return serviceCompte.searchAccountsWithMinimumBalance(soldeMini);
		else
			return serviceCompte.searchAll();
	}
    

}
