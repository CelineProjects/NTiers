
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import banque.beans.GestionClient;
import banque.beans.GestionCompte;
import banque.entities.Compte;

 
@ManagedBean
@SessionScoped
public class ConsultMB implements Serializable {
     
    private List<Compte> comptes;
     
    private Compte selectedCompte;
    @EJB
    private GestionClient clientDAO;
    @EJB
    private GestionCompte compteDAO;
     

    public List<Compte> getComptes() {
    	comptes=compteDAO.getListeComptes();
        return comptes;
    }
 
 
 
    public Compte getSelectedCompte() {
        return selectedCompte;
    }
    
    public void suprimerCompte()throws EJBException {
    	System.out.println(selectedCompte);
       compteDAO.retirerCompte(selectedCompte);
       
      
    }
 
    public void setSelectedCompte(Compte selectedCompte) {
        this.selectedCompte = selectedCompte;
    }
}