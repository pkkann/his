/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author patrick
 */
@Entity
@Table(name = "enrollment")
public class Enrollment implements Serializable {
    
    @Id
    @Column(name = "idenrollment")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEnrollment;
    
    @OneToOne
    @JoinColumn(name = "idperson")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Person enrolledPerson;
    
    @OneToOne
    @JoinColumn(name = "iduser")
    @Cascade(CascadeType.SAVE_UPDATE)
    private User enrolledByUser;
    
    @OneToMany
    @Column(name = "guests")
    private Set<Guest> guests = new HashSet(0);

    public Enrollment() {
    }

    public Enrollment(Person enrolledPerson, User enrolledByUser) {
        this.enrolledPerson = enrolledPerson;
        this.enrolledByUser = enrolledByUser;
    }

    public int getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(int idEnrollment) {
        this.idEnrollment = idEnrollment;
    }

    public Person getEnrolledPerson() {
        return enrolledPerson;
    }

    public void setEnrolledPerson(Person enrolledPerson) {
        this.enrolledPerson = enrolledPerson;
    }

    public User getEnrolledByUser() {
        return enrolledByUser;
    }

    public void setEnrolledByUser(User enrolledByUser) {
        this.enrolledByUser = enrolledByUser;
    }

    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }
    
    
}
