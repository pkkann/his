
package Control;

import File.FileTool;
import Model.Person;
import java.io.File;

/**
 *
 * @author patrick
 */
public class PersonHandler {
    
    private PersonKatalog personKatalog;

    public PersonHandler(PersonKatalog personKatalog) {
        this.personKatalog = personKatalog;
    }
    
    public boolean opretPerson(String navn, String adresse, String fodselsdag, String udlobsdato, File billed, boolean hone) {
        if(FileTool.copyFile(billed, new File(ISM.ISM.picDir + "//" + billed.getName()))) {
            Person p = new Person(navn, adresse, fodselsdag, new File(ISM.ISM.picDir + "//" + billed.getName()));
            personKatalog.addPerson(p);
            return true;
        } else {
            return false;
        }
        
        
    }
}
