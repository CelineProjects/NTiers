package banque.beans;

import java.util.List;

import javax.ejb.Local;

import banque.entities.Historique;

@Local
public interface GestionHistoriqueLocal {
	Historique ajouterHistorique(Historique unHistorique);

	public void retirerHistorique(Historique historique);
	
	public List<Historique> getListeHistoriques() ;

	public void modifierHistorique(Historique historique);
}
