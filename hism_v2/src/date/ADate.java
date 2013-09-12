/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

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
    
    public static String formatADate(ADate d, String seperator) {
        if(String.valueOf(d.getMonth()).length() != 2) {
            return d.getDay() + seperator + "0" + d.getMonth() + seperator + d.getYear();
        } else {
            return d.getDay() + seperator + d.getMonth() + seperator + d.getYear();
        }
        
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
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
