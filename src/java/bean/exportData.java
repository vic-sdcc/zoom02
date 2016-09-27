/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopAssociate;
import model.CoopEducInfo;
import model.CoopMember;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class exportData implements Serializable {

    /**
     * Creates a new instance of exportData
     */
    public exportData() {
    }

    private String filename;
    private List<Boolean> exportColumns;
    private DataConvert dConvert;
    Integer boolSize = 20;
    private returnObject rObjct;
    @PersistenceUnit
    EntityManagerFactory emf;

    public void memberList(List<CoopMember> memberData, memberFilterData filterData) {
        int x = 0, y = 0;
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }

        try {
            setFilename("/home/vic/membership/" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(y++).setCellValue("Member No.");
            rowhead.createCell(y++).setCellValue("Full Name");
            rowhead.createCell(y++).setCellValue("Organizational Unit");

            if (getExportColumns().get(0)) {
                rowhead.createCell(y++).setCellValue("Address");
            }
            if (getExportColumns().get(1)) {
                rowhead.createCell(y++).setCellValue("Gender");
            }
            if (getExportColumns().get(2)) {
                rowhead.createCell(y++).setCellValue("Civil Status");
            }
            if (getExportColumns().get(3)) {
                rowhead.createCell(y++).setCellValue("Birthdate");
            }
            if (getExportColumns().get(4)) {
                rowhead.createCell(y++).setCellValue("TIN");
            }
            if (getExportColumns().get(5)) {
                rowhead.createCell(y++).setCellValue("Pag-Ibig");
            }
            if (getExportColumns().get(6)) {
                rowhead.createCell(y++).setCellValue("Philhealth");
            }
            if (getExportColumns().get(7)) {
                rowhead.createCell(y++).setCellValue("SSS");
            }
            if (getExportColumns().get(8)) {
                rowhead.createCell(y++).setCellValue("GSIS");
            }
            if (getExportColumns().get(9)) {
                rowhead.createCell(y++).setCellValue("Membership Date");
            }
            if (getExportColumns().get(10)) {
                rowhead.createCell(y++).setCellValue("Membership Status");
            }
            if (getExportColumns().get(11)) {
                rowhead.createCell(y++).setCellValue("Skill");
            }
            if (getExportColumns().get(12)) {
                rowhead.createCell(y++).setCellValue("Compensation Bracket");
            }
            if (getExportColumns().get(13)) {
                rowhead.createCell(y++).setCellValue("Educational Attainment");
            }

            for (int i = 0; i != memberData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(x++).setCellValue(memberData.get(i).getMemNo());
                row.createCell(x++).setCellValue(getrObjct().fullname(memberData.get(i).getPPrefix(), memberData.get(i).getLastName(), memberData.get(i).getFirstName(), memberData.get(i).getMiddleName(), memberData.get(i).getSuffix()));
                row.createCell(x++).setCellValue(memberData.get(i).getOuCode().getOuShortName());
                if (getExportColumns().get(0)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getStreet() + " " + memberData.get(i).getBarangay() + " " + memberData.get(i).getCityMun() + " " + memberData.get(i).getProvince() != null ? memberData.get(i).getProvince() : "");
                }
                if (getExportColumns().get(1)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getGender());
                }
                if (getExportColumns().get(2)) {
                    row.createCell(x++).setCellValue(getdConvert().civilStatusConv(memberData.get(i).getCivilStatus()));
                }
                if (getExportColumns().get(3)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getBirthdate());
                }
                if (getExportColumns().get(4)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getTaxIdNo());
                }
                if (getExportColumns().get(5)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getPagIbig());
                }
                if (getExportColumns().get(6)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getPhilhealth());
                }
                if (getExportColumns().get(7)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getSss());
                }
                if (getExportColumns().get(8)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getGsis());
                }
                if (getExportColumns().get(9)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getMemDate());
                }
                if (getExportColumns().get(10)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getStatusId().getStatusName());
                }
                if (getExportColumns().get(11)) {
                    try {
                        String skl = (String) emf.createEntityManager().createQuery("SELECT c.skillsCode.skillsName FROM CoopSkillsMem c "
                                + "WHERE UPPER(c.skillsCode.skillsName) LIKE UPPER('%" + filterData.getSkill() + "%') "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(skl);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(12)) {
                    if (filterData.getCompensation() != null) {
                        row.createCell(x++).setCellValue(filterData.getCompensation());
                    } else {
                        try {
                            String cmpBrckt = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.compBracket FROM CoopEmplDtlMem c "
                                    + "WHERE c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList().get(0);
                            row.createCell(x++).setCellValue(cmpBrckt);
                        } catch (Exception e) {
                            row.createCell(x++).setCellValue("");
                        }
                    }
                }
                if (getExportColumns().get(13)) {
                    List<CoopEducInfo> educInfo = null;

                    //POST-GRADUATE
                    educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                            + "WHERE c.educInfoNum.schoolLevel ='POST-GRADUATE' "
                            + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                    for (int a = 0; a != educInfo.size(); a++) {
                        rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                        row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                    }

                    //COLLEGE
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='COLLEGE' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //VOCATIONAL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='VOCATIONAL' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //HIGH SCHOOL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='HIGH SCHOOL' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //ELEMENTARY
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='ELEMENTARY' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    if (educInfo.isEmpty()) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                x = 0;
            }

            FileOutputStream fileOut = new FileOutputStream(getFilename());
            workbook.write(fileOut);

            fileOut.close();
            System.out.println("Your excel file has been generated!");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Your excel file has been generated.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            System.out.println(ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while generating excel file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void associateList(List<CoopAssociate> associateData, memberFilterData filterData) {
        int x = 0, y = 0;
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }
        try {
            setFilename("/home/vic/membership/" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(y++).setCellValue("Member No.");
            rowhead.createCell(y++).setCellValue("Full Name");
            rowhead.createCell(y++).setCellValue("Organizational Unit");

            if (getExportColumns().get(0)) {
                rowhead.createCell(y++).setCellValue("Address");
            }
            if (getExportColumns().get(1)) {
                rowhead.createCell(y++).setCellValue("Gender");
            }
            if (getExportColumns().get(2)) {
                rowhead.createCell(y++).setCellValue("Civil Status");
            }
            if (getExportColumns().get(3)) {
                rowhead.createCell(y++).setCellValue("Birthdate");
            }
            if (getExportColumns().get(4)) {
                rowhead.createCell(y++).setCellValue("TIN");
            }
            if (getExportColumns().get(5)) {
                rowhead.createCell(y++).setCellValue("Pag-Ibig");
            }
            if (getExportColumns().get(6)) {
                rowhead.createCell(y++).setCellValue("Philhealth");
            }
            if (getExportColumns().get(7)) {
                rowhead.createCell(y++).setCellValue("SSS");
            }
            if (getExportColumns().get(8)) {
                rowhead.createCell(y++).setCellValue("GSIS");
            }
            if (getExportColumns().get(9)) {
                rowhead.createCell(y++).setCellValue("Membership Date");
            }
            if (getExportColumns().get(10)) {
                rowhead.createCell(y++).setCellValue("Membership Status");
            }
            if (getExportColumns().get(11)) {
                rowhead.createCell(y++).setCellValue("Skill");
            }
            if (getExportColumns().get(12)) {
                rowhead.createCell(y++).setCellValue("Compensation Bracket");
            }
            if (getExportColumns().get(13)) {
                rowhead.createCell(y++).setCellValue("Educational Attainment");
            }

            for (int i = 0; i != associateData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(x++).setCellValue(associateData.get(i).getMemNo());
                row.createCell(x++).setCellValue(getrObjct().fullname(associateData.get(i).getPPrefix(), associateData.get(i).getLastName(), associateData.get(i).getFirstName(), associateData.get(i).getMiddleName(), associateData.get(i).getSuffix()));
                row.createCell(x++).setCellValue(associateData.get(i).getOuCode().getOuShortName());
                if (getExportColumns().get(0)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getStreet() + " " + associateData.get(i).getBarangay() + " " + associateData.get(i).getCityMun() + " " + associateData.get(i).getProvince() != null ? associateData.get(i).getProvince() : "");
                }
                if (getExportColumns().get(1)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getGender());
                }
                if (getExportColumns().get(2)) {
                    row.createCell(x++).setCellValue(getdConvert().civilStatusConv(associateData.get(i).getCivilStatus()));
                }
                if (getExportColumns().get(3)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getBirthdate());
                }
                if (getExportColumns().get(4)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getTaxIdNo());
                }
                if (getExportColumns().get(5)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getPagIbig());
                }
                if (getExportColumns().get(6)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getPhilhealth());
                }
                if (getExportColumns().get(7)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getSss());
                }
                if (getExportColumns().get(8)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getGsis());
                }
                if (getExportColumns().get(9)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getMemDate());
                }
                if (getExportColumns().get(10)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getStatusId().getStatusName());
                }
                if (getExportColumns().get(11)) {
                    try {
                        String skl = (String) emf.createEntityManager().createQuery("SELECT c.skillsCode.skillsName FROM CoopSkillsAssoc c "
                                + "WHERE UPPER(c.skillsCode.skillsName) LIKE UPPER('%" + filterData.getSkill() + "%') "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(skl);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(12)) {
                    if (filterData.getCompensation() != null) {
                        row.createCell(x++).setCellValue(filterData.getCompensation());
                    } else {
                        try {
                            String cmpBrckt = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.compBracket FROM CoopEmplDtlAssoc c "
                                    + "WHERE c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList().get(0);
                            row.createCell(x++).setCellValue(cmpBrckt);
                        } catch (Exception e) {
                            row.createCell(x++).setCellValue("");
                        }
                    }
                }
                if (getExportColumns().get(13)) {
                    List<CoopEducInfo> educInfo = null;

                    //POST-GRADUATE
                    educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                            + "WHERE c.educInfoNum.schoolLevel ='POST-GRADUATE' "
                            + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                    for (int a = 0; a != educInfo.size(); a++) {
                        rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                        row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                    }

                    //COLLEGE
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='COLLEGE' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //VOCATIONAL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='VOCATIONAL' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //HIGH SCHOOL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='HIGH SCHOOL' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //ELEMENTARY
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='ELEMENTARY' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    if (educInfo.isEmpty()) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                x = 0;
            }

            FileOutputStream fileOut = new FileOutputStream(getFilename());
            workbook.write(fileOut);

            fileOut.close();
            System.out.println("Your excel file has been generated!");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Your excel file has been generated.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            System.out.println(ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while generating excel file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Boolean> getExportColumns() {
        if (exportColumns == null) {
            exportColumns = new ArrayList<>();
            for (int i = 0; i != boolSize; i++) {
                exportColumns.add(Boolean.FALSE);
            }
        }
        return exportColumns;
    }

    public void setExportColumns(List<Boolean> exportColumns) {
        this.exportColumns = exportColumns;
    }

    public DataConvert getdConvert() {
        if (dConvert == null) {
            dConvert = new DataConvert();
        }
        return dConvert;
    }

    public void setdConvert(DataConvert dConvert) {
        this.dConvert = dConvert;
    }

    public returnObject getrObjct() {
        if (rObjct == null) {
            rObjct = new returnObject();
        }
        return rObjct;
    }

    public void setrObjct(returnObject rObjct) {
        this.rObjct = rObjct;
    }

}
