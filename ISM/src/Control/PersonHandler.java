package Control;

import DB.PersonDAO;
import File.FileTool;
import Model.HoneUser;
import Model.Person;
import View.OpretHoneGUI;
import java.io.File;

/**
 *
 * @author patrick
 */
public class PersonHandler {

    private PersonKatalog personKatalog;
    private PersonDAO personDAO;
    private OpretHoneGUI opretHoneGUI;

    public PersonHandler(PersonKatalog personKatalog, PersonDAO personDAO, OpretHoneGUI opretHoneGUI) {
        this.personKatalog = personKatalog;
        this.personDAO = personDAO;
        this.opretHoneGUI = opretHoneGUI;
    }

    public boolean opretPerson(String navn, String adresse, String fodselsdag, String udlobsdato, File billed, boolean hone) {
        if (FileTool.copyFile(billed, new File(ISM.ISM.picDir + "//" + billed.getName()))) {
            Person p = new Person(navn, adresse, fodselsdag, udlobsdato, new File(ISM.ISM.picDir + "//" + billed.getName()), hone);
            
            if(hone) {
                HoneUser hu = null;
                opretHoneGUI.setHoneUser(hu);
                opretHoneGUI.setVisible(true);
                p.setHoneUser(hu);
            }
            
            personKatalog.addPerson(p);
            personDAO.insertPerson(p);

            return true;
        } else {
            return false;
        }
    }
}
