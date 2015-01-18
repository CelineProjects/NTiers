package banque.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import banque.entities.Client;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Client
 * 
 */
@Entity
public class Client implements Serializable {

	@GeneratedValue
	@Id
	private int id;
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String login;
	private String nom, prenom, adress, password;
	@OneToMany(fetch = FetchType.EAGER, cascade = REMOVE, mappedBy = "client")
	private List<Compte> comptes = new ArrayList<Compte>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Compte> getComptes() {
		return comptes;
	}
	public void ajoutCompte(Compte compte){
		this.comptes.add(compte);
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
