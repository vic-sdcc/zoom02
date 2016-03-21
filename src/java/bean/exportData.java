/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopAssociate;
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

    public void memberList(List<CoopMember> memberData, String name) {
        System.out.println("filename: " + name);
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }

        try {
            setFilename("/home/vic/membership/" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Member No.");
            rowhead.createCell(1).setCellValue("Account No.");
            rowhead.createCell(2).setCellValue("Name");
            rowhead.createCell(3).setCellValue("Email");

            for (int i = 0; i != memberData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(0).setCellValue(memberData.get(i).getMemNo());
                row.createCell(1).setCellValue(memberData.get(i).getScAcctno());
                row.createCell(2).setCellValue(memberData.get(i).getLastName() + ", " + memberData.get(i).getFirstName() + " " + memberData.get(i).getMiddleName() + "." + " ");
                row.createCell(3).setCellValue(memberData.get(i).getEmail());
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

    public void associateList(List<CoopAssociate> associateData, String name) {
        System.out.println("filename: " + name);
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }
        try {
            setFilename("/home/vic/membership/" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Associate No.");
            rowhead.createCell(1).setCellValue("Account No.");
            rowhead.createCell(2).setCellValue("Name");
            rowhead.createCell(3).setCellValue("Email");

            for (int i = 0; i != associateData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(0).setCellValue(associateData.get(i).getAssociateNo());
                row.createCell(1).setCellValue(associateData.get(i).getScAcctno());
                row.createCell(2).setCellValue(associateData.get(i).getLastName() + ", " + associateData.get(i).getFirstName() + " " + associateData.get(i).getMiddleName() + "." + " ");
                row.createCell(3).setCellValue(associateData.get(i).getEmail());
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

}
