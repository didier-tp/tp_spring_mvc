package tp.appliSpringMvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpringMvc.core.dao.DaoCompte;
import tp.appliSpringMvc.core.entity.Compte;

/*
 * MAUVAISE VERSION DU CODE
 * A un peu EXPERIMENTER en Tp
 * pour visualiser le PROBLEME: boucle infinie si pas de @JsonIgnore
 * sur entity.Compte.operations
 */


//@RestController
@RequestMapping(value="/rest/api-bank/bad-compte" , headers="Accept=application/json")
public class BadRestCtrl {
	
	@Autowired
	private DaoCompte daoCompte;
	
	@GetMapping("/{id}")
	public Compte getCompteEntityById(@PathVariable("id") long numeroCompte) {
			return daoCompte.findById( numeroCompte).get();
	}

}
