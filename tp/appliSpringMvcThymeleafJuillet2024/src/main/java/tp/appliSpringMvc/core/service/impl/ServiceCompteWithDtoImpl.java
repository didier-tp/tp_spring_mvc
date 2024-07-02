package tp.appliSpringMvc.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpringMvc.converter.MyGenericMapper;
import tp.appliSpringMvc.core.dao.DaoClient;
import tp.appliSpringMvc.core.dao.DaoCompte;
import tp.appliSpringMvc.core.dao.DaoOperation;
import tp.appliSpringMvc.core.entity.Client;
import tp.appliSpringMvc.core.entity.Compte;
import tp.appliSpringMvc.core.entity.Operation;
import tp.appliSpringMvc.core.exception.MyNotFoundException;
import tp.appliSpringMvc.core.exception.SoldeInsuffisantException;
import tp.appliSpringMvc.core.service.ServiceCompteWithDto;
import tp.appliSpringMvc.dto.CompteDto;
import tp.appliSpringMvc.dto.CompteDtoEx;

@Service
@Transactional
public class ServiceCompteWithDtoImpl 
  extends AbstractGenericServiceWithDto<CompteDto,Long,Compte,DaoCompte> 
  implements ServiceCompteWithDto{
	
	private DaoCompte daoCompte; //for specific methods of this class
	private DaoOperation daoOperation;
	private DaoClient daoClient;
	
	
	static IdHelper<CompteDto,Compte,Long> compteIdHelper = new IdHelper<>(){
		@Override public Long extractEntityId(Compte e) {return e.getNumero();}
		@Override public Long extractDtoId(CompteDto dto) {return dto.getNumero();}
		@Override public void setDtoId(CompteDto dto, Long id) { dto.setNumero(id); }
	};
	
	@Autowired
	public ServiceCompteWithDtoImpl(DaoCompte daoCompte,DaoOperation daoOperation,DaoClient daoClient ) {
		super(CompteDto.class,Compte.class, daoCompte,compteIdHelper);
		this.daoCompte=daoCompte;
		this.daoOperation=daoOperation;
		this.daoClient=daoClient;
	}

	@Override
	public List<CompteDto> searchCustomerAccounts(Long numClient) {
		return MyGenericMapper.map(daoCompte.findByClientsNumero(numClient),CompteDto.class);
	}

	@Override
	public List<CompteDto> searchAccountsWithMinimumBalance(Double soldeMini) {
		return MyGenericMapper.map(daoCompte.findBySoldeGreaterThanEqual(soldeMini),CompteDto.class);
	};
	
	@Override
	//@Transactional(/* propagation = Propagation.REQUIRED par défaut */)
	//maintenant @Transactional est placé dans le haut de la classe
	public void transfert(double montant, long numCptDeb, long numCptCred) {
		try {
			Compte cptDeb = daoCompte.findById(numCptDeb).orElse(null);
			if(cptDeb.getSolde() < montant)
				throw new SoldeInsuffisantException("solde insuffisant sur compte " + numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			//créer, rattacher et enregistrer un objet Operation sur de débit
			Operation opDebit = new Operation(null, "debit suite au virement", -montant);
			opDebit.setCompte(cptDeb);	daoOperation.save(opDebit);
			daoCompte.save(cptDeb); //v1 ou v2 sans .save() si @Transactional
			
			Compte cptCred = daoCompte.findById(numCptCred).orElse(null);
			cptCred.setSolde(cptCred.getSolde() + montant);
			//créer, rattacher et enregistrer un objet Operation sur le crédit
			Operation opCredit = new Operation(null, "crédit suite au virement", montant);
			opCredit.setCompte(cptCred);	daoOperation.save(opCredit);
			daoCompte.save(cptCred); // v1  ou v2 sans .save() si @Transactional
		} catch (SoldeInsuffisantException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException("echec virement" , e);
		}
	}
	
	@Override
	public CompteDtoEx searchCompteWithOperationsById(Long numCompte) {
		Compte compteEntityWithOperations = daoCompte.findWithOperationsById(numCompte);
		return MyGenericMapper.map(compteEntityWithOperations,CompteDtoEx.class);
	}
	
	@Override
	public boolean verifierPasDecouvert(long numCpt) throws MyNotFoundException {
		Compte compte = daoCompte.findById(numCpt).orElse(null);
		if(compte==null)
			throw new MyNotFoundException("compte inexistant avec numero="+numCpt);
		return (compte.getSolde() >= 0 );
	}

	@Override
	public void fixerProprietaireCompte(long numCompte, long numClient) {
		Client clientEntity = daoClient.findById(numClient).get();
		Compte compteEntity = daoCompte.findById(numCompte).get();
		clientEntity.getComptes().add(compteEntity);
		daoClient.save(clientEntity);
	}

}
