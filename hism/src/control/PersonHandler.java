package control;

import java.io.File;
import model.Person;

/**
 *
 * @author pkann
 */
public class PersonHandler {

    private PersonKatalog personKatalog;

    public PersonHandler(PersonKatalog personKatalog) {
        this.personKatalog = personKatalog;
    }

    public void createPerson(String name, String address, String birthdayDate, String expirationDate, File picturePath) {
        Person p = new Person(name, address, birthdayDate, expirationDate, picturePath);
        personKatalog.addPerson(p);
    }
    
    public void createHone(String name, String address, String birthdayDate, String expirationDate, File picturePath, String username, String password, boolean admin, boolean reserve) {
        Person p = new Person(name, address, birthdayDate, expirationDate, picturePath, username, password, admin, reserve);
        personKatalog.addPerson(p);
    }
}
