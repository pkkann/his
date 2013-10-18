/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import date.ADate;

/**
 *
 * @author patrick
 */
public class Guest {
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private ADate birthday;
    private ADate creationDate;

    public Guest(String firstname, String middlename, String lastname, ADate birthday, ADate creationDate) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.birthday = birthday;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ADate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ADate creationDate) {
        this.creationDate = creationDate;
    }

    public ADate getBirthday() {
        return birthday;
    }

    public void setBirthday(ADate birthday) {
        this.birthday = birthday;
    }
    
}
