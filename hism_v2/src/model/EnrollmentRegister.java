/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Enrollment;
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
        return enrollments;
    }

    /**
     * Sets all enrollments
     * @param enrollments 
     */
    public void setEnrollments(HashSet<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
}
