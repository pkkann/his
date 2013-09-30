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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author patrick
 */
@Entity
@Table(name = "quarantine")
public class Quarantine implements Serializable {

    @Id
    @Column(name = "idQuarantine")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idQuarantine;
    
    @Column(name = "quarantineexpiredate")
    private String quarantineExpireDate;
    
    @OneToOne
    @JoinColumn(name = "idperson")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Person person;

    public Quarantine() {
    }

    public Quarantine(Person person) {
        this.person = person;
    }

    public Quarantine(String quarantineExpireDate, Person person) {
        this.quarantineExpireDate = quarantineExpireDate;
        this.person = person;
    }

    public int getIdQuarantine() {
        return idQuarantine;
    }

    public void setIdQuarantine(int idQuarantine) {
        this.idQuarantine = idQuarantine;
    }

    public String getQuarantineExpireDate() {
        return quarantineExpireDate;
    }

    public void setQuarantineExpireDate(String quarantineExpireDate) {
        this.quarantineExpireDate = quarantineExpireDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
