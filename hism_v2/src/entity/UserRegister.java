/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashSet;

/**
 *
 * @author patrick
 */
public class UserRegister {
    
    private HashSet<User> users = new HashSet(0);
    
    public UserRegister() {
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }
    
    
    
}
