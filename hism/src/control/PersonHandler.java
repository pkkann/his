package control;

import file.FileTool;
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
        File picPath = null;
        if (picturePath != null) {
            picPath = new File(FileTool.picDir + "//" + picturePath.getName());
            FileTool.copyFile(picturePath, picPath);
        }
        Person p = new Person(name, address, birthdayDate, expirationDate, picPath);
        personKatalog.addPerson(p);
    }

    public void createHone(String name, String address, String birthdayDate, String expirationDate, File picturePath, String username, String password, boolean admin, boolean reserve) {
        File picPath = null;
        if (picturePath != null) {
            picPath = new File(FileTool.picDir + "//" + picturePath.getName());
            FileTool.copyFile(picturePath, picPath);
        }
        Person p = new Person(name, address, birthdayDate, expirationDate, picPath, username, password, admin, reserve);
        personKatalog.addPerson(p);
    }
}
