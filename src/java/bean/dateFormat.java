/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class dateFormat implements Serializable {

    //date format and compare
    private boolean clearEnd;

    public dateFormat() {

    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public Date addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public Date parseStringDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateInString = date;
        return formatter.parse(dateInString);
    }

    public String formatDate(Date currentDate, String format) {
        if (currentDate == null) {
            currentDate = new Date();
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(currentDate);
    }

    public static Integer getMaxDateOfMonth(int month, int year) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(year, month, 2);
        int maxDay = dateCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    public Integer getMaxDateOfYear(int month, int year) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(year, month, 2);
        int maxDay = dateCal.getActualMaximum(Calendar.DAY_OF_YEAR);
        return maxDay;
    }

    public Integer dateDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public Integer dateMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public Integer dateYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public void compareDate(Date from, Date to) {
        if (from.after(to)) {
            setClearEnd(true);
        }
        setClearEnd(false);
    }

    public Integer returnDiffDays(Date from, Date to) {
        if (from == null || to == null) {
            return null;
        } else {
            long diffDays = (to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000);
            return (int) (long) diffDays;
        }
    }

    public boolean isClearEnd() {
        return clearEnd;
    }

    public void setClearEnd(boolean clearEnd) {
        this.clearEnd = clearEnd;
    }

}
