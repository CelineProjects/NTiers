import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class AjoutClientMB {

	public List<Compte> getListCompte() {
		return listCompte;
	}

	private Client newClient;
	private Compte selectedRow;

	private List<Compte> listCompte;

	@EJB
	private GestionClient clientDAO;
	@EJB
	private GestionCompte CompteDAO;
	


	private String typeCompte, rib, nom, prenom, login, adress, password1,
			password2;

	public String getTypeCompte() {
		return typeCompte;
	}

	@PostConstruct
	public void Init() {
		if (listCompte == null) {
			listCompte = new ArrayList<Compte>();

			System.out.println("les compte" + listCompte);
		}

	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public GestionClient getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(GestionClient clientDAO) {
		this.clientDAO = clientDAO;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Client getNewClient() {
		return newClient;
	}

	public void setNewClient(Client newClient) {
		this.newClient = newClient;
	}

	public void ajouterNewClient() throws ClientInconnu, CompteInconnu {

		System.out.println(nom);
		newClient = new Client();

		newClient.setAdress(adress);
		newClient.setLogin(login);
		newClient.setNom(nom);
		newClient.setPrenom(prenom);
		newClient.setPassword(password1);

		newClient.setComptes(listCompte);
		System.out.println(newClient.getComptes());
		if (!existLogin(newClient)) {
			newClient = clientDAO.ajouterClient(newClient);

			newClient.setComptes(listCompte);
			for (Compte c : listCompte) {
				
				c.setClient(newClient);
				c = CompteDAO.ajouterCompte(c);
				
				
				System.out.println(c);
			}

			
			listCompte.removeAll(getListComptes());

			System.out.println(listCompte);

			newClient = null;
			listCompte.clear();
		}

	}

	public void ajouterCompte() {
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

		if (!exist(c)){
			
			listCompte.add(c);
		}

		System.out.println("addcompte" + c.getId() + ";" + listCompte);

	}

	private boolean exist(Compte comp){
		
		
		Compte cc=CompteDAO.findCompte(comp.getRib());
		if(cc!=null){
			System.out.println("exist");
			FacesContext context = FacesContext.getCurrentInstance();
			
		    context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"erreur", "votre Compte existe déjà dans la liste"));
		    return true;
}
		
		
	
	
	for(Compte c: listCompte){
		if(c.getRib().equals(comp.getRib()) ){
					System.out.println("exist");
					FacesContext context = FacesContext.getCurrentInstance();
					
				    context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"erreur", "votre Compte existe déjà dans la liste"));
				    return true;
		}
	}
	return false;
}

	private boolean existLogin(Client cl) {

		Client clie=clientDAO.findClientByLogin(cl.getLogin());
		if(clie!=null){
			System.out.println("exist");
			FacesContext context = FacesContext.getCurrentInstance();
			
		    context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"erreur", "Ce client existe déjà dans la base de données sivous "
		    		+ "voulez lui ajouter des compte allez à la partie ajouter Compte"));
		    return true;
}
		

		return false;
	}
	
	
	public void supprimerCompte() {
		System.out.println("suppression");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		
			String ribS = params.get("ribSupp");
			for(Compte c : listCompte){
				if(c.getRib().equals(ribS)){
					selectedRow=c;
				break;	
				}
				
			}
			if(listCompte.remove(selectedRow))
				context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO,"suppression", "Compte supprimer de la liste"));
			System.out.println(listCompte);
		
		
		
	}

	public List<Compte> getListComptes() {
		return listCompte;

	}

	public void setListCompte(List<Compte> listCompte) {
		listCompte = listCompte;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public Compte getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Compte selectedRow) {
		this.selectedRow = selectedRow;
	}

}
