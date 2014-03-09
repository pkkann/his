/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.PersonTask;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Patrick
 */
public class PersonTaskRegister {
    
    private HashSet<PersonTask> personTasks = new HashSet(0);
    
    public PersonTaskRegister() {
        
    }
    
    /**
     * Registers a personTask
     * @param pt
     * @return 
     */
    public Serializable registerPersonTask(PersonTask pt) {
        System.out.println("Registering personTask...");
        
        personTasks.add(pt);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(pt);
        pt.setIdPersonTask((Integer)sz);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
        return sz;
    }
    
    /**
     * Saves a personTask
     * @param pt 
     */
    public void savePersonTask(PersonTask pt) {
        System.out.println("Saving personTask...");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(pt);
        
        tx.commit();
        s.close();
        System.out.println("Save complete!");
    }
    
    /**
     * returns a personTask
     * @param idPersonTask
     * @return 
     */
    public PersonTask getPersonTask(int idPersonTask) {
        System.out.println("Getting personTask...");
        for (PersonTask pt : personTasks) {
            if (pt.getIdPersonTask() == idPersonTask) {
                System.out.println("Found personTask\nGet complete!");
                return pt;
            }
        }
        System.out.println("No personTask found\nGet complete!");
        return null;
    }
    
    /**
     * Deletes a personTask
     * @param pt 
     */
    public void deletePersonTask(PersonTask pt) {
        System.out.println("Deleting personTask...");
        
        personTasks.remove(pt);

        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.delete(pt);

        tx.commit();
        s.close();
        System.out.println("Deletion complete!");
    }
    
    /**
     * Returns all personTasks
     * @return 
     */
    public HashSet<PersonTask> getPersonTasks() {
        System.out.println("Getting all personsTasks...");
        System.out.println("Get complete!");
        return personTasks;
    }
    
    /**
     * Sets alle personTasks
     * @param personTasks 
     */
    public void setPersonTasks(HashSet<PersonTask> personTasks) {
        System.out.println("Setting all personsTasks...");
        this.personTasks = personTasks;
        System.out.println("Set complete!");
    }
    
    /**
     * Loads all personTasks from db to register
     */
    public void loadPersonTasksFromDB() {
        System.out.println("Loading personsTasks from db...");
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Criteria c = s.createCriteria(PersonTask.class);
        List l = c.list();
        for (Object o : l) {
            PersonTask pt = (PersonTask) o;
            personTasks.add(pt);
        }

        tx.commit();
        s.close();
        System.out.println("Load complete!");
    }
    
}
