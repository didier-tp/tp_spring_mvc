package tp.appliSpringMvc.converter;


import tp.appliSpringMvc.core.entity.Client;
import tp.appliSpringMvc.core.entity.Compte;
import tp.appliSpringMvc.core.entity.Operation;
import tp.appliSpringMvc.dto.ClientDto;
import tp.appliSpringMvc.dto.ClientDtoEx;
import tp.appliSpringMvc.dto.CompteDto;
import tp.appliSpringMvc.dto.CompteDtoEx;
import tp.appliSpringMvc.dto.OperationDto;

//interface habituellement implémentée via mapStruct mais pas dans ce projet (par simplicité)
public interface MyMapper {
	public static MyMapper INSTANCE = new MyMapperImpl(); 
	
	OperationDto operationToOperationDto(Operation source);
	Operation operationDtoToOperation(OperationDto source);
	
	ClientDto clientToClientDto(Client source);
	ClientDtoEx clientToClientDtoEx(Client source);
	
	Client clientDtoToClient(ClientDto source);
	Client clientDtoExToClient(ClientDtoEx source);
	
	CompteDto compteToCompteDto(Compte compte);
	Compte compteDtoToCompte(CompteDto compteDto);
	
	CompteDtoEx compteToCompteDtoEx(Compte compte);
	Compte compteDtoExToCompte(CompteDtoEx compteDto);
}
