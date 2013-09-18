/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import date.ADate;
import java.io.File;
import java.util.ArrayList;

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
    private ADate birthdayDate;
    private ADate expirationDate;
    private boolean expired;
    private File picturePath;
    private boolean quarantine;
    private ADate quarantineExpirationDate;
    private boolean oneOne;
    private ADate creationDate;
    private boolean enrolled;
    private boolean hone;
    private ArrayList<Guest> guests;

    public Person(String firstname, String middlename, String lastname, String address, ADate birthdayDate, ADate expirationDate, File picturePath, ADate creationDate) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.picturePath = picturePath;
        this.creationDate = creationDate;
        this.quarantine = false;
        this.oneOne = false;
        this.expired = false;
        this.enrolled = false;
        this.hone = false;
        guests = new ArrayList<>();
    }
    
    public Person(String firstname, String middlename, String lastname, String address, ADate birthdayDate, ADate expirationDate, File picturePath, ADate creationDate, boolean oneOne) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.picturePath = picturePath;
        this.creationDate = creationDate;
        this.quarantine = false;
        this.oneOne = oneOne;
        this.expired = false;
        this.enrolled = false;
        this.hone = false;
        guests = new ArrayList<>();
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

    public ADate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(ADate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public ADate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ADate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public File getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(File picturePath) {
        this.picturePath = picturePath;
    }

    public ADate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ADate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isQuarantine() {
        return quarantine;
    }

    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
    }

    public ADate getQuarantineExpirationDate() {
        return quarantineExpirationDate;
    }

    public void setQuarantineExpirationDate(ADate quarantineExpirationDate) {
        this.quarantineExpirationDate = quarantineExpirationDate;
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

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isHone() {
        return hone;
    }

    public void setHone(boolean hone) {
        this.hone = hone;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    public int guestsSize() {
        return guests.size();
    }

    public Guest getGuest(int index) {
        return guests.get(index);
    }
    
    public Guest getGuestByID(int id) {
        for(Guest g : guests) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public boolean addGuest(Guest e) {
        return guests.add(e);
    }

    public void addGuest(int index, Guest element) {
        guests.add(index, element);
    }

    public Guest removeGuest(int index) {
        return guests.remove(index);
    }

    public boolean removeGuest(Object o) {
        return guests.remove(o);
    }
    
}
