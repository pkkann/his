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

    public Person() {
    }

    public Person(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
    }

    public Person(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, String picturePath) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.picturePath = picturePath;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    
    
}
