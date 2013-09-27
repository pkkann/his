/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Guest;
import entity.Person;
import hibernate.HiberUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class PersonRegister {
    
    private ArrayList<Person> persons;
    
    public PersonRegister() {
        persons = new ArrayList<>();
    }
    
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
    
    public ArrayList<Person> getPersons() {
        return persons;
    }
    
    public int RegisterPerson(Person p) {
        persons.add(p);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        int returnID = (Integer)s.save(p);
        
        tx.commit();
        s.close();
        
        return returnID;
    }
    
    public void RemovePerson (Person p) {
        persons.remove(p);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(p);
        
        tx.commit();
        s.close();
    }
    
    public void SavePerson (Person p) {
        persons.set(persons.indexOf(p), p);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(p);
        
        tx.commit();
        s.close();
    }
    
    public Person getPerson (int id) {
        for(Person p : persons) {
            if(p.getIdperson() == id) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Person> getAllPersonsFromDB() {
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        List<Person> ps = s.createCriteria(Person.class).list();
        ArrayList<Person> returnPs = new ArrayList<>(ps);
        
        s.close();
        
        return returnPs;
    }
    
}
