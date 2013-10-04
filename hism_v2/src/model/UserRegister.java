/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class UserRegister {
    
    private HashSet<User> users = new HashSet(0);
    
    public UserRegister() {
    }
    
    /**
     * Registers a user
     * @param u 
     */
    public void registerUser(User u) {
        System.out.println("Registering user...");
        
        users.add(u);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Serializable sz = s.save(u);
        u.setIduser((Integer)sz);
        
        tx.commit();
        s.close();
        System.out.println("Registration complete!");
    }
    
    /**
     * Save a user
     * @param u 
     */
    public void saveUser(User u) {
        System.out.println("Saving user...");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.save(u);
        
        tx.commit();
        s.close();
        System.out.println("Save complete!");
    }
    
    /**
     * Returns a user
     * @param idUser
     * @return User
     */
    public User getUser(int idUser) {
        System.out.println("Getting user...");
        for(User u : users) {
            if(u.getIduser() == idUser) {
                System.out.println("Found user\nGet complete!");
                return u;
            }
        }
        System.out.println("No user found\nGet complete!");
        return null;
    }
    
    /**
     * Delete a user
     * @param u 
     */
    public void deleteUser(User u) {
        System.out.println("Deleting user...");
        
        users.remove(u);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(u);
        
        tx.commit();
        s.close();
        System.out.println("Deletion complete!");
    }

    /**
     * Returns all users
     * @return HashSet<User>
     */
    public HashSet<User> getUsers() {
        System.out.println("Getting all users...");
        System.out.println("Get complete!");
        return users;
    }

    /**
     * Sets all users
     * @param users 
     */
    public void setUsers(HashSet<User> users) {
        System.out.println("Setting all users...");
        System.out.println("Set complete!");
        this.users = users;
    }
    
    
    
}
