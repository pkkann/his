package control;

import file.FileTool;
import java.io.File;
import java.util.ArrayList;
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

    public int createHone(String name, String address, String birthdayDate, String expirationDate, File picturePath, String username, String password, boolean admin, boolean reserve) {
        
        for(Person p : personKatalog.getPersoner()) {
            if(p.getUsername().equalsIgnoreCase(username)) {
                return 1;
            }
        }
        
        File picPath = null;
        if (picturePath != null) {
            picPath = new File(FileTool.picDir + "//" + picturePath.getName());
            FileTool.copyFile(picturePath, picPath);
        }
        
        Person p = new Person(name, address, birthdayDate, expirationDate, picPath, username, password, admin, reserve);
        personKatalog.addPerson(p);
        return -1;
    }
    
    public ArrayList<Person> searchBirthday(String birthday) {
        
        return null;
    }
    
    public ArrayList<Person> searchName(String name) {
        
        return null;
    }
    
    public ArrayList<Person> getPersoner() {
        return personKatalog.getPersoner();
    }
    
    public Person getPerson(int index) {
        return personKatalog.getPerson(index);
    }
    
    
}
