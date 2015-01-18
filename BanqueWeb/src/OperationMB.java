

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import banque.beans.CompteInconnu;
import banque.beans.GestionClient;
import banque.beans.GestionCompte;
import banque.entities.Compte;
import java.lang.Math;

 
@ManagedBean
@SessionScoped
public class OperationMB implements Serializable {
	 @ManagedProperty("#{operationService}")
	    private OperationService operationService;
    
    
    
	 private Compte selectedCompte;
	    private String montant,ribCible,op;
	    
    


  
    public void setoperationService(OperationService operationService) {
		this.operationService = operationService;
	}





	
    
    
    
    
    public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public void depot() throws CompteInconnu, IOException{
		FacesContext context= FacesContext.getCurrentInstance();
    	
    	System.out.println(selectedCompte.getId());
    	
    	
    	operationService.choisirCompte(selectedCompte);
    	
    	operationService.depot(Math.abs(Float.parseFloat(montant)), "moi");
    	
    	System.out.println(selectedCompte.getId()+"++"+selectedCompte.getSolde());  
    	
    	 context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO,"succès", "dépôt Effectuer"
 	    		));
    	 context.getExternalContext().redirect("operation.xhtml");
    	 montant="0";
    	
    }
 
	
	public void effectuerVir() throws CompteInconnu, IOException {
		
		System.out.println("virement ");
		System.out.println(selectedCompte.getId());

		 System.out.println("virement ");
	 		operationService.choisirCompte(selectedCompte);
	 		FacesContext context= FacesContext.getCurrentInstance();
		
		if(operationService.transfert(ribCible, Math.abs(Float.parseFloat(montant)))){
			
			
		
	    context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO,"succès", "Virement Effectuer"
	    		));
	    		System.out.println("virement effectué");
				 context.getExternalContext().redirect("operation.xhtml");
				 montant="0";
				 
		}
		else 
			{context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_WARN,"echec", "Attention le transfert n'a pas abouti :\n RIB erroné "
		    		));
			 //context.getExternalContext().redirect("client.xhtml");
			
			}
			
	
		
	 }
 

 
 
 
 public void ret() throws CompteInconnu, IOException{
	 FacesContext context= FacesContext.getCurrentInstance();
	 
	 System.out.println(selectedCompte.getId()+"++++");
	 if(montant!=null){
 	operationService.choisirCompte(selectedCompte);
 	
 	operationService.retrait(Math.abs(Float.parseFloat(montant)));
 	 context.getExternalContext().redirect("operation.xhtml");
 	 montant="0";
	 }
 	
 }
    public String getMontant() {
    	
		return montant;
	}
    





	public void setMontant(String montant) {
		this.montant = montant;
	}





	public String getRibCible() {
		return ribCible;
	}





	public void setRibCible(String ribCible) {
		this.ribCible = ribCible;
	}





	public GestionCompte getCompteDAO() {
		return compteDAO;
	}





	public void setCompteDAO(GestionCompte compteDAO) {
		this.compteDAO = compteDAO;
	}





	@EJB
    private GestionClient clientDAO;
    @EJB
    private GestionCompte compteDAO;
     

  
   
 
    public void setSelectedCompte(Compte selectedCompte) {
        this.selectedCompte = selectedCompte;
    }





	public Compte getSelectedCompte() {
		return selectedCompte;
	}
}
