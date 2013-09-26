/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import java.util.ArrayList;
import model.PersonRegister;

/**
 *
 * @author patrick
 */
public class PersonHandler {
    
    private PersonRegister perReg;
    
    public PersonHandler(PersonRegister perReg) {
        this.perReg = perReg;
    }
    
    public void createPerson(String firstname, String middlename, String lastname, String address, String birthday, String expirationdate, String picturepath, String quarantineexpirationdate, String creationdate, Integer oneone, Integer quarantine, Integer enrolled, String enrolleddate, Integer hone) {
        Person p = new Person(firstname, middlename, lastname, address, birthday, expirationdate, picturepath, quarantineexpirationdate, creationdate, oneone, quarantine, enrolled, enrolleddate, hone);
        perReg.RegisterPerson(p);
    }
    
    public void savePerson(int personid, String firstname, String middlename, String lastname, String address, String birthday, String expirationdate, String picturepath, String quarantineexpirationdate, Integer oneone, Integer quarantine, Integer enrolled, String enrolleddate, Integer hone) {
        Person p = perReg.getPerson(personid);
        p.setFirstname(firstname);
        p.setMiddlename(middlename);
        p.setLastname(lastname);
        p.setAddress(address);
        p.setBirthday(birthday);
        p.setExpirationdate(expirationdate);
        p.setPicturepath(picturepath);
        p.setQuarantineexpirationdate(quarantineexpirationdate);
        p.setOneone(oneone);
        p.setQuarantine(quarantine);
        p.setEnrolled(enrolled);
        p.setEnrolleddate(enrolleddate);
        p.setHone(hone);
        
        perReg.SavePerson(p);
    }
    
    public Person getPerson(int personid) {
        return perReg.getPerson(personid);
    }
    
    public void deletePerson(int personid) {
        Person p = perReg.getPerson(personid);
        perReg.RemovePerson(p);
    }
    
    public void loadPersonsFromDBToArrays() {
        ArrayList<Person> persons = perReg.getAllPersonsFromDB();
        perReg.setPersons(persons);
    }
    
}
