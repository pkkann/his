package session;

import control.PersonKatalog;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author pkann
 */
public class LoginHandler {

    private PersonKatalog personKatalog;
    private Person loggedIn = null;

    public LoginHandler(PersonKatalog personKatalog) {
        this.personKatalog = personKatalog;
    }

    public Person getLoggedIn() {
        return loggedIn;
    }
    
    public boolean isAdmin() {
        return loggedIn.isAdmin();
    }
    
    public boolean login(String username, String password) {
        ArrayList<Person> personer = personKatalog.getPersoner();
        
        for(Person p : personer) {
            if(p.isHone()) {
                System.out.println("Høne fundet");
                if(p.getUsername().equals(username)) {
                    System.out.println("Username matcher");
                    if(p.getPassword().equals(password)) {
                        System.out.println("Password matcher");
                        loggedIn = p;
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void logout() {
        loggedIn = null;
    }
}
