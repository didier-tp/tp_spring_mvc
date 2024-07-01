package tp.appliSpringMvc.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import tp.appliSpringMvc.core.service.ServiceClientWithDto;
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.core.service.ServiceOperationWithDto;
import tp.appliSpringMvc.dto.ClientDto;
import tp.appliSpringMvc.dto.ClientDtoEx;
import tp.appliSpringMvc.dto.CompteDto;
import tp.appliSpringMvc.dto.OperationDto;

// classe qui initialise un jeu de donnees en phase de développement (sinon table vides)
@Component
@Profile("dev")
public class ReInitDataSet {
	
	@Autowired
	private ServiceCompteWithDto serviceCompte;
	
	@Autowired
	private ServiceClientWithDto serviceClient;
	
	@Autowired
	private ServiceOperationWithDto serviceOperation;
	
	@PostConstruct
	public void init() {
		CompteDto compteC1Sauvegarde = serviceCompte.saveNew(new CompteDto(null,"compteC1", 50.0));
		OperationDto opC1a = new OperationDto(null, "achat bonbons", -4.67);
		serviceOperation.saveNewWithAccountNumber(opC1a,compteC1Sauvegarde.getNumero());
		
		
		CompteDto compteC2Sauvegarde  = serviceCompte.saveNew(new CompteDto(null,"compteC2", 150.0));
		ClientDtoEx clientA = new ClientDtoEx(null, "alex", "Therieur", "12 rue Elle 75001 Paris", "email1");
		clientA.getComptes().add(compteC1Sauvegarde);
		clientA.getComptes().add(compteC2Sauvegarde);
		clientA = serviceClient.saveNewEx(clientA);
		
	
		CompteDto compteC3Sauvegarde = serviceCompte.saveNew(new CompteDto(null,"compteC3", 250.0));
		CompteDto compteC4Sauvegarde = serviceCompte.saveNew(new CompteDto(null,"compteC4", 350.0));
		ClientDtoEx clientB = new ClientDtoEx(null, "axelle", "Aire", "13 rue Elle 75001 Paris", "email2");
		clientB.getComptes().add(compteC3Sauvegarde);
		clientB.getComptes().add(compteC4Sauvegarde);
		clientB = serviceClient.saveNewEx(clientB);
		
		ClientDto clientABis = new ClientDto(null, "alain", "Therieur", "12 rue Elle 75001 Paris", "email1bis");
		clientABis = serviceClient.saveNew(clientABis);
	}

}
