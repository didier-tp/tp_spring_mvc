package tp.appliSpringMvc.core.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "operation")
@NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o")
@NamedQuery(name = "Operation.findByAccountNumber", query = "SELECT o FROM Operation o WHERE o.compte.numero = ?1")
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOp; // id, auto_incr
	private String label; // ex: "achat xy"
	private Double montant; // ex: -50 ou +60

	@Temporal(TemporalType.DATE)
	private Date dateOp; // ex: 2022-11-07 en base

	@ManyToOne // plusieurs opérations pour un compte (@ManyToOne, @OneToMany)
	@JoinColumn(name = "numCompte") // avec clef_etrangère (fk) : numCompte
	private Compte compte;

	public Operation() {
		this.dateOp = new Date();
	}

	public Operation(Long numOp, String label, Double montant) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
	}

	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

	public Operation(Long numOp, String label, Double montant, Date dateOp) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = dateOp;
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

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
