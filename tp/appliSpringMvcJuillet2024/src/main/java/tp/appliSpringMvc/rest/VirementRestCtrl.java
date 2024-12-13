package tp.appliSpringMvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliSpringMvc.core.exception.SoldeInsuffisantException;
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.VirementRequest;
import tp.appliSpringMvc.dto.VirementResponse;

@RestController
@RequestMapping(value="/rest/api-bank/virement" , headers="Accept=application/json")
public class VirementRestCtrl {

    @Autowired
    private ServiceCompteWithDto serviceCompte;

    @PostMapping
    public VirementResponse postVirement(@RequestBody VirementRequest virementRequest) {
        VirementResponse response = new VirementResponse();
        response.setMontant(virementRequest.getMontant());
        response.setNumCompteDebit(virementRequest.getNumCompteDebit());
        response.setNumCompteCredit(virementRequest.getNumCompteCredit());
        try {
            serviceCompte.transfert(virementRequest.getMontant(),
                    virementRequest.getNumCompteDebit(),
                    virementRequest.getNumCompteCredit());
            response.setStatus(true);
            response.setMessage("virement bien effectué");
        } catch (SoldeInsuffisantException e) {
            //throw new RuntimeException(e);
            response.setStatus(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
