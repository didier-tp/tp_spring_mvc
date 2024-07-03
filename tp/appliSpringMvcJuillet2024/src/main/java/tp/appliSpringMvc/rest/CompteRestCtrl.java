package tp.appliSpringMvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

}
