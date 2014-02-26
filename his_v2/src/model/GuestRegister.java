/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Enrollment;
import entity.Guest;
import hibernate.HiberUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author patrick
 */
public class GuestRegister {
    
    private HashSet<Guest> guests = new HashSet(0);
    
    public Serializable registerGuest(Guest g) {
        System.out.println("Registering guest...");

        guests.add(g);

        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Serializable sz = s.save(g);
        g.setIdGuest((Integer) sz);

        s.save(g);

        tx.commit();
        s.close();
        System.out.println("Registration complete!");
        return sz;
    }
    
    public Set<Guest> getGuests() {
        return guests;
    }
    
    public Guest getGuest(int idGuest) {
        Iterator<Guest> i = guests.iterator();
        
        while(i.hasNext()) {
            Guest g = i.next();
            if(g.getIdGuest() == idGuest) {
                return g;
            }
        }
        return null;
    }
    
    public void loadGuestsFromDB() {
        System.out.println("Loading guests from db...");
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Criteria c = s.createCriteria(Guest.class);
        List l = c.list();
        for (Object o : l) {
            Guest g = (Guest) o;
            guests.add(g);
            for(Guest g1 : guests) {
                
            }
        }

        tx.commit();
        s.close();
        System.out.println("Load complete!");
    }
}
