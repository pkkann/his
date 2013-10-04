/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Enrollment;
import entity.Guest;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author patrick
 */
public class EnrollmentRegister {
    
    private HashSet<Enrollment> enrollments = new HashSet(0);
    
    public EnrollmentRegister() {
    }
    
    /**
     * Registers an enrollment
     * @param en 
     */
    public void registerEnrollment(Enrollment en) {
        System.out.println("Registering enrollment...");
        
        enrollments.add(en);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(en);
        en.setIdEnrollment((Integer)sz);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
    }
    
    /**
     * Registers a guest
     * @param en
     * @param g 
     */
    public void registerGuest(Enrollment en, Guest g) {
        System.out.println("Registering guest...");
        
        en.getGuests().add(g);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(g);
        g.setIdGuest((Integer)sz);
        
        s.saveOrUpdate(en);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
    }
    
    /**
     * Deletes a guest
     * @param en
     * @param g 
     */
    public void DeleteGuest(Enrollment en, Guest g) {
        System.out.println("Deleting guest...");
        en.getGuests().remove(g);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(en);
        
        tx.commit();
        s.close();
        System.out.println("Delete complete!");
    }
    
    /**
     * Get a guest
     * @param en
     * @param guestID
     * @return Guest
     */
    public Guest getGuest(Enrollment en, int guestID) {
        System.out.println("Getting guest...");
        for(Guest gu : en.getGuests()) {
            if(gu.getIdGuest() == guestID) {
                System.out.println("Guest found\nGet complete!");
                return gu;
            }
        }
        System.out.println("Guest not found\nGet complete!");
        return null;
    }
    
    /**
     * Returns an enrollment
     * @param idEnrollment
     * @return Enrollment
     */
    public Enrollment getEnrollment(int idEnrollment) {
        for(Enrollment en : enrollments) {
            if(en.getIdEnrollment() == idEnrollment) {
                return en;
            }
        }
        return null;
    }
    
    /**
     * Deletes an enrollment
     * @param en 
     */
    public void deleteEnrollment(Enrollment en) {
        System.out.println("Deleting enrollment...");
        
        enrollments.remove(en);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(en);
        
        tx.commit();
        s.close();
        System.out.println("Deletion complete!");
    }
    
    /**
     * Saves an enrollment
     * @param en 
     */
    public void saveEnrollment(Enrollment en) {
        System.out.println("Saving enrollment...");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(en);
        
        tx.commit();
        s.close();
        System.out.println("Save complete!");
    }

    /**
     * Returns all enrollments
     * @return HashSet<Enrollment>
     */
    public HashSet<Enrollment> getEnrollments() {
        System.out.println("Getting all enrollments...");
        System.out.println("Get complete!");
        return enrollments;
    }

    /**
     * Sets all enrollments
     * @param enrollments 
     */
    public void setEnrollments(HashSet<Enrollment> enrollments) {
        System.out.println("Setting all enrollments...");
        System.out.println("Set complete!");
        this.enrollments = enrollments;
    }
    
}
