/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vic
 */
@Entity
@Table(name = "coop_associate_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAssociateUser.findAll", query = "SELECT c FROM CoopAssociateUser c"),
    @NamedQuery(name = "CoopAssociateUser.findByAssociateNo", query = "SELECT c FROM CoopAssociateUser c WHERE c.associateNo = :associateNo"),
    @NamedQuery(name = "CoopAssociateUser.findByUserId", query = "SELECT c FROM CoopAssociateUser c WHERE c.userId = :userId")})
public class CoopAssociateUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "associate_no")
    private Integer associateNo;
    @Column(name = "user_id")
    private BigInteger userId;

    public CoopAssociateUser() {
    }

    public CoopAssociateUser(Integer associateNo) {
        this.associateNo = associateNo;
    }

    public Integer getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(Integer associateNo) {
        this.associateNo = associateNo;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (associateNo != null ? associateNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAssociateUser)) {
            return false;
        }
        CoopAssociateUser other = (CoopAssociateUser) object;
        if ((this.associateNo == null && other.associateNo != null) || (this.associateNo != null && !this.associateNo.equals(other.associateNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAssociateUser[ associateNo=" + associateNo + " ]";
    }
    
}
