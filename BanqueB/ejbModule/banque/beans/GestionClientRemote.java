package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entities.Client;




@Remote
public interface GestionClientRemote {
	
	Client ajouterClient(Client unClient);

	public List<Client> getListeClients();

	public void retirerClient(Client client);

	public Client findClient(String name);
	public Client findClientByLogin(String login);
	public Client findClientById(int id);

	public void modifierClient(Client client) throws ClientInconnu;

}
