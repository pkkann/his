
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
    
    public void opretPerson(String navn, String adresse, String fodselsdag, String udlobsdato, Image billed, boolean hone) {
        System.out.println("BAAAAAAAAAH");
    }
}
