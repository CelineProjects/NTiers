import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import banque.beans.GestionClientRemote;
import banque.beans.GestionCompteRemote;
import banque.entities.Client;
import banque.entities.Compte;
import banque.entities.CompteEpargne;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main client=new Main();
		client.test1();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
	}

	private void test1() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Go !");
			
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.SECURITY_PRINCIPAL,"wattari");
		    jndiProperties.put(Context.SECURITY_CREDENTIALS, "wattari123");//
		    jndiProperties.put("jboss.naming.client.ejb.context", true);
			
			//Context context = new InitialContext();
			
			Context context = new InitialContext(jndiProperties);
			
			GestionCompteRemote gestionCompte = (GestionCompteRemote)context.lookup("EARtT/BanqueB/GestionCompte!banque.beans.GestionCompteRemote");
			
			Compte l = new Compte();
			l.setRib("12313-3212-3213");
			l.setSolde(23);
			
			CompteEpargne ln = new CompteEpargne();
			ln.setSolde((float) 12.33);
			ln.setRib("12313-3212-213213");
			ln.setInteret((float) 123.23);
			
			
			l = gestionCompte.ajouterCompte(l);
			ln = (CompteEpargne)gestionCompte.ajouterCompte(ln);
			
			
			Client h=new Client();
			h.setPrenom("Sylvain");
			h.setNom("Vauttier");
			
			GestionClientRemote gestionClient = (GestionClientRemote) context.lookup("EARtT/BanqueB/GestionClient!library.beans.GestionClientRemote");
			h=gestionClient.ajouterClient(h);
			
			
			
			
			
			//gestionClient
			
			gestionClient.retirerClient(h);
			
			System.out.println("Done !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

}