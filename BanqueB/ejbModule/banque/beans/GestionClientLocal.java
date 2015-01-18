package banque.beans;

import java.util.List;

import javax.ejb.Local;

import banque.entities.Client;



@Local
public interface GestionClientLocal {
	Client ajouterClient(Client unClient);

	public List<Client> getListeClients();

	public void retirerClient(Client client);

	public Client findClient(String name);
	public Client findClientByLogin(String login);
	public Client findClientById(int id);

	public void modifierClient(Client client) throws ClientInconnu;

	
}
