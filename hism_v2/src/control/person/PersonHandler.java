/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.person;

import java.io.File;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author patrick
 */
public class PersonHandler {

    private PersonRegister personRegister;

    public PersonHandler(PersonRegister personRegister) {
        this.personRegister = personRegister;
    }
    
    public void createPerson(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, File picturePath, String creationDate) {
        Person p = new Person(firstname, middlename, lastname, address, birthdayDate, expirationDate, picturePath, creationDate);
        personRegister.add(p);
        p.setId(personRegister.indexOf(p));
    }
    
    public void checkExpirationDates(String date, String month, String year) {
        
    }
    
    public ArrayList<Person> search(String s) {
        ArrayList<Person> result = new ArrayList<>();
        
        for(Person p : personRegister.getPersons()) {
            if(p.getFirstname().equalsIgnoreCase(s) || p.getMiddlename().equalsIgnoreCase(s) || p.getLastname().equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getLastname()).equalsIgnoreCase(s)) {
                result.add(p);
            } else if(p.getBirthdayDate().equals(s)) {
                result.add(p);
            }
        }
        return result;
    }
}
