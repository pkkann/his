
package Control;

import DB.PersonDAO;
import File.FileTool;
import Model.Person;
import java.io.File;

/**
 *
 * @author patrick
 */
public class PersonHandler {
    
    private PersonKatalog personKatalog;
    private PersonDAO personDAO;

    public PersonHandler(PersonKatalog personKatalog, PersonDAO personDAO) {
        this.personKatalog = personKatalog;
        this.personDAO = personDAO;
    }
    
    public boolean opretPerson(String navn, String adresse, String fodselsdag, String udlobsdato, File billed, boolean hone) {
        if(FileTool.copyFile(billed, new File(ISM.ISM.picDir + "//" + billed.getName()))) {
            Person p = new Person(navn, adresse, fodselsdag, udlobsdato, new File(ISM.ISM.picDir + "//" + billed.getName()), hone);
            
            personKatalog.addPerson(p);
            personDAO.insertPerson(p);
            
            return true;
        } else {
            return false;
        }
        
        
    }
}
