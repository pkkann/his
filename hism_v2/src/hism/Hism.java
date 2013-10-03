/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import entity.Enrollment;
import entity.Guest;
import entity.Person;
import entity.Quarantine;
import entity.User;
import hibernate.HiberUtil;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author patrick
 */
public class Hism {
    
    public Hism() {
        
    }
    
    public static void main(String[] args) {
        Session s = HiberUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        Person p = new Person("Patrick", "", "Kann", "8-56", "210489", "031013", "031013");
        User u = new User("pkkann", "rollercoaster", "Patrick", "", "Kann", "031013", false, false);
        Enrollment en = new Enrollment(p, u);
        Set guests = new HashSet();
        Guest g = new Guest("Lars", "", "Kann", "050645", "031013");
        guests.add(g);
        en.setGuests(guests);
        
        Quarantine q = new Quarantine("012014", p);
        
        s.save(u);
        s.save(p);
        s.save(en);
        s.save(g);
        s.save(q);
        
        
        tx.commit();
        s.close();
    }
    
}
