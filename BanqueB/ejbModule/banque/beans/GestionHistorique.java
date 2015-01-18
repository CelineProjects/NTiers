package banque.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entities.Historique;
import banque.entities.Mouvement;

/**
 * Session Bean implementation class GestionHistorique
 */
@Stateless
@LocalBean
public class GestionHistorique implements GestionHistoriqueRemote, GestionHistoriqueLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
    public GestionHistorique() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Historique ajouterHistorique(Historique unHistorique) {
		// TODO Auto-generated method stub
				em.persist(unHistorique);
				return unHistorique;
	}

	@Override
	public void retirerHistorique(Historique historique) {
		// TODO Auto-generated method stub
				historique = em.find(Historique.class, historique.getId());
				em.remove(historique);
		
	}

	@Override
	public List<Historique> getListeHistoriques() {
		// TODO Auto-generated method stub
		return em.createQuery("Select h from Historique h").getResultList();
	}

	@Override
	public void modifierHistorique(Historique historique) {
		// TODO Auto-generated method stub
		Historique m = em.find(Historique.class, historique.getId());
		if (m != null)
			em.merge(historique);
		
	}

}
