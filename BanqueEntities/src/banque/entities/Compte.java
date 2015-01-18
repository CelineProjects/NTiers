package banque.entities;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comppte
 * 
 */
@Entity
public class Compte implements Serializable {

	@GeneratedValue
	@Id
	private int id;
	@Column(unique=true)
	private String rib;
	private static final long serialVersionUID = 1L;
	protected float solde, decouvert=0,penalite=(float) 0.05,interet=0;
	public float getInteret() {
		return interet;
	}

	public void setInteret(float interet) {
		this.interet = interet;
	}

	public void setDecouvert(float decouvert) {
		this.decouvert = decouvert;
	}

	public float getPenalite() {
		return penalite;
	}

	public void setPenalite(float penalite) {
		this.penalite = penalite;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = REMOVE, mappedBy = "compte")
	private Historique historique;
	@ManyToOne
	private Client client;

	public float getDecouvert() {
		return decouvert;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Compte() {
		super();
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

}
