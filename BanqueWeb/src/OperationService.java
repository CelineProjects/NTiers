
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sun.net.www.http.Hurryable;
import banque.beans.CompteInconnu;
import banque.beans.GestionClient;
import banque.beans.GestionCompte;
import banque.beans.GestionHistorique;
import banque.beans.GestionMouvement;
import banque.entities.Compte;
import banque.entities.CompteEpargne;
import banque.entities.Historique;
import banque.entities.Mouvement;
import banque.entities.TypeMouvement;

/**
 * Session Bean implementation class GestionOperation
 */
@ManagedBean(name = "operationService")
@SessionScoped
public class OperationService implements Serializable {

	@EJB
	GestionClient clientDAO;

	@EJB
	GestionCompte compteDAO;

	@EJB
	GestionMouvement mouvementDAO;

	@EJB
	GestionHistorique historiqueDAO;

	Compte compte = null;
	Historique historique;
	Mouvement mouvement;

	/**
	 * Default constructor.
	 * 
	 * @throws CompteInconnu
	 */

	public void choisirCompte(Compte c) throws CompteInconnu {
		// TODO Auto-generated method stub
		this.compte = c;
		if (compte.getHistorique() == null) {
			historique = new Historique();
			System.out.println("1");
			historique.setCompte(this.compte);
			System.out.println("12");
			this.compte.setHistorique(historique);
			System.out.println("123");

			historique = historiqueDAO.ajouterHistorique(historique);
			System.out.println("112324");
			compteDAO.modifierCompte(this.compte);
			System.out.println("189");
		} else {
			historique = this.compte.getHistorique();

		}

	}

	public void validerOperation() throws CompteInconnu {
		// TODO Auto-generated method stub

		if (compte != null) {
			System.out.println(compte.getId());

			mouvement.setHistorique(historique);
			historique.ajoutMouvement(mouvement);
			System.out.println(compte.getId());

			mouvement = mouvementDAO.ajouterMouvement(mouvement);

			historiqueDAO.modifierHistorique(historique);

			mouvement = null;

		} else
			throw new CompteInconnu();

	}

	public void depot(float montant, String depositaire) throws CompteInconnu {
		// TODO Auto-generated method stub
		compte.setSolde(compte.getSolde() + montant);

		/* creer Mouvement */
		mouvement = new Mouvement(Calendar.getInstance().getTime(),
				TypeMouvement.DEPOT, montant, depositaire);

		System.out.println(mouvement.getType());

		/* ajout interet pour compte epargne */
		if (compte instanceof CompteEpargne) {
			compte.setSolde(compte.getSolde()
					+ (((CompteEpargne) compte).getInteret() * montant));

		}
		compteDAO.modifierCompte(compte);
		validerOperation();

	}

	public void retrait(float montant) throws CompteInconnu {
		// TODO Auto-generated method stub

		compte.setSolde(compte.getSolde() - montant);
		if (compte.getSolde() < compte.getDecouvert()) {
			compte.setSolde(compte.getSolde() - compte.getPenalite() * montant);

		}

		/* creer Mouvement */
		mouvement = new Mouvement(Calendar.getInstance().getTime(),
				TypeMouvement.RETRAIT, montant, "moi");

		compteDAO.modifierCompte(compte);
		validerOperation();

	}

	public Historique consulterHistorique() {
		// TODO Auto-generated method stub
		return compte.getHistorique();
	}

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

	public float conuslterSolde() {
		// TODO Auto-generated method stub
		return compte.getSolde();
	}

	public Historique consulterLastMouvement() {
		// TODO Auto-generated method stub
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.add(Calendar.DATE, -7);
		return this.consulterHistorique(calendar.getTime());

	}

	public Boolean transfert(String rib, float montant) throws CompteInconnu {
		Compte c = compteDAO.findCompte(rib);
		
		Compte myC = compte;
		System.out.println(myC.getRib());

		if (c != null) {
			System.out.println(c.getRib());
			System.out.println("rib existe");
			retrait(montant);

			this.choisirCompte(c);

			if (c.getClient().getNom().equals(myC.getClient().getNom()))
				this.depot(montant, "moi from other compte");
			else
				this.depot(montant, myC.getClient().getNom());

			this.choisirCompte(myC);
			return true;

		} else
			return false;
		// TODO Auto-generated method stub

	}

}
