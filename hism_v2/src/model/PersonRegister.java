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
     * Saves a person
     * @param p 
     */
    public void savePerson(Person p) {
        System.out.println("Saving person...");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(p);
        
        tx.commit();
        s.close();
        System.out.println("Save complete!");
    }
    
    /**
     * Returns a person
     * @param idPerson
     * @return Person
     */
    public Person getPerson(int idPerson) {
        System.out.println("Getting person...");
        for(Person p : persons) {
            if(p.getIdPerson() == idPerson) {
                System.out.println("Found person\nGet complete!");
                return p;
            }
        }
        System.out.println("No person found\nGet complete!");
        return null;
    }
    
    /**
     * Deletes a person
     * @param p 
     */
    public void deletePerson(Person p) {
        System.out.println("Deleting person...");
        
        persons.remove(p);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(p);
        
        tx.commit();
        s.close();
        System.out.println("Deletion complete!");
    }

    /**
     * Returns all persons
     * @return HashSet<Person>
     */
    public HashSet<Person> getPersons() {
        System.out.println("Getting all persons...");
        System.out.println("Get complete!");
        return persons;
    }

    /**
     * Sets all persons
     * @param persons 
     */
    public void setPersons(HashSet<Person> persons) {
        System.out.println("Setting all persons...");
        this.persons = persons;
        System.out.println("Set complete!");
    }
    
}
