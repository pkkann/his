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
    private Person loggedIn;

    public LoginHandler(PersonKatalog personKatalog) {
        this.personKatalog = personKatalog;
    }

    public Person getLoggedIn() {
        return loggedIn;
    }
    
    public boolean login(String username, String password) {
        ArrayList<Person> personer = personKatalog.getPersoner();
        
        for(Person p : personer) {
            if(p.isHone()) {
                if(p.getUsername().equals(username)) {
                    if(p.getPassword().equals(password)) {
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
