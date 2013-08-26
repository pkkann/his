
package Control;

import File.FileTool;
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
    
    public void opretPerson(String navn, String adresse, String fodselsdag, String udlobsdato, File billed, boolean hone) {
        boolean t = FileTool.copyFile(billed, new File("pictures" + "//test.jpg"));
        System.out.println(t);
    }
}
