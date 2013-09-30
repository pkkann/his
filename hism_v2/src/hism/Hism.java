/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import hibernate.HiberUtil;
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
        
        tx.commit();
        s.close();
    }
    
}
