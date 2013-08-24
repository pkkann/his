
package Control;

import Model.Person;
import java.awt.Image;

/**
 *
 * @author patrick
 */
public class PersonHandler {
    
    private PersonKatalog personKatalog;

    public PersonHandler(PersonKatalog personKatalog) {
        this.personKatalog = personKatalog;
    }
    
    public void opretPerson(String navn, String adresse, String fodselsdag, Image billed, boolean hone) {
        Person p = new Person(navn, adresse, fodselsdag, billed);
        this.personKatalog.addPerson(p);
    }
}
