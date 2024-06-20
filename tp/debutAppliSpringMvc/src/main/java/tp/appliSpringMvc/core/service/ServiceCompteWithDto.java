package tp.appliSpringMvc.core.service;

import java.util.List;

import tp.appliSpringMvc.core.exception.MyNotFoundException;
import tp.appliSpringMvc.core.exception.SoldeInsuffisantException;
import tp.appliSpringMvc.dto.CompteDto;
import tp.appliSpringMvc.dto.CompteDtoEx;

public interface ServiceCompteWithDto extends GenericServiceWithDto<CompteDto,Long>{
	
	public CompteDtoEx searchCompteWithOperationsById(Long numCompte);

	List<CompteDto> searchCustomerAccounts(Long numClient);

	List<CompteDto> searchAccountsWithMinimumBalance(Double soldeMini);

	public void transfert(double montant, long numCptDeb, long numCptCred) throws SoldeInsuffisantException;
	
	boolean verifierPasDecouvert(long numCpt) throws MyNotFoundException;
	public void fixerProprietaireCompte(long numCompte, long numClient);
}
