package tp.appliSpringMvc.core.service;

import tp.appliSpringMvc.dto.OperationDto;

public interface ServiceOperationWithDto extends GenericServiceWithDto<OperationDto,Long>{

	OperationDto saveNewWithAccountNumber(OperationDto opDto, Long numCompte);


}
