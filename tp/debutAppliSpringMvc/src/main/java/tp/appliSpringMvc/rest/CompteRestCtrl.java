package tp.appliSpringMvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.CompteDto;

@RestController
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompteWithDto serviceCompte;
	
	
	//V1 (avant ResponseEntity<> direct )
	//V3 (après ExceptionHandler )
	@GetMapping("/{id}")
	public CompteDto getCompteById(@PathVariable("id") long numeroCompte) {
			return serviceCompte.searchById( numeroCompte);
	}
	
}
