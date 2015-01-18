import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import banque.beans.ClientInconnu;
import banque.beans.CompteInconnu;
import banque.beans.GestionClient;
import banque.beans.GestionCompte;


import banque.entities.Client;
import banque.entities.Compte;
import banque.entities.CompteEpargne;
import banque.entities.ComptePlatine;
import banque.entities.CompteStandard;

@ManagedBean
@RequestScoped
public class AjoutCompteMB {

	// private String client;
	// private List<Client> listClient;
	//
	//
	//
	//
	// @PostConstruct
	// public void Init(){
	// if(listClient==null){
	// listClient= clientDAO.getListeClients();
	//
	//
	// System.out.println("les compte"+listClient);
	// }
	//
	//
	// }
	//
	// public List<Client> getListClient() {
	// listClient= clientDAO.getListeClients();
	// return listClient;
	// }
	//
	// public void setListClient(List<Client> listClient) {
	// this.listClient = listClient;
	// }
	//
	// public String getClient() {
	// return client;
	// }
	//
	// public void setClient(String client) {
	// this.client = client;
	//
	// }
	
	
	private Client currentClient;

	public List<Client> completeClient(String query) {

		List<Client> allClients = clientDAO.getListeClients();
		List<Client> filteredClient = new ArrayList<Client>();
		for (int i = 0; i < allClients.size(); i++) {
			Client skin = allClients.get(i);
			if (skin.getNom().toLowerCase().contains(query)) {
				filteredClient.add(skin);
			}

		}

		return filteredClient;

	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	@EJB
	private GestionClient clientDAO;
	@EJB
	private GestionCompte CompteDAO;

	private String typeCompte, rib;

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;

	}

	public GestionClient getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(GestionClient clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void ajouterCompte() throws ClientInconnu, CompteInconnu {

		System.out.println("iiiiiiiii");
		Compte c;

		if (typeCompte.equals("cs"))
			c = new CompteStandard();

		else if (typeCompte.equals("ce"))
			c = new CompteEpargne();

		else if (typeCompte.equals("cp"))
			c = new ComptePlatine(100);

		else
			c = new Compte();

		c.setRib(rib);
		c.setSolde(0);
		System.out.println(rib);
		System.out.println(currentClient);
		//
		// for(Client cc: listClient){
		// if(cc.getNom().equals(client)){
		// c.setClient(cc);
		// cc.ajoutCompte(c);
		// clientDAO.modifierClient(cc);
		// }
		// if(!exist(c))
		// c=CompteDAO.ajouterCompte(c);
		// else CompteDAO.modifierCompte(c);
		//
		// break;
		//
		//
		//
		// }

		c.setClient(currentClient);
		currentClient.ajoutCompte(c);
		clientDAO.modifierClient(currentClient);

		if (!exist(c)) {
			
			c = CompteDAO.ajouterCompte(c);
			
			
			
			
			

			
			
			
			
			
			

		} else
			CompteDAO.modifierCompte(c);

		// System.out.println(clientDAO.findClient(client));

		// c.setClient(clientDAO.findClient(client));

		// client=null;

	}

	private boolean exist(Compte comp) {

		Compte cc = CompteDAO.findCompte(comp.getRib());
		if (cc != null) {
			System.out.println("exist");
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "erreur",
					"votre Compte existe déjà dans la liste"));
			return true;
		}

		return false;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

}
