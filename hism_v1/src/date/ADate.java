/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.util.Calendar;

/**
 *
 * @author patrick
 */
public class ADate {

    private int day;
    private int month;
    private int year;

    public ADate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public ADate(String date) {
        this.day = Integer.valueOf(date.substring(0, 2));
        this.month = Integer.valueOf(date.substring(2, 4));
        this.year = Integer.valueOf(date.substring(4, 8));
    }
    
    public ADate() {
        Calendar c = Calendar.getInstance();
        this.day = c.get(Calendar.DATE);
        this.month = (c.get(Calendar.MONTH) + 1);
        this.year = c.get(Calendar.YEAR);
        c = null;
    }
    
    public static String formatADate(ADate date, String seperator) {
        String d = String.valueOf(date.getDay());
        String m = String.valueOf(date.getMonth());
        String y = String.valueOf(date.getYear());
        
        if(d.length() != 2) {
            d = "0" + d;
        }
        if(m.length() != 2) {
            m = "0" + m;
        }
        
        String re = d + seperator + m + seperator + y;
        
        return re;
    }

    public int getDay() {
        return day;
    }
    
    public String getDayAsString() {
        String d = String.valueOf(day);
        if(d.length() != 2) {
            d = "0" + d;
        }
        return d;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }
    
    public String getMonthAsString() {
        String m = String.valueOf(month);
        if(m.length() != 2) {
            m = "0" + m;
        }
        return m;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String toString() {
        
        if(String.valueOf(month).length() != 2) {
            return day +  "0" + month +  year;
        } else {
            return "" + day + month + year;
        }
        
    }
}
