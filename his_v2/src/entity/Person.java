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

    @Column(name = "hoene")
    private boolean hoene;

    @Column(name = "reserve")
    private boolean reserve;

    @Column(name = "oneone")
    private boolean oneOne;

    public Person() {
    }

    public Person(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, boolean oneOne) {
        setOneOne(oneOne);
        setFirstname(firstname);
        setMiddlename(middlename);
        setLastname(lastname);
        setAddress(address);
        setBirthdayDate(birthdayDate);
        setExpirationDate(expirationDate);
        setCreationDate(creationDate);
    }

    public Person(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, boolean oneOne, String picturePath) {
        setHoene(hoene);
        setReserve(reserve);
        setOneOne(oneOne);
        setFirstname(firstname);
        setMiddlename(middlename);
        setLastname(lastname);
        setAddress(address);
        setBirthdayDate(birthdayDate);
        setExpirationDate(expirationDate);
        setCreationDate(creationDate);
        setPicturePath(picturePath);
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
        if (firstname == null || firstname.isEmpty()) {
            throw new IllegalArgumentException("Firstname can not be null or empty");
        } else {
            this.firstname = firstname;
        }
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        if (middlename == null) {
            throw new IllegalArgumentException("Middlename can not be null");
        } else {
            this.middlename = middlename;
        }
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.isEmpty()) {
            throw new IllegalArgumentException("Lastname can not be null or empty");
        } else {
            this.lastname = lastname;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address can not be null or empty");
        } else {
            this.address = address;
        }
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        if (birthdayDate == null || birthdayDate.isEmpty()) {
            throw new IllegalArgumentException("BirthdayDate can not be null or empty");
        } else {
            String[] split = birthdayDate.split("/");
            if (split.length != 3) {
                throw new IllegalArgumentException("BirthdayDate is in the wrong format. Should be dd/mm/yyyy");
            } else {
                if (split[0].length() != 2 || split[1].length() != 2 || split[2].length() != 4) {
                    throw new IllegalArgumentException("BirthdayDate is in the wrong format. Should be dd/mm/yyyy");
                } else {
                    this.birthdayDate = birthdayDate;
                }
            }
        }
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (!this.hoene && !this.oneOne && !this.reserve) {
            if (expirationDate == null || expirationDate.isEmpty()) {
                throw new IllegalArgumentException("ExpirationDate can not be null or empty");
            } else {
                String[] split = expirationDate.split("/");
                if (split.length != 2) {
                    throw new IllegalArgumentException("ExpirationDate is in the wrong format. Should be mm/yyyy");
                } else {
                    if (split[0].length() != 2 || split[1].length() != 4) {
                        throw new IllegalArgumentException("ExpirationDate is in the wrong format. Should be mm/yyyy");
                    } else {
                        this.expirationDate = expirationDate;
                    }
                }
            }
        } else {
            this.expirationDate = expirationDate;
        }
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        if (creationDate == null || creationDate.isEmpty()) {
            throw new IllegalArgumentException("CreationDate can not be null or empty");
        } else {
            String[] split = creationDate.split("/");
            if (split.length != 3) {
                throw new IllegalArgumentException("CreationDate is in the wrong format. Should be dd/mm/yyyy");
            } else {
                if (split[0].length() != 2 || split[1].length() != 2 || split[2].length() != 4) {
                    throw new IllegalArgumentException("CreationDate is in the wrong format. Should be dd/mm/yyyy");
                } else {
                    this.creationDate = creationDate;
                }
            }
        }
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        if (picturePath == null || picturePath.isEmpty()) {
            throw new IllegalArgumentException("PicturePath can not be null or empty");
        } else {
            this.picturePath = picturePath;
        }
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

}
