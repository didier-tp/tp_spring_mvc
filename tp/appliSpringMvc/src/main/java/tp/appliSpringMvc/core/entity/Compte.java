package tp.appliSpringMvc.core.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compte")
@NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c")
@NamedQuery(name = "Compte.findByCustomerNumber", query = "SELECT c FROM Compte c JOIN c.clients cli WHERE cli.numero = ?1")
@NamedQuery(name = "Compte.findWithOperationsById", query = "SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = ?1")

public class Compte {

	@ManyToMany(mappedBy = "comptes") // coté secondaire avec mappedBy="nomJavaRelationInverse"
	private List<Client> clients = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue pour que le id auto_incrémenté par mysql ou h2 ou ...
	// remonte bien en mémoire dans .numero de l"onjet java
	private Long numero;

	@Column(name = "label", length = 64) // VARCHAR(64)
	private String label;

	private Double solde;

	@OneToMany(mappedBy = "compte")
	//@JsonIgnore
	private List<Operation> operations;

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	public Compte() {
		this(null, null, null);
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
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

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
