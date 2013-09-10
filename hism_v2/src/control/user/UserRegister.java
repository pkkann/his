/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.user;

import java.util.ArrayList;
import model.User;

/**
 *
 * @author patrick
 */
public class UserRegister {

    private ArrayList<User> users;

    public UserRegister() {
        users = new ArrayList<>();
    }

    public int size() {
        return users.size();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public int indexOf(Object o) {
        return users.indexOf(o);
    }

    public User get(int index) {
        return users.get(index);
    }

    public User set(int index, User element) {
        return users.set(index, element);
    }

    public boolean add(User e) {
        return users.add(e);
    }

    public void add(int index, User element) {
        users.add(index, element);
    }

    public User remove(int index) {
        return users.remove(index);
    }

    public boolean remove(Object o) {
        return users.remove(o);
    }
}
