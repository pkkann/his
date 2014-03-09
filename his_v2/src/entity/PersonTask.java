/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Patrick
 */
@Entity
@Table(name = "persontask")
public class PersonTask implements Serializable {

    @Id
    @Column(name = "idpersontask")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPersonTask;

    @Column(name = "tasktext")
    private String taskText;

    @Column(name = "finished")
    private boolean finished;

    @OneToOne
    @JoinColumn(name = "idperson")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Person person;
    
    @OneToOne
    @JoinColumn(name = "iduser")
    @Cascade(CascadeType.SAVE_UPDATE)
    private User author;
    
    public PersonTask(){}

    public PersonTask(String taskText, Person person, User author) {
        this.taskText = taskText;
        this.finished = false;
        this.person = person;
        this.author = author;
    }

    public int getIdPersonTask() {
        return idPersonTask;
    }

    public void setIdPersonTask(int idPersonTask) {
        this.idPersonTask = idPersonTask;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
