package banque.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Mouvement
 * 
 */
@Entity
public class Mouvement implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private static final long serialVersionUID = 1L;
	private Date date;
	private String name;
	private TypeMouvement type;
	private float montant;
	@ManyToOne
	private Historique historique;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeMouvement getType() {
		return type;
	}

	public void setType(TypeMouvement type) {
		this.type = type;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Mouvement() {
		super();
	}
	public Mouvement(Date date,TypeMouvement typeMouvement,float montant,String name){
		super();
		this.date=date;
		this.montant=montant;
		this.type=typeMouvement;
		this.name=name;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
