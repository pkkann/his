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
@Table(name = "person")
public class Person implements Serializable {
    
    @Id
    @Column(name = "idperson")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPerson;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "middlename")
    private String middlename;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "birthdaydate")
    private String birthdayDate;
    
    @Column(name = "expirationdate")
    private String expirationDate;
    
    @Column(name = "creationdate")
    private String creationDate;
    
    @Column(name = "picturepath")
    private String picturePath;
    
}
