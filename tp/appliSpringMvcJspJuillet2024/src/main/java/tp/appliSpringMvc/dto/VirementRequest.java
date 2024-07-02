package tp.appliSpringMvc.dto;


public class VirementRequest {
	private Long numCompteDebit;
	private Long numCompteCredit;
	private Double montant;
	
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

	
}
