package banque.entities;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Historique
 *
 */
@Entity

public class Historique implements Serializable {

	@GeneratedValue  
	@Id
	private int id;
	private static final long serialVersionUID = 1L;
	@OneToMany(fetch = FetchType.EAGER, cascade = REMOVE, mappedBy = "historique")
	private List<Mouvement> mouvements = new ArrayList<Mouvement>();
	@OneToOne
	private Compte compte;
	
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public List<Mouvement> getMouvements() {
		return mouvements;
	}
	public void setMouvements(List<Mouvement> mouvements) {
		this.mouvements = mouvements;
	}
	public Historique() {
		super();
	}   
	public void ajoutMouvement(Mouvement mouvement){
		this.mouvements.add(mouvement);
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
