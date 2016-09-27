package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.CoopAssociate;
import service.CoopAssociateFacadeREST;

@ManagedBean
@SessionScoped
public class associateListModel implements Serializable {

    @EJB
    private CoopAssociateFacadeREST coopAssociateFacadeREST;
    private List<CoopAssociate> associateList;
    private ArrayList<CoopAssociate> associateArrList;
    private DataModel<CoopAssociate> associateModel;
    private CoopAssociate associate;
    private CoopAssociate selectedAssociate;
    

    public associateListModel() {

    }

    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("associateListModel", null);
    }

    public List<CoopAssociate> getAssociateList() {
        if (associateList == null) {
            associateList = new ArrayList<>();
        }
        return associateList;
    }

    public void setAssociateList(List<CoopAssociate> associateList) {
        this.associateList = associateList;
    }

    public ArrayList<CoopAssociate> getAssociateArrList() {
        if (associateArrList == null) {
            associateArrList = new ArrayList<>();
        }
        return associateArrList;
    }

    public void setAssociateArrList(ArrayList<CoopAssociate> associateArrList) {
        this.associateArrList = associateArrList;
    }

    public DataModel<CoopAssociate> getAssociateModel() {
        if (associateModel == null) {
            associateModel = new ListDataModel<>(getAssociateList());
        }
        return associateModel;
    }

    public void setAssociateModel(DataModel<CoopAssociate> associateModel) {
        this.associateModel = associateModel;
    }

    public CoopAssociate getAssociate() {
        if (associate == null) {
            associate = new CoopAssociate();
        }
        return associate;
    }

    public void setAssociate(CoopAssociate associate) {
        this.associate = associate;
    }

    public CoopAssociate getSelectedAssociate() {
        if (selectedAssociate == null) {
            selectedAssociate = new CoopAssociate();
        }
        return selectedAssociate;
    }

    public void setSelectedAssociate(CoopAssociate selectedAssociate) {
        this.selectedAssociate = selectedAssociate;
    }

}
