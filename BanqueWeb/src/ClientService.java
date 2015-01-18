
 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import banque.beans.GestionClient;
import banque.entities.Client;
 

 
@ManagedBean(name="clientService", eager = true)
@ApplicationScoped
public class ClientService {
	@EJB
	private GestionClient clientDOA;
	
	 @PostConstruct
	    public void init() {
		 clients=clientDOA.getListeClients();
		 
	 }
    private List<Client> clients;

	public List<Client> getClients() {
		
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public Client findById(int id){
		
		return clientDOA.findClient(id);
	}
    
    

}