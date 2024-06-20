package tp.appliSpringMvc.dto;

import java.util.ArrayList;
import java.util.List;

//version étendue de CompteDto avec plus de détails

public class CompteDtoEx extends CompteDto {
	private List<OperationDto> operations = new ArrayList<>();
	//...

	public CompteDtoEx(Long numero, String label, Double solde) {
		super(numero, label, solde);
	}
	
	public CompteDtoEx() {
		this(null,null,null);
	}
	
	public CompteDtoEx(Long numero, String label, Double solde,List<OperationDto> operations) {
		super(numero, label, solde);
		this.operations=operations;
	}

	public List<OperationDto> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationDto> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "CompteDtoEx [operations=" + operations + "] " + super.toString();
	}
	
	
}
