package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entities.Compte;
import banque.entities.Historique;
import banque.entities.Mouvement;

@Remote
public interface GestionCompteRemote {

	Compte ajouterCompte(Compte unCompte);

	public void retirerCompte(Compte compte);
	
	public List<Compte> getListeComptes() ;
	
	public Compte findCompte(String rib);

	public void modifierCompte(Compte compte) throws CompteInconnu;

}
