/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import entity.User;
import java.util.ArrayList;
import model.UserRegister;

/**
 *
 * @author patrick
 */
public class UserHandler {
    
    private UserRegister useReg;
    
    public UserHandler (UserRegister useReg) {
        this.useReg = useReg;
    }
    
    public void createUser(String email, String password, String firstname, String middlename, String lastname, String creationdate, Integer administrator, Integer reserve) {
        User u = new User(email, password, firstname, middlename, lastname, creationdate, administrator, reserve);
        useReg.RegisterUser(u);
    }
    
    public void saveUser(int userid, String email, String password, String firstname, String middlename, String lastname, Integer administrator, Integer reserve) {
        User u = useReg.getUser(userid);
        u.setEmail(email);
        u.setPassword(password);
        u.setFirstname(firstname);
        u.setMiddlename(middlename);
        u.setLastname(lastname);
        u.setAdministrator(administrator);
        u.setReserve(reserve);
        useReg.SaveUser(u);
    }
    
    public User getUser(int userid) {
        return useReg.getUser(userid);
    }
    
    public void deleteUser(int userid) {
        User u = useReg.getUser(userid);
        useReg.RemoveUser(u);
    }
    
    public void loadUsersFromDBToArrays() {
        ArrayList<User> users = useReg.getAllUsersFromDB();
        useReg.setUsers(users);
    }
    
}
