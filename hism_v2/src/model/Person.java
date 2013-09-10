/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;

/**
 *
 * @author patrick
 */
public class Person {

    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String address;
    private String birthdayDate;
    private String expirationDate;
    private File picturePath;
    private boolean quarantine;
    private String quarantineExpirationDate;
    private boolean oneOne;
    private String creationDate;

    public Person(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, File picturePath, String creationDate) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.picturePath = picturePath;
        this.creationDate = creationDate;
        quarantine = false;
        oneOne = false;
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

    public File getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(File picturePath) {
        this.picturePath = picturePath;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isQuarantine() {
        return quarantine;
    }

    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
    }

    public String getQuarantineExpirationDate() {
        return quarantineExpirationDate;
    }

    public void setQuarantineExpirationDate(String quarantineExpirationDate) {
        this.quarantineExpirationDate = quarantineExpirationDate;
    }

    public boolean isOneOne() {
        return oneOne;
    }

    public void setOneOne(boolean oneOne) {
        this.oneOne = oneOne;
    }
}
