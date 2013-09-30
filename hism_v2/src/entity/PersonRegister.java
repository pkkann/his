/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashSet;

/**
 *
 * @author patrick
 */
public class PersonRegister {
    
    private HashSet<Person> persons = new HashSet(0);
    
    public PersonRegister() {
    }

    public HashSet<Person> getPersons() {
        return persons;
    }

    public void setPersons(HashSet<Person> persons) {
        this.persons = persons;
    }
    
    
    
}
