/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class exportData {

    /**
     * Creates a new instance of exportData
     */
    public exportData() {
    }

    public void memberList(List<CoopMember> memberData) {
        try {
            String filename = "Member Data List(" + new Date() + ").xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Member No.");
            rowhead.createCell(1).setCellValue("Account No.");
            rowhead.createCell(2).setCellValue("Name");
            rowhead.createCell(3).setCellValue("Email");

            for (int i = 1; i != memberData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(memberData.get(i).getMemNo());
                row.createCell(1).setCellValue(memberData.get(i).getScAcctno());
                row.createCell(2).setCellValue(memberData.get(i).getLastName() + ", " + memberData.get(i).getFirstName() + " " + memberData.get(i).getMiddleName() + "." + " ");
                row.createCell(3).setCellValue(memberData.get(i).getEmail());
            }
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            
            
            fileOut.close();
            System.out.println("Your excel file has been generated!");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void associateList(List<CoopAssociate> associateData) {
        try {
            String filename = "Associate Data List(" + new Date() + ").xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Associate No.");
            rowhead.createCell(1).setCellValue("Account No.");
            rowhead.createCell(2).setCellValue("Name");
            rowhead.createCell(3).setCellValue("Email");

            for (int i = 1; i != associateData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i);
                row.createCell(0).setCellValue(associateData.get(i).getAssociateNo());
                row.createCell(1).setCellValue(associateData.get(i).getScAcctno());
                row.createCell(2).setCellValue(associateData.get(i).getLastName() + ", " + associateData.get(i).getFirstName() + " " + associateData.get(i).getMiddleName() + "." + " ");
                row.createCell(3).setCellValue(associateData.get(i).getEmail());
            }
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            
            
            fileOut.close();
            System.out.println("Your excel file has been generated!");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
