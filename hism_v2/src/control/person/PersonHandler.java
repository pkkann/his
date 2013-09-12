/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.person;

import control.picture.PictureHandler;
import date.ADate;
import db.person.PersonDAO;
import file.FileTool;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

/**
 *
 * @author patrick
 */
public class PersonHandler {

    private PersonRegister personRegister;
    private PictureHandler pictureHandler;
    private PersonDAO personDAO;

    public PersonHandler(PersonRegister personRegister, PictureHandler pictureHandler, PersonDAO personDAO) {
        this.personDAO = personDAO;
        this.personRegister = personRegister;
        this.pictureHandler = pictureHandler;
    }
    
    public void populatePersonsFromDB() {
        try {
            ArrayList<Person> persons = personDAO.getAllPersons();
            if(!persons.isEmpty()) {
                personRegister.setPersons(persons);
            } else {
                System.out.println("empty");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Person getPerson(int id) {
        for(Person p : personRegister.getPersons()) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void createPerson(String firstname, String middlename, String lastname, String address, ADate birthdayDate, ADate expirationDate, File picturePath, ADate creationDate) {
        Person p = new Person(firstname, middlename, lastname, address, birthdayDate, expirationDate, picturePath, creationDate);
        personRegister.add(p);
        p.setId(personRegister.indexOf(p));
        File newPicturePath = new File(FileTool.getDefaultFolder()+"\\"+"personPicture"+pictureHandler.getpictureID()+".jpg");
        FileTool.copyFile(picturePath, newPicturePath);
        p.setPicturePath(newPicturePath);
        pictureHandler.insertPicture(newPicturePath);
        try {
            personDAO.insertPerson(p);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkExpirationDates(ADate date) {
        ArrayList<Person> persons = personRegister.getPersons();

        Calendar currentC = Calendar.getInstance();
        currentC.set(date.getYear(), date.getMonth(), date.getDay());

        for (Person p : persons) {
            Calendar personC = Calendar.getInstance();
            personC.set(p.getExpirationDate().getYear(), p.getExpirationDate().getMonth() - 1, p.getExpirationDate().getDay());
            
            if (currentC.after(personC)) {
                p.setExpired(true);
                System.out.println(p.getFirstname() + " er udløbet");
            }
        }
    }

    public ArrayList<Person> search(String s) {
        ArrayList<Person> result = new ArrayList<>();

        for (Person p : personRegister.getPersons()) {
            if (p.getFirstname().equalsIgnoreCase(s) || p.getMiddlename().equalsIgnoreCase(s) || p.getLastname().equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getLastname()).equalsIgnoreCase(s)) {
                result.add(p);
            } else if (p.getBirthdayDate().equals(s)) {
                result.add(p);
            }
        }
        return result;
    }
    
    public ArrayList<Person> getPersons() {
        return personRegister.getPersons();
    }
    
    public void setPersons(ArrayList<Person> persons) {
        personRegister.setPersons(persons);
    }
}
