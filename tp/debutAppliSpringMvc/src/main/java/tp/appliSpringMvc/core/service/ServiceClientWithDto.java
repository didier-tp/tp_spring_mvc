package tp.appliSpringMvc.core.service;

import tp.appliSpringMvc.dto.ClientDto;
import tp.appliSpringMvc.dto.ClientDtoEx;

public interface ServiceClientWithDto extends GenericServiceWithDto<ClientDto,Long>{

	public ClientDtoEx searchCustomerWithAccountsById(Long numClient);

	public ClientDtoEx saveNewEx(ClientDtoEx clientA);

}
