/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberFilterData {

    private String ouCodeID;
    private Character Status;
    private Date membershipDateFrom;
    private Date membershipDateTo;
    private String occupation;
    private Integer ageFrom ;
    private Integer ageTo;
    private String compensation;
    private boolean personStat;
    
    /**
     * Creates a new instance of memberFilterData
     */
    public memberFilterData() {
    }

    public String getOuCodeID() {
        return ouCodeID;
    }

    public void setOuCodeID(String ouCodeID) {
        this.ouCodeID = ouCodeID;
    }

    public Character getStatus() {
        return Status;
    }

    public void setStatus(Character Status) {
        this.Status = Status;
    }

    public Date getMembershipDateFrom() {
        return membershipDateFrom;
    }

    public void setMembershipDateFrom(Date membershipDateFrom) {
        this.membershipDateFrom = membershipDateFrom;
    }

    public Date getMembershipDateTo() {
        return membershipDateTo;
    }

    public void setMembershipDateTo(Date membershipDateTo) {
        this.membershipDateTo = membershipDateTo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public boolean isPersonStat() {
        return personStat;
    }

    public void setPersonStat(boolean personStat) {
        this.personStat = personStat;
    }
    
}
