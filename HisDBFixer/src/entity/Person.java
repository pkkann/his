/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author patrick
 */
public class Person implements Serializable {

    private int idPerson;

    private String firstname;

    private String middlename;

    private String lastname;

    private String address;

    private String birthdayDate;

    private String expirationDate;

    private String creationDate;

    private String picturePath;
    
    private String phone;
    
    private String email;

    private boolean hoene;

    private boolean reserve;

    private boolean oneOne;
    
    private boolean expired;

    public Person() {
    }

    public Person(String firstname, String middlename, String lastname, String address, String email, String phone, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, boolean oneOne) {
        this.oneOne = oneOne;
        this.reserve = reserve;
        this.hoene = hoene;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.expired = false;
    }

    public Person(String firstname, String middlename, String lastname, String address, String email, String phone, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, boolean oneOne, String picturePath) {
        this.oneOne = oneOne;
        this.reserve = reserve;
        this.hoene = hoene;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.picturePath = picturePath;
        this.expired = false;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isHoene() {
        return hoene;
    }

    public void setHoene(boolean hoene) {
        this.hoene = hoene;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public boolean isOneOne() {
        return oneOne;
    }

    public void setOneOne(boolean oneOne) {
        this.oneOne = oneOne;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    
    @Override
    public String toString() {
        return firstname + " " + middlename + " " + lastname;
    }

}
