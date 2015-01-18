package banque.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import banque.entities.Mouvement;

@Remote
public interface GestionMouvementRemote {
	
	Mouvement ajouterMouvement(Mouvement unMouvement);

	public void retirerMouvement(Mouvement Mouvement);
	
	public List<Mouvement> getListeMouvements() ;
	
	public List<Mouvement> findMouvement(Date date);

	public void modifierMouvement(Mouvement mouvement);
}
