/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Quarantine;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class QuarantineRegister {
    
    private HashSet<Quarantine> quarantines = new HashSet(0);
    
    public QuarantineRegister() {
    }
    
    /**
     * Registers a quarantine
     * @param q 
     */
    public void registerQuarantine(Quarantine q) {
        System.out.println("Registering quarantine...");
        
        quarantines.add(q);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(q);
        q.setIdQuarantine((Integer)sz);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
    }
    
    /**
     * Saves a quarantine
     * @param q 
     */
    public void saveQuarantine(Quarantine q) {
        System.out.println("Saving quarantine...");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(q);
        
        tx.commit();
        s.close();
        System.out.println("Save complete!");
    }
    
    /**
     * Returns a quarantine
     * @param quarantineID
     * @return Quarantine
     */
    public Quarantine getQuarantine(int quarantineID) {
        System.out.println("Getting quarantine...");
        
        for(Quarantine q : quarantines) {
            if(q.getIdQuarantine() == quarantineID) {
                System.out.println("Found quarantine\nGet complete!");
                return q;
            }
        }
        System.out.println("No quarantine found\nGet complete!");
        return null;
    }
    
    /**
     * Deletes a quarantine
     * @param q 
     */
    public void deleteQuarantine(Quarantine q) {
        System.out.println("Deleting quarantine...");
        
        quarantines.remove(q);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(q);
        
        tx.commit();
        s.close();
        System.out.println("Deletion complete!");
    }

    /**
     * Returns all quarantines
     * @return HashSet<Quarantine>
     */
    public HashSet<Quarantine> getQuarantines() {
        System.out.println("Getting all quarantines...");
        System.out.println("Get complete!");
        return quarantines;
    }

    /**
     * Sets all quarantines
     * @param quarantines 
     */
    public void setQuarantines(HashSet<Quarantine> quarantines) {
        System.out.println("Setting all quarantines...");
        System.out.println("Set complete!");
        this.quarantines = quarantines;
    }
    
    
    
}
