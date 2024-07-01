package tp.appliSpringMvc.dto;


public class VirementResponse {
	private Long numCompteDebit;
	private Long numCompteCredit;
	private Double montant;
	private Boolean status;//true si ok , false si echec
	private String message;//"virement bien effectué" ou "echec"
	
	public Long getNumCompteDebit() {
		return numCompteDebit;
	}
	public void setNumCompteDebit(Long numCompteDebit) {
		this.numCompteDebit = numCompteDebit;
	}
	public Long getNumCompteCredit() {
		return numCompteCredit;
	}
	public void setNumCompteCredit(Long numCompteCredit) {
		this.numCompteCredit = numCompteCredit;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
