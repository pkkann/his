/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
import hibernate.HiberUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class UserRegister {
    
    private ArrayList<User> users;
    
    public UserRegister() {
        users = new ArrayList<>();
    }
    
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
    
    public int RegisterUser(User u) {
        users.add(u);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        int returnID = (Integer)s.save(u);
        
        tx.commit();
        s.close();
        
        return returnID;
    }
    
    public void RemoveUser (User u) {
        users.remove(u);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.delete(u);
        
        tx.commit();
        s.close();
    }
    
    public void SaveUser (User u) {
        users.set(users.indexOf(u), u);
        
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.saveOrUpdate(u);
        
        tx.commit();
        s.close();
    }
    
    public User getUser (int id) {
        for(User u : users) {
            if(u.getIduser() == id) {
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<User> getAllUsersFromDB() {
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        List<User> us = s.createCriteria(User.class).list();
        ArrayList<User> returnUs = new ArrayList<>(us);
        
        s.close();
        
        return returnUs;
    }
    
}
