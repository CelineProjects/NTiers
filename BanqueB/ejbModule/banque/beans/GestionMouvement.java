package banque.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entities.Mouvement;

/**
 * Session Bean implementation class GestionMouvement
 */
@LocalBean
@Stateless
public class GestionMouvement implements GestionMouvementRemote,
		GestionMouvementLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */

	public GestionMouvement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mouvement ajouterMouvement(Mouvement unMouvement) {
		// TODO Auto-generated method stub
		em.persist(unMouvement);
		return unMouvement;
	}

	@Override
	public void retirerMouvement(Mouvement Mouvement) {
		// TODO Auto-generated method stub
		Mouvement = em.find(Mouvement.class, Mouvement.getId());
		em.remove(Mouvement);
	}

	@Override
	public List<Mouvement> findMouvement(Date date) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT m FROM Mouvement m WHERE c.date LIKE :custDate")
				.setParameter("custDate", date).getResultList();
	}

	public List<Mouvement> getListeMouvements() {
		// TODO Auto-generated method stub
		return em.createQuery("Select m from Mouvement m").getResultList();
	}

	@Override
	public void modifierMouvement(Mouvement mouvement)  {
		// TODO Auto-generated method stub
		Mouvement m = em.find(Mouvement.class, mouvement.getId());
		if (m != null)
			em.merge(mouvement);
		

	}

}
