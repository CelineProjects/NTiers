package banque.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entities.Client;
import banque.entities.Compte;

/**
 * Session Bean implementation class GestionClient
 */
@Stateless
@LocalBean
public class GestionClient implements GestionClientRemote, GestionClientLocal {
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */

	public GestionClient() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public Client ajouterClient(Client unClient) {
		// TODO Auto-generated method stub
		em.persist(unClient);
		return unClient;
	}

	@Override
	public List<Client> getListeClients() {
		// TODO Auto-generated method stub
		List<Client> clients = em.createQuery("Select c from Client c")
				.getResultList();
		return clients;
	}

	@Override
	public void retirerClient(Client client) {
		// TODO Auto-generated method stub
		client = em.find(Client.class, client.getId());
		em.remove(client);
	}
	public Client findClient(int id) {
		 Client client = em.find(Client.class, id);
		return client;
		
	}

	@Override
	public Client findClient(String name) {
		// TODO Auto-generated method stub
		
		Client client = null;

		List<Client> clients = getListeClients();
		
		for(Client c : clients){
			if(c.getNom().equals(name))
			return client;
		}
		return null;
		
	}
	
	public Client findClientByLogin(String login) {
		// TODO Auto-generated method stub
		
		Client client = null;

		List<Client> clients = getListeClients();
		
		for(Client c : clients){
			if(c.getLogin().equals(login))
			return c;
		}
		return null;
		
	}
	public Client findClientById(int id) {
		// TODO Auto-generated method stub
		Client c=em.find(Client.class, id);

		return c;
		
	}
	@Override
	public void modifierClient(Client client) throws ClientInconnu {
		// TODO Auto-generated method stub
		Client c = em.find(Client.class, client.getId());
		if (c != null)
			em.merge(client);
		else
			throw new ClientInconnu();

	}

}
