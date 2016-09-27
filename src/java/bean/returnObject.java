/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopEducInfo;
import model.CoopMemberStatus;
import model.CoopOrgUnit;
import model.CoopSkills;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class returnObject implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    private String memberType;

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

    public List<String> memOccupationList() {
        return emf.createEntityManager().createQuery("SELECT DISTINCT(c.preoccupation) FROM CoopMember c WHERE c.preoccupation IS NOT NULL").getResultList();
    }

    public String fullname(String pprefix, String lastName, String firstName, String middleName, String suffix) {
        String str = "";

        if (pprefix != null) {
            str += pprefix + " ";
        }
        if (firstName != null) {
            str += firstName + " ";
        }
        if (middleName != null) {
            str += middleName + " ";
        }
        if (lastName != null) {
            str += lastName + " ";
        }
        if (suffix != null) {
            str += suffix;
        }

        return str;
    }

    public String educInfo(CoopEducInfo educInfo) {
        String str = "";

        //School Name
        if (educInfo.getSchoolName() != null) {
            str += educInfo.getSchoolName();
        } else {
            str += "-";
        }
        str += ";";

        //School Level
        if (educInfo.getSchoolLevel() != null) {
            str += educInfo.getSchoolLevel();
        } else {
            str += "-";
        }
        str += ";";

        //Course
        if (educInfo.getCourse() != null) {
            str += educInfo.getCourse();
        } else {
            str += "-";
        }
        str += ";";

        //Graduated
        if (educInfo.getGraduated() != null) {
            if (educInfo.getGraduated()) {
                str += "GRADUATED";
            } else {
                str += "UNDER-GRADUATE";
            }
        } else {
            str += "UNDER-GRADUATE";
        }
        str += ";";

        //Last year attended
        if (educInfo.getYearLastAttended() != null) {
            str += educInfo.getYearLastAttended();
        } else {
            str += "-";
        }

        return str;
    }

    public void member() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        setMemberType(params.get("type"));
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

}
