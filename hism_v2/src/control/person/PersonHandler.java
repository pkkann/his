/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.person;

import java.io.File;
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
}
