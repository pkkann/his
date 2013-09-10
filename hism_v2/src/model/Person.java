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
    private String name;
    private String address;
    private String birthdayDate;
    private String expirationDate;
    private File picturePath;
    private boolean quarantine;
    private String quarantineExpirationDate;

    public Person(String name, String address, String birthdayDate, String expirationDate, File picturePath) {
        this.name = name;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.picturePath = picturePath;
        quarantine = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
    
    
}
