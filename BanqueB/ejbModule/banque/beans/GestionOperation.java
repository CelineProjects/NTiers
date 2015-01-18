package banque.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entities.Compte;
import banque.entities.CompteEpargne;
import banque.entities.Historique;
import banque.entities.Mouvement;
import banque.entities.TypeMouvement;



/**
 * Session Bean implementation class GestionOperation
 */
@Stateful
@LocalBean

public class GestionOperation implements GestionOperationRemote,
		GestionOperationLocal {

	@PersistenceContext
	
	EntityManager em;
	@EJB
	GestionClient gcl;
	
	@EJB
	GestionCompte gcomp;
	
	@EJB
	GestionMouvement gm;
	
	@EJB
	GestionOperation go;
	
	@EJB
	GestionHistorique gh;

	Compte compte = null;
	
	

	public GestionClient getGcl() {
		return gcl;
	}

	public void setGcl(GestionClient gcl) {
		this.gcl = gcl;
	}

	public GestionCompte getGcomp() {
		return gcomp;
	}

	public void setGcomp(GestionCompte gcomp) {
		this.gcomp = gcomp;
	}

	public GestionMouvement getGm() {
		return gm;
	}

	public void setGm(GestionMouvement gm) {
		this.gm = gm;
	}

	public GestionOperation getGo() {
		return go;
	}

	public void setGo(GestionOperation go) {
		this.go = go;
	}

	public GestionHistorique getGh() {
		return gh;
	}

	public void setGh(GestionHistorique gh) {
		this.gh = gh;
	}

	/**
	 * Default constructor.
	 */

	@Override
	public void choisirCompte(Compte c) {
		// TODO Auto-generated method stub
		this.compte = c;

	}

	@Override
	public void validerOperation() throws CompteInconnu {
		// TODO Auto-generated method stub
		compte = em.find(Compte.class, compte.getId());
		if (compte != null)
			em.merge(compte);
		else
			throw new CompteInconnu();

	}

	@Override
	public void depot(float montant,String depositaire) {
		// TODO Auto-generated method stub
		compte.setSolde(compte.getSolde() + montant);
		/*modifier l'Historique du compte*/
		compte.getHistorique().ajoutMouvement(
				gm.ajouterMouvement(new Mouvement(Calendar.getInstance().getTime(), TypeMouvement.DEPOT, montant,depositaire)));
		gh.modifierHistorique(compte.getHistorique());
		/*ajout interet pour compte epargne*/
		if (compte instanceof CompteEpargne) {
			compte.setSolde(compte.getSolde() + (((CompteEpargne) compte).getInteret()*montant));
			
			
		}
		try {
			validerOperation();
		} catch (CompteInconnu e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void retrait(float montant) {
		// TODO Auto-generated method stub

		compte.setSolde(compte.getSolde() - montant);
		if (compte.getSolde() < compte.getDecouvert())
			compte.setSolde(compte.getSolde() - compte.getPenalite() * montant);

		/* update historique */
		compte.getHistorique().ajoutMouvement(
				gm.ajouterMouvement(new Mouvement(Calendar.getInstance().getTime(), TypeMouvement.RETRAIT, montant,"moi")));
		gh.modifierHistorique(compte.getHistorique());
		
		try {
			validerOperation();
		} catch (CompteInconnu e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Historique consulterHistorique() {
		// TODO Auto-generated method stub
		return compte.getHistorique();
	}

	@Override
	public Historique consulterHistorique(Date d) {
		// TODO Auto-generated method stub
		List<Mouvement> m = new ArrayList<Mouvement>();
		for (Mouvement mouv : compte.getHistorique().getMouvements()) {
			if (mouv.getDate().after(d))
				m.add(mouv);
		}
		Historique h = new Historique();
		h.setMouvements(m);

		return h;
	}

	@Override
	public float conuslterSolde() {
		// TODO Auto-generated method stub
		return compte.getSolde();
	}

	@Override
	public Historique consulterLastMouvement() {
		// TODO Auto-generated method stub
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.add(Calendar.DATE, -7);
		return this.consulterHistorique(calendar.getTime());

	}

	

	@Override
	public Boolean transfert(String rib,float montant) throws CompteInconnu {
		Compte c=gcomp.findCompte(rib);
		go.choisirCompte(c);
		if(c!=null){
			retrait(montant);
			go.depot(montant, c.getClient().getNom());
			validerOperation();
			go.validerOperation();
			return true;
			
			
						
		}
		else return false;
		// TODO Auto-generated method stub

	}

}
