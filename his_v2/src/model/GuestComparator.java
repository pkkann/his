/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Guest;
import java.util.Comparator;

/**
 *
 * @author patrick
 */
public class GuestComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Guest g1 = (Guest)o1;
        Guest g2 = (Guest)o2;
        
        return g1.getFirstname().compareToIgnoreCase(g2.getFirstname());
    }
    
}
