package tp.appliSpringMvc.core.service;

import java.util.List;

import tp.appliSpringMvc.core.exception.ConflictException;
import tp.appliSpringMvc.core.exception.MyNotFoundException;

//DTO=DTO class "essential/basic"
//DTO= DTO class 
public interface GenericServiceWithDto<DTO,ID> {
	DTO searchById(ID id)throws MyNotFoundException;
	List<DTO> searchAll(); //may return an empty list without exception
	DTO saveOrUpdate(DTO dto);
	DTO saveNew(DTO dto) throws ConflictException;
	DTO updateExisting(DTO dto)throws MyNotFoundException;
	boolean existsById(ID id);
	void deleteById(ID id)throws MyNotFoundException;
}
