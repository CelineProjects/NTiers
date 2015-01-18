package banque.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompteEpargne
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CompteEpargne extends Compte implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CompteEpargne() {
		super();
		this.decouvert=0;
		interet=(float) 0.01;
	}
	public float getInteret() {
		return interet;
	}
	public void setInteret(float interet) {
		this.interet = interet;
	}

}
