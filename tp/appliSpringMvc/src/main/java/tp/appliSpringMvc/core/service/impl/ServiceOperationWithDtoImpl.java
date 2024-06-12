package tp.appliSpringMvc.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpringMvc.converter.MyGenericMapper;
import tp.appliSpringMvc.core.dao.DaoCompte;
import tp.appliSpringMvc.core.dao.DaoOperation;
import tp.appliSpringMvc.core.entity.Operation;
import tp.appliSpringMvc.core.service.ServiceOperationWithDto;
import tp.appliSpringMvc.dto.OperationDto;

@Service
@Transactional
public class ServiceOperationWithDtoImpl 
  extends AbstractGenericServiceWithDto<OperationDto,Long,Operation,DaoOperation> 
  implements ServiceOperationWithDto{
	
	private DaoOperation daoOperation; //for specific methods of this class
	private DaoCompte daoCompte;//secondary Dao
	
	
	static IdHelper<OperationDto,Operation,Long> operationIdHelper = new IdHelper<>(){
		@Override public Long extractEntityId(Operation e) {return e.getNumOp();}
		@Override public Long extractDtoId(OperationDto dto) {return dto.getNumOp();}
		@Override public void setDtoId(OperationDto dto, Long id) { dto.setNumOp(id); }
	};
	
	@Autowired
	public ServiceOperationWithDtoImpl(DaoOperation daoOperation,DaoCompte daoCompte ) {
		super(OperationDto.class,Operation.class, daoOperation,operationIdHelper);
		this.daoOperation=daoOperation;
		this.daoCompte=daoCompte;
	}

	@Override
	public OperationDto saveNewWithAccountNumber(OperationDto opDto, Long numCompte) {
		Operation operationEntity = MyGenericMapper.map(opDto,Operation.class);
		if(operationEntity.getDateOp()==null)
			operationEntity.setDateOp(new Date());
		operationEntity.setCompte(daoCompte.findById(numCompte).get());
		daoOperation.save(operationEntity);
		opDto.setNumOp(operationEntity.getNumOp());
		opDto.setDateOp((new SimpleDateFormat("yyy-MM-dd")).format(operationEntity.getDateOp()));
		return opDto;
	}

}
