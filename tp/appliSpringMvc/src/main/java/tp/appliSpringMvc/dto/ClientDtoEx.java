package tp.appliSpringMvc.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientDtoEx extends ClientDto {
	private List<CompteDto> comptes = new ArrayList<>();

	

	@Override
	public String toString() {
		return "ClientDtoEx [comptes=" + comptes + ", toString()=" + super.toString() + "]";
	}

	public ClientDtoEx() {
		this(null,null,null,null,null);
	}

	public ClientDtoEx(Long numero, String prenom, String nom, String email, String adresse) {
		super(numero, prenom, nom, email, adresse);
	}
	
	
	public ClientDtoEx(Long numero, String prenom, String nom, String email, String adresse,List<CompteDto> comptes) {
		super(numero, prenom, nom, email, adresse);
		this.comptes = comptes;
	}



	public List<CompteDto> getComptes() {
		return comptes;
	}



	public void setComptes(List<CompteDto> comptes) {
		this.comptes = comptes;
	}
	
	
	
}
