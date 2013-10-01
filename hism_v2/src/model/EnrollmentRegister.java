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
