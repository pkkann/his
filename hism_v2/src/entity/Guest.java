/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author patrick
 */
@Entity
@Table(name = "guest")
public class Guest implements Serializable {
    
    @Id
    @Column(name = "idguest")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idGuest;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "middlename")
    private String middlename;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "birthdaydate")
    private String birthdayDate;
    
    @Column(name = "creationdate")
    private String creationDate;
    
}
