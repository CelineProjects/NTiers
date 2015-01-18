
 
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import banque.beans.GestionClient;
import banque.entities.Client;
 

 
@FacesConverter("entitieConverter")
public class EntitieConverter implements Converter {
	

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
        if(value != null && value.trim().length() > 0) {
            try {
            	 ClientService service = (ClientService) fc.getExternalContext().getApplicationMap().get("clientService");
            	 System.out.println(value);
                 
       Client c=  service.findById(Integer.parseInt(value));
       System.out.println(c.getPassword());
                 return c;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entities."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Client) object).getId());
        }
        else {
            return null;
        }
    }   
}