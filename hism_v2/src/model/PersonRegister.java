/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Person;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class PersonRegister {
    
    private HashSet<Person> persons = new HashSet(0);
    
    public PersonRegister() {
    }
    
    /**
     * Registers a person
     * @param p 
     */
    public void registerPerson(Person p) {
        System.out.println("Registering person...");
        
        persons.add(p);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(p);
        p.setIdPerson((Integer)sz);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
    }
    
    /**
     * Returns a person
     * @param idPerson
     * @return Person
     */
    public Person getPerson(int idPerson) {
        for(Person p : persons) {
            if(p.getIdPerson() == idPerson) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns all persons
     * @return HashSet<Person>
     */
    public HashSet<Person> getPersons() {
        return persons;
    }

    /**
     * Sets all persons
     * @param persons 
     */
    public void setPersons(HashSet<Person> persons) {
        this.persons = persons;
    }
    
}
