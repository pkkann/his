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
public class User {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String middlename;
    private String lastname;
    private ADate creationDate;
    private boolean administrator;
    private boolean reserve;

    public User(String email, String password, String firstname, String middlename, String lastname, ADate creationDate, boolean administrator, boolean reserve) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.creationDate = creationDate;
        this.administrator = administrator;
        this.reserve = reserve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ADate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ADate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
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
    
    
}
