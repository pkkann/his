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
@Table(name = "user")
public class User implements Serializable {
    
    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iduser;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "middlename")
    private String middlename;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "creationdate")
    private String creationDate;
    
    @Column(name = "reserve")
    private boolean reserve;
    
    @Column(name = "administrator")
    private boolean administrator;

    public User() {
    }

    public User(String username, String password, String firstname, String middlename, String lastname, String creationDate, boolean reserve, boolean administrator) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.creationDate = creationDate;
        this.reserve = reserve;
        this.administrator = administrator;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    
    
    
}
