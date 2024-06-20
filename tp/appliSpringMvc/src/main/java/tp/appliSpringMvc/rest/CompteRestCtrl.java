package tp.appliSpringMvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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
	
	//exemple de fin d'URL: ./rest/api-bank/compte
	//                      ./rest/api-bank/compte?soldeMini=0
	//                      ./rest/api-bank/compte?customerId=1
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria(
			 @RequestParam(value="soldeMini",required=false) Double soldeMini,
			 @RequestParam(value="customerId",required=false) Long customerId) {
		if(soldeMini!=null)
			return serviceCompte.searchAccountsWithMinimumBalance(soldeMini);
		else if(customerId!=null)
		    return serviceCompte.searchCustomerAccounts(customerId);
		else
			return serviceCompte.searchAll();
	}
	
	//appelé en mode POST
	//avec url = .../rest/api-bank/compte
	//avec dans la partie "body" de la requête
	// { "numero" : null , "label" : "comptequiVaBien" , "solde" : 50.0 }
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_CUSTOMER') || hasRole('ROLE_ADMIN')")
	public CompteDto postCompte(@Valid @RequestBody CompteDto compteDto) {
			CompteDto compteSauvegarde = serviceCompte.saveNew(compteDto);  //avec numero auto_incrémenté
			return compteSauvegarde; //avec numero auto_incrémenté
	}
		
	//appelé en mode PUT
	//avec url = .../rest/api-bank/compte
	//ou bien avec url = .../rest/api-bank/compte/1
	//avec dans la partie "body" de la requête
	// { "numero" : 1 , "label" : "libelleModifie" , "solde" : 120.0  }
	@PutMapping({"","/{id}"})
	@PreAuthorize("hasRole('ROLE_CUSTOMER') || hasRole('ROLE_ADMIN')")
	public CompteDto putCompte( @RequestBody CompteDto compteDto, 
								@PathVariable(name="id",required = false) Long numeroCompte) {
		    //si l'id du compte à mettre à jour est passé en fin d'url, on répercute cela dans le dto
			if(numeroCompte!=null) {
				compteDto.setNumero(numeroCompte);
			}//sinon , on considère que l'appelant a déjà  placer l'id dans la partie json/body
			CompteDto compteMisAJour = serviceCompte.updateExisting(compteDto); 
			return compteMisAJour; //on pourrait simplement retourner ResponseEntity<>(avec status OK)
	}
		
	//.../rest/api-bank/compte/1 ou 2 
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_CUSTOMER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCompteById(@PathVariable("id") Long numeroCompte) {
		serviceCompte.deleteById( numeroCompte);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //NO_CONTENT = OK mais sans message
	}
    

}
