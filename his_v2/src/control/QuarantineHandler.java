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
public class QuarantineHandler implements HismHandler{
    
    private QuarantineRegister quR;
    private PersonRegister peR;
    
    public QuarantineHandler(QuarantineRegister qaR, PersonRegister peR) {
        this.quR = qaR;
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
                    return QUARANTINE_FORMAT_ERROR;
                }
                
                // Create quarantine
                q = new Quarantine(quarantineExpireDate, p);
                
                // Register quarantine
                quR.registerQuarantine(q);
            } else {
                // Create quarantine
                q = new Quarantine("", p);
                
                // Register quarantine
                quR.registerQuarantine(q);
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
        Quarantine q = quR.getQuarantine(quarantineID);
        
        if(q != null) {
            // Check expireDate is there
            if(!quarantineExpireDate.isEmpty()) {
                // Check expireDate is valid
                String[] quarantineExpireDate_Split = quarantineExpireDate.split("/");
                String quaran_Month = quarantineExpireDate_Split[0];
                String quaran_Year = quarantineExpireDate_Split[1];
                
                if(quaran_Month.length() != 2 || quaran_Year.length() != 4) {
                    return QUARANTINE_FORMAT_ERROR;
                }
                
                // Set quarantine
                q.setQuarantineExpireDate(quarantineExpireDate);
                
                // Save quarantine
                quR.saveQuarantine(q);
            } else {
                // Save quarantine (Nothing is changed... xD)
                quR.saveQuarantine(q);
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
        Quarantine q = quR.getQuarantine(quarantineID);
        
        if(q != null) {
            // Remove quarantine
            quR.deleteQuarantine(q);
        } else {
            return GET_ERROR;
        }
        
        return NO_ERROR;
    }
    
    /**
     * Return a quarantine
     * @param idPerson
     * @return qa : Quarantine
     */
    public Quarantine getQuarantine(int idPerson) {
        return quR.getQuarantine(idPerson);
    }
    
    /**
     * Returns wether a person is quarantined or not
     * @param idPerson
     * @return boolean
     */
    public boolean isQuarantined(int idPerson) {
        Quarantine q = quR.getQuarantine(idPerson);
        if(q == null) {
            return false;
        } else {
            return true;
        }
    }
    
}
