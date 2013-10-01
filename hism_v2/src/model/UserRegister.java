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
     * Returns a user
     * @param idUser
     * @return User
     */
    public User getUser(int idUser) {
        for(User u : users) {
            if(u.getIduser() == idUser) {
                return u;
            }
        }
        return null;
    }

    /**
     * Returns all users
     * @return HashSet<User>
     */
    public HashSet<User> getUsers() {
        return users;
    }

    /**
     * Sets all users
     * @param users 
     */
    public void setUsers(HashSet<User> users) {
        this.users = users;
    }
    
    
    
}
