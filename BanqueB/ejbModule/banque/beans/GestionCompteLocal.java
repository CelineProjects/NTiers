package banque.beans;

import java.util.List;

import javax.ejb.Local;

import banque.entities.Compte;

@Local
public interface GestionCompteLocal {
	Compte ajouterCompte(Compte unCompte);

	public void retirerCompte(Compte compte);
	
	public List<Compte> getListeComptes() ;
	
	public Compte findCompte(String rib);

	public void modifierCompte(Compte compte) throws CompteInconnu;

}
