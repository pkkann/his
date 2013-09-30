/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashSet;

/**
 *
 * @author patrick
 */
public class EnrollmentRegister {
    
    private HashSet<Enrollment> enrollments = new HashSet(0);
    
    public EnrollmentRegister() {
    }

    public HashSet<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(HashSet<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
    
    
}
