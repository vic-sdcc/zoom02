/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class autoComplete {

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    public autoComplete() {

    }

    public List<String> completeSkill(String query) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT c.skillsName FROM CoopSkills c "
                + "WHERE UPPER(c.skillsName) LIKE UPPER('%" + query + "%')").getResultList();
    }
}
