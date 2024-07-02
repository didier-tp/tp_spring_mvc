package tp.appliSpringMvc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CompteDto {
	private Long numero;
	
	@NotEmpty(message = "label is required (not empty)")
	private String label;
	
	@Min(value=-999999)
	@Max(value=999999999)
	private Double solde;
	//...
	public CompteDto(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	public CompteDto() {
		this(null,null,null);
	}

	@Override
	public String toString() {
		return "CompteDto [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
	
}

//+autre future classe CompteDtoAvecDetails avec operations en plus
