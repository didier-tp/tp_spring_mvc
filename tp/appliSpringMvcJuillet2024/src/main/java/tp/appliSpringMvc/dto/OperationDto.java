package tp.appliSpringMvc.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OperationDto {
	private Long numOp;
	private String label;
	private Double montant;
	private String dateOp;
	
	public OperationDto(Long numOp,String label, Double montant, String dateOp) {
		super();
		this.numOp=numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = dateOp;
	}
	
	public OperationDto(Long numOp,String label, Double montant, Date date) {
		super();
		this.numOp=numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = (new SimpleDateFormat("yyy-MM-dd")).format(date);
	}
	
	public OperationDto(Long numOp,String label, Double montant) {
		this(numOp, label,montant,new Date());
	}
	
	public OperationDto() {
		this(null, null,null,new Date());
	}
	
	

	@Override
	public String toString() {
		return "OperationDto [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp
				+ "]";
	}

	public Long getNumOp() {
		return numOp;
	}

	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getDateOp() {
		return dateOp;
	}

	public void setDateOp(String dateOp) {
		this.dateOp = dateOp;
	}
	
	
	
	
	
}
