package banque.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompteStandard
 *
 */
@Entity

public class CompteStandard extends Compte implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public CompteStandard() {
		super();
		this.decouvert=0;
		
	}
   
}
