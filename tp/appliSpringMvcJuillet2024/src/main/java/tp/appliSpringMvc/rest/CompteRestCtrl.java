package tp.appliSpringMvc.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.appliSpringMvc.core.exception.MyNotFoundException;
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.CompteDto;

import java.util.List;

@RestController
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {

    @Autowired
    private ServiceCompteWithDto serviceCompte;

    //http://localhost:8080/appliSpringMvc/rest/api-bank/compte/1
    @GetMapping("/{id}")
    public CompteDto getCompteById(@PathVariable("id") long numeroCompte) {
        return serviceCompte.searchById( numeroCompte);
    }

    /*
    //http://localhost:8080/appliSpringMvc/rest/api-bank/compte/1
    @GetMapping("/{id}")
    public ResponseEntity<CompteDto> getCompteById(@PathVariable("id") long numeroCompte) {
        try {
            CompteDto c =serviceCompte.searchById( numeroCompte);
            //return new ResponseEntity<CompteDto>(c, HttpStatus.OK);
            return ResponseEntity.ok(c);
        } catch (MyNotFoundException e) {
           // return new ResponseEntity<CompteDto>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    */


    //http://localhost:8080/appliSpringMvc/rest/api-bank/compte
    //http://localhost:8080/appliSpringMvc/rest/api-bank/compte?soldeMini=200
    @GetMapping("")
    List<CompteDto> getComptesByCriteria(
            @RequestParam(value="soldeMini",required = false)Double soldeMini){
        if(soldeMini==null)
             return serviceCompte.searchAll();
        else
            return serviceCompte.searchAccountsWithMinimumBalance(soldeMini);
    }
    //en entrée: {"numero": null ,"label":"ccc","solde" :50.0}
    //en retour: {"numero": 786 ,"label":"ccc","solde" :50.0}
    @PostMapping("")
    CompteDto postCompte(@RequestBody CompteDto compte){
        compte = this.serviceCompte.saveNew(compte);
        return compte; //on retourne le compte ajouté avec clef primaire auto incrémentée
    }

    //en entrée: {"numero": 786 ,"label":"ccc","solde" :50.0}
    //en retour: {"numero": 786 ,"label":"ccc","solde" :50.0}
    @PutMapping("")
    CompteDto putCompte(@RequestBody CompteDto compte){
        compte = this.serviceCompte.updateExisting(compte);
        return compte;
    }

    //http://localhost:8080/appliSpringMvc/rest/api-bank/compte/1
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompteById(@PathVariable("id") long numeroCompte) {
        serviceCompte.deleteById(numeroCompte);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
