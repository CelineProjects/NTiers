package banque.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;





import banque.entities.Client;
import banque.entities.Compte;
import banque.entities.Historique;
import banque.entities.Mouvement;

/**
 * Session Bean implementation class GestionCompte
 */
@Stateless
@LocalBean
public class GestionCompte implements GestionCompteRemote, GestionCompteLocal {

	
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionCompte() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Compte ajouterCompte(Compte unCompte) {
		// TODO Auto-generated method stub
		em.persist(unCompte);
		return unCompte;
	}

	@Override
	public void retirerCompte(Compte compte) {
		// TODO Auto-generated method stub
		compte=em.find(Compte.class, compte.getId());
		em.remove(compte);
	}

	
	public List<Compte> getListeComptes() {
		// TODO Auto-generated method stub
		
		
		 List<Compte> comptes =em.createQuery("Select l from Compte l").getResultList();
		 return comptes;
	}

	@Override
	public void modifierCompte(Compte compte) throws CompteInconnu {
		// TODO Auto-generated method stub
		Compte c= em.find(Compte.class, compte.getId());
		if (c != null)
			em.merge(compte);
		else
			throw new CompteInconnu();
		
	}

	@Override
	public Compte findCompte(String rib) {
		// TODO Auto-generated method stub
		

		List<Compte> comptes = getListeComptes();
		
		for(Compte c : comptes){
			if(c.getRib().equals(rib))
			return c;
		}
		return null;
	}


}
