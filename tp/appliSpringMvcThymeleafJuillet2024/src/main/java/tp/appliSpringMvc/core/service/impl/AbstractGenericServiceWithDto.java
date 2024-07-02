package tp.appliSpringMvc.core.service.impl;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpringMvc.converter.MyGenericMapper;
import tp.appliSpringMvc.core.exception.ConflictException;
import tp.appliSpringMvc.core.exception.MyNotFoundException;
import tp.appliSpringMvc.core.service.GenericServiceWithDto;

public abstract class AbstractGenericServiceWithDto<DTO,ID,E,DAO extends CrudRepository<E, ID>> implements GenericServiceWithDto<DTO,ID> {

	private Class<E> entityClass;
	private Class<DTO> dtoClass;
	private IdHelper<DTO,E,ID> idHelper;
	private DAO dao;
	
	public AbstractGenericServiceWithDto(Class<DTO> dtoClass,Class<E> entityClass, DAO dao, IdHelper<DTO,E,ID> idHelper) {
		this.entityClass=entityClass;
		this.dtoClass=dtoClass;
		this.idHelper=idHelper;
		this.dao=dao;
	}

	@Override
	public DTO searchById(ID id) throws MyNotFoundException {
		E entity = dao.findById(id).orElse(null);
		if(entity!=null) 
			return MyGenericMapper.map(entity, dtoClass);
		else 
			throw new MyNotFoundException("no existing " + dtoClass.getSimpleName() + " for id="+id);
	}

	@Override
	public List<DTO> searchAll() {
		return MyGenericMapper.map((List<E>)dao.findAll(), dtoClass);
	}

	@Override
	public DTO saveOrUpdate(DTO dto) {
		E entity = MyGenericMapper.map(dto, entityClass);
		dao.save(entity);
		//return MyGenericMapper.map(entity, dtoClass);
		idHelper.copyEntityIDIntoDto(entity, dto);
		return dto;
	}

	@Override
	public void deleteById(ID id) throws MyNotFoundException{
		if(!existsById(id)) 
			throw new MyNotFoundException("no existing " + dtoClass.getSimpleName() + " to delete for id="+id);
		dao.deleteById(id);
	}

	@Override
	public DTO saveNew(DTO dto) throws ConflictException{
		ID id = this.idHelper.extractDtoId(dto);
		if(id != null) {
			if(dao.existsById(id))
				throw new ConflictException("already existing " + dtoClass.getSimpleName() + " with id="+id);
		}
		return saveOrUpdate(dto);
	}

	@Override
	public DTO updateExisting(DTO dto) {
		ID id = this.idHelper.extractDtoId(dto);
		if(!existsById(id)) 
			throw new MyNotFoundException("no existing " + dtoClass.getSimpleName() + " to update for id="+id);
		return saveOrUpdate(dto);
	}

	@Override
	public boolean existsById(ID id) {
		return dao.existsById(id);
	}

}
