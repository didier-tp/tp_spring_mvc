package tp.appliSpringMvc.dto;

//cette classe "Customer" ou "ClientDto" ou ...
//correspond à un DTO (Data Transfert Object):

public class ClientDto {
	private Long numero;
	private String prenom;
	private String nom;
	private String email;
	private String adresse;

	public ClientDto(Long numero, String prenom, String nom, String email, String adresse) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
	}

	public ClientDto() {
		this(null, null, null, null, null);
	}

	@Override
	public String toString() {
		return "ClientDto [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", adresse="
				+ adresse + "]";
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}
