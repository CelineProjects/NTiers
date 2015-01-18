package banque.beans;

import java.util.Date;

import javax.ejb.Local;

import banque.entities.Compte;
import banque.entities.Historique;

@Local
public interface GestionOperationLocal {

	public void choisirCompte(Compte c);

	public void validerOperation() throws CompteInconnu;

	public void retrait(float montant);

	public Historique consulterHistorique();

	public Historique consulterHistorique(Date d);

	public float conuslterSolde();

	public Historique consulterLastMouvement();

	void depot(float montant, String depositaire);


	Boolean transfert(String rib, float montant) throws CompteInconnu;


}
