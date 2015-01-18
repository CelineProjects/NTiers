import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import banque.beans.GestionClient;
import banque.entities.Client;


@ManagedBean
@SessionScoped


public class ClientMB {
	
	private List<Client> selectedClient;
	private Client currentClient;
	private String client1,login,password;
	
	
	
@EJB
	private GestionClient clientDAO;
	

//@ManagedProperty(value="#{clientService}")
//private ClientService service;
	

	public List<Client> getSelectedClient() {
		
		return selectedClient;
	}

	public void setSelectedClient(List<Client> selectedClient) {
		if(selectedClient!=null)
			System.out.println(selectedClient+"==="+selectedClient);
		this.selectedClient = selectedClient;
		if(this.selectedClient!=null)
			System.out.println(this.selectedClient+"==="+this.selectedClient);
	}

	public List<Client> completeClient(String query) {
		
		 List<Client> allClients = clientDAO.getListeClients();
		List<Client> filteredClient = new ArrayList<Client>();
		 for (int i = 0; i < allClients.size(); i++) {
	            Client skin = allClients.get(i);
	            if(skin.getNom().toLowerCase().contains(query)) {
	                filteredClient.add(skin);
	            }
	          
	        }
		
		setSelectedClient(filteredClient);
	//	service.setClients(filteredClient);
		
		return filteredClient;
		

	}
	
	
	
	
	
	public String getClient1() {
		return client1;
	}

	public void setClient1(String client) {
		for(Client c:selectedClient){
			if(c.getNom().equals(client))
				setCurrentClient(c);
			break;
		}
		this.client1=client;
	}
	
	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void log() throws IOException {
		FacesContext context= FacesContext.getCurrentInstance();
		if((login.equals(currentClient.getLogin())) && password.equals(currentClient.getPassword())){
			
		
	    context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO,"succès", "Bienvenue  "+currentClient.getLogin()
	    		));
				 context.getExternalContext().redirect("operation.xhtml");
				 
		}
		else 
			{context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_WARN,"echec", "Attention le login ou mot de passe erroné "
		    		));
			 //context.getExternalContext().redirect("client.xhtml");
			
			}
			
	}
}
