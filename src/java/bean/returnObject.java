/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopMemberStatus;
import model.CoopOrgUnit;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class returnObject implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    public returnObject() {
        //CoopProspect q = (CoopProspect) emf.createEntityManager().createQuery("SELECT c FROM CoopProspect c").getResultList().get(0);
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Successful", "New data has been created."));
    }

    public List<CoopOrgUnit> ouList() {
        return emf.createEntityManager().createQuery("SELECT c FROM CoopOrgUnit c WHERE c.ouShortName LIKE 'PT%'").getResultList();
    }

    public List<CoopMemberStatus> memStatList() {
        return emf.createEntityManager().createQuery("SELECT c FROM CoopMemberStatus c").getResultList();
    }
    
    public List<String> memOccupationList(){
        return emf.createEntityManager().createQuery("SELECT DISTINCT(c.preoccupation) FROM CoopMember c WHERE c.preoccupation IS NOT NULL").getResultList();
    }

}
