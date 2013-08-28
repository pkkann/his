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

    public void createPerson(String name, String address, String birthdayDate, String expirationDate, File picturePath, boolean hone) {
        if (!hone) {
            Person p = new Person(name, address, birthdayDate, expirationDate, picturePath);
        } else {
            
        }
    }
}
