/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import entity.Quarantine;
import model.PersonRegister;
import model.QuarantineRegister;

/**
 *
 * @author patrick
 */
public class QuarantineHandler {
    
    private QuarantineRegister qaR;
    private PersonRegister peR;
    public static final int NO_ERROR = 0;
    public static final int GET_ERROR = 1;
    public static final int FORMAT_ERROR = 2;
    
    public QuarantineHandler(QuarantineRegister qaR, PersonRegister peR) {
        this.qaR = qaR;
        this.peR = peR;
    }
    
    /**
     * Create a quarantine
     * @param personID
     * @param quarantineExpireDate
     * @return Error code : Integer
     */
    public int createQuarantine(int personID, String quarantineExpireDate) {
        
        // Get person
        Person p = peR.getPerson(personID);
        
        Quarantine q;
        
        if(p != null) {
            // Check expireDate is there
            if(!quarantineExpireDate.isEmpty()) {
                // Check expireDate is valid
                String[] quarantineExpireDate_Split = quarantineExpireDate.split("/");
                String quaran_Month = quarantineExpireDate_Split[0];
                String quaran_Year = quarantineExpireDate_Split[1];
                
                if(quaran_Month.length() != 2 || quaran_Year.length() != 4) {
                    return FORMAT_ERROR;
                }
                
                // Create quarantine
                q = new Quarantine(quarantineExpireDate, p);
                
                // Register quarantine
                qaR.registerQuarantine(q);
            } else {
                // Create quarantine
                q = new Quarantine(p);
                
                // Register quarantine
                qaR.registerQuarantine(q);
            }
        } else {
            return GET_ERROR;
        }
        
        return NO_ERROR;
    }
    
    /**
     * Save a quarantine
     * @param quarantineID
     * @param quarantineExpireDate
     * @return Error code : Integer
     */
    public int saveQuarantine(int quarantineID, String quarantineExpireDate) {
        
        // Get quarantine
        Quarantine q = qaR.getQuarantine(quarantineID);
        
        if(q != null) {
            // Check expireDate is there
            if(!quarantineExpireDate.isEmpty()) {
                // Check expireDate is valid
                String[] quarantineExpireDate_Split = quarantineExpireDate.split("/");
                String quaran_Month = quarantineExpireDate_Split[0];
                String quaran_Year = quarantineExpireDate_Split[1];
                
                if(quaran_Month.length() != 2 || quaran_Year.length() != 4) {
                    return FORMAT_ERROR;
                }
                
                // Set quarantine
                q.setQuarantineExpireDate(quarantineExpireDate);
                
                // Save quarantine
                qaR.saveQuarantine(q);
            } else {
                // Save quarantine (Nothing is changed... xD)
                qaR.saveQuarantine(q);
            }
        } else {
            return GET_ERROR;
        }
        
        return NO_ERROR;
    }
    
    /**
     * Remove a quarantine
     * @param quarantineID
     * @return Error code : Integer
     */
    public int removeQuarantine(int quarantineID) {
        
        // Get quarantine
        Quarantine q = qaR.getQuarantine(quarantineID);
        
        if(q != null) {
            // Remove quarantine
            qaR.deleteQuarantine(q);
        } else {
            return GET_ERROR;
        }
        
        return NO_ERROR;
    }
    
}
