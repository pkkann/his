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
public class UserHandler implements HismHandlerIF {

    private UserRegister usR;

    public UserHandler(UserRegister usR) {
        this.usR = usR;
    }

    /**
     * Create a user
     *
     * @param username
     * @param password
     * @param firstname
     * @param middlename
     * @param lastname
     * @param creationDate (DDMMYYYY)
     * @param reserve
     * @param administrator
     * @return Error code : Integer
     */
    public int createUser(String username, String password, String passwordAgain, String firstname, String middlename, String lastname, String creationDate, boolean reserve, boolean administrator) {

        // Check fields are filled
        if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || creationDate.isEmpty()) {
            return FIELDS_NOT_FILLED_ERROR;
        }

        // Check if username is free
        for (User u : usR.getUsers()) {
            if (u.getUsername().equals(username)) {
                return USERNAME_NOT_FREE_ERROR;
            }
        }

        // Check if password is more than 8 characters
        if (password.length() < 10) {
            return PASSWORD_NOT_LONG_ENOUGH_ERROR;
        }

        // Check if passwords are the same
        if (!password.equals(passwordAgain)) {
            return PASSWORDS_DO_NOT_MATCH;
        }

        // Create user
        User u = new User(username, password, firstname, middlename, lastname, creationDate, reserve, administrator);

        // Register user
        usR.registerUser(u);

        return NO_ERROR;
    }

    /**
     * Save a user
     *
     * @param userID
     * @param username
     * @param password
     * @param firstname
     * @param middlename
     * @param lastname
     * @param reserve
     * @param administrator
     * @return Error code : Integer
     */
    public int saveUser(int userID, String username, String password, String firstname, String middlename, String lastname, boolean reserve, boolean administrator) {

        // Get user
        User u = usR.getUser(userID);

        if (u != null) {
            // Check fields are filled
            if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }

            // Check if username is free
            if (!u.getUsername().equals(username)) {
                for (User us : usR.getUsers()) {
                    if (us.getUsername().equals(username)) {
                        return USERNAME_NOT_FREE_ERROR;
                    }
                }
            }

            // Check if password is more than 8 characters
            if (password.length() < 8) {
                return PASSWORD_NOT_LONG_ENOUGH_ERROR;
            }

            // Set user
            u.setAdministrator(administrator);
            u.setFirstname(firstname);
            u.setMiddlename(middlename);
            u.setLastname(lastname);
            u.setPassword(password);
            u.setReserve(reserve);
            u.setUsername(username);

            // Save user
            usR.saveUser(u);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Remove a user
     *
     * @param userID
     * @return Error code : Integer
     */
    public int removeUser(int userID) {

        // Get user
        User u = usR.getUser(userID);

        if (u != null) {
            // Delete user
            usR.deleteUser(u);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }
    /**
     * Returns a result based on firstname, middlename and lastname
     * @param firstname
     * @param middlename
     * @param lastname
     * @return data : ArrayList<String[]>
     */
    public ArrayList<String[]> searchUserLon(String firstname, String middlename, String lastname) {
        // Create collection
        ArrayList<String[]> data = new ArrayList<>();

        // Make full name
        String name = firstname + " " + middlename + " " + lastname;
        
        for (User u : usR.getUsers()) {
            if ((u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname()).equals(name)) {
                String[] dat = {u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname()};
                data.add(dat);
            }
        }
        
        return data;
    }

    /**
     * Returns a result based on a string
     *
     * @param search
     * @return data : ArrayList<String[]>
     */
    public ArrayList<User> searchUser(String search) {
        // Create collection
        ////ArrayList<String[]> data = new ArrayList<>();
        ArrayList<User> data = new ArrayList<>();

        // Split string into words
        String[] sSplit = search.split(" ");

        // Do search
        if (search.isEmpty()) {
            for (User u : usR.getUsers()) {
                String admin = "Nej";
                if (u.isAdministrator()) {
                    admin = "Ja";
                }
                String reserve = "Nej";
                if (u.isReserve()) {
                    reserve = "Ja";
                }
//                String[] dat = {String.valueOf(u.getIduser()), u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname(), u.getUsername(), admin, reserve};
//                data.add(dat);
                data.add(u);
            }
        } else {
            for (User u : usR.getUsers()) {
                for (String s : sSplit) {
                    if (u.getFirstname().equalsIgnoreCase(s) || u.getMiddlename().equalsIgnoreCase(s) || u.getLastname().equalsIgnoreCase(s) || u.getUsername().equalsIgnoreCase(s)) {
                        String admin = "Nej";
                        if (u.isAdministrator()) {
                            admin = "Ja";
                        }
                        String reserve = "Nej";
                        if (u.isReserve()) {
                            reserve = "Ja";
                        }
//                        String[] dat = {String.valueOf(u.getIduser()), u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname(), u.getUsername(), admin, reserve};
//                        data.add(dat);
                        data.add(u);
                        break;
                    }
                }
            }
        }

        return data;
    }

    /**
     * Returns a user based on an Integer
     * 
     * @param id
     * @return u : User
     */
    public User getUser(int id) {
        User u = null;

        for (User uu : usR.getUsers()) {
            if (uu.getIduser() == id) {
                u = uu;
                break;
            }
        }

        return u;
    }
}
