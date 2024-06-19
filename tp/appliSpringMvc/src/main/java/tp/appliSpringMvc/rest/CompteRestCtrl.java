package tp.appliSpringMvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpringMvc.core.exception.MyNotFoundException;
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.ApiError;
import tp.appliSpringMvc.dto.CompteDto;

@RestController
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompteWithDto serviceCompte;
	
	
	//V1 (avant ResponseEntity<> direct )
	//V3 (après ExceptionHandler )
	@GetMapping("/{id}")
	public CompteDto getCompteById(@PathVariable("id") long numeroCompte) {
			return serviceCompte.searchById( numeroCompte);
	}
	
	
	/*
	//V2 (avant ResponseEntity<> direct/explicite )
	@GetMapping("/{id}")
	public ResponseEntity<?> getCompteById(@PathVariable("id") long numeroCompte) {
			try {
				CompteDto compte = serviceCompte.searchById( numeroCompte);
				return ResponseEntity.ok().body(compte);
			} catch (MyNotFoundException e) {
				//e.printStackTrace();
				ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,e);
				return ResponseEntity.
						status(apiError.getStatus())
						.body(apiError);
			}
	}
	*/
	
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria(
			 @RequestParam(value="soldeMini",required=false) Double soldeMini ) {
		if(soldeMini!=null)
			return serviceCompte.searchAccountsWithMinimumBalance(soldeMini);
		else
			return serviceCompte.searchAll();
	}
    

}
