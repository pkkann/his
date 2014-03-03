/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.User;
import java.util.Comparator;

/**
 *
 * @author patrick
 */
public class UserComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        User u1 = (User)o1;
        User u2 = (User)o2;
        
        return u1.getFirstname().compareToIgnoreCase(u2.getFirstname());
    }
    
}
