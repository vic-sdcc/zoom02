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
import model.CoopMember;
import service.CoopMemberFacadeREST;

@ManagedBean
@SessionScoped
public class memberListModel implements Serializable {

    @EJB
    private CoopMemberFacadeREST coopMemberFacadeREST;
    private List<CoopMember> memberList;
    private ArrayList<CoopMember> memberArrList;
    private DataModel<CoopMember> memberModel;
    private CoopMember member;
    private CoopMember selectedMember;
    

    public memberListModel() {

    }

    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberListModel", null);
    }

    public List<CoopMember> getMemberList() {
        if (memberList == null) {
            memberList = new ArrayList<>();
        }
        return memberList;
    }

    public void setMemberList(List<CoopMember> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<CoopMember> getMemberArrList() {
        if (memberArrList == null) {
            memberArrList = new ArrayList<>();
        }
        return memberArrList;
    }

    public void setMemberArrList(ArrayList<CoopMember> memberArrList) {
        this.memberArrList = memberArrList;
    }

    public DataModel<CoopMember> getMemberModel() {
        if (memberModel == null) {
            memberModel = new ListDataModel<>(getMemberList());
        }
        return memberModel;
    }

    public void setMemberModel(DataModel<CoopMember> memberModel) {
        this.memberModel = memberModel;
    }

    public CoopMember getMember() {
        if (member == null) {
            member = new CoopMember();
        }
        return member;
    }

    public void setMember(CoopMember member) {
        this.member = member;
    }

    public CoopMember getSelectedMember() {
        if (selectedMember == null) {
            selectedMember = new CoopMember();
        }
        return selectedMember;
    }

    public void setSelectedMember(CoopMember selectedMember) {
        this.selectedMember = selectedMember;
    }

}
