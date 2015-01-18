package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entities.Historique;

@Remote
public interface GestionHistoriqueRemote {

		Historique ajouterHistorique(Historique unHistorique);

		public void retirerHistorique(Historique historique);
		
		public List<Historique> getListeHistoriques() ;

		public void modifierHistorique(Historique historique);

}
