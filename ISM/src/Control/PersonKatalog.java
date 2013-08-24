
package Control;

import Model.Person;
import java.util.ArrayList;

/**
 *
 * @author patrick
 */
public class PersonKatalog {

    private ArrayList<Person> personer;
    
    public PersonKatalog() {
        personer = new ArrayList<>();
    }
    
    public void addPerson(Person p) {
        personer.add(p);
    }
}
