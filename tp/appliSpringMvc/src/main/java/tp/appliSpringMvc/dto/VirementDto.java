package tp.appliSpringMvc.dto;

public class VirementDto {
	private Double montant;
	private String numCptDeb;
	private String numCptCred;
	
	private Boolean ok;
	private String message;
	//...
	
	public VirementDto(Double montant, String numCptDeb, String numCptCred) {
		super();
		this.montant = montant;
		this.numCptDeb = numCptDeb;
		this.numCptCred = numCptCred;
	}
	
	public VirementDto() {
		this(null,null,null);
	}


	@Override
	public String toString() {
		return "VirementDto [montant=" + montant + ", numCptDeb=" + numCptDeb + ", numCptCred=" + numCptCred + ", ok="
				+ ok + ", message=" + message + "]";
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getNumCptDeb() {
		return numCptDeb;
	}

	public void setNumCptDeb(String numCptDeb) {
		this.numCptDeb = numCptDeb;
	}

	public String getNumCptCred() {
		return numCptCred;
	}

	public void setNumCptCred(String numCptCred) {
		this.numCptCred = numCptCred;
	}

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
