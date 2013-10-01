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
     * Returns all quarantines
     * @return HashSet<Quarantine>
     */
    public HashSet<Quarantine> getQuarantines() {
        return quarantines;
    }

    /**
     * Sets all quarantines
     * @param quarantines 
     */
    public void setQuarantines(HashSet<Quarantine> quarantines) {
        this.quarantines = quarantines;
    }
    
    
    
}
