package model;

import java.io.File;

/**
 *
 * @author pkann
 */
public class Person {

    private int id;
    private String name;
    private String address;
    private String birthdayDate;
    private String expirationDate;
    private File picturePath;
    private boolean hone;
    private String username;
    private String password;
    private boolean admin;
    private boolean reserve;
    private boolean quarantine;

    public Person(String name, String address, String birthdayDate, String expirationDate, File picturePath) {
        this.name = name;
        this.address = address;
        this.birthdayDate = birthdayDate;
        this.expirationDate = expirationDate;
        this.picturePath = picturePath;
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

    public File getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(File picturePath) {
        this.picturePath = picturePath;
    }

    public boolean isHone() {
        return hone;
    }

    public void setHone(boolean hone) {
        this.hone = hone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isQuarantine() {
        return quarantine;
    }

    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
}
