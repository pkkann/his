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

        for (Person p : personKatalog.getPersoner()) {
            if (p.getUsername().equalsIgnoreCase(username)) {
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

    public ArrayList<Person> searchBirthday(String birthday, boolean hone, boolean reserve) {
        return null;
    }

    public ArrayList<Person> searchName(String name, boolean hone, boolean reserve) {
        return null;
    }

    public ArrayList<Person> searchAll(boolean hone, boolean reserve) {
        ArrayList<Person> result = new ArrayList<>();

        if (hone && reserve) {
            for (Person p : personKatalog.getPersoner()) {
                if (p.isHone() && p.isReserve()) {
                    result.add(p);
                }
            }
        } else if (hone) {
            for (Person p : personKatalog.getPersoner()) {
                if (p.isHone()) {
                    result.add(p);
                }
            }
        } else if (reserve) {
            for (Person p : personKatalog.getPersoner()) {
                if (p.isReserve()) {
                    result.add(p);
                }
            }
        } else {
            result = personKatalog.getPersoner();
        }

        return result;
    }

    public ArrayList<Person> getPersoner() {
        return personKatalog.getPersoner();
    }

    public Person getPerson(int index) {
        return personKatalog.getPerson(index);
    }

    public boolean adminPresent() {
        for (Person p : personKatalog.getPersoner()) {
            if (p.isAdmin()) {
                return true;
            }
        }
        return false;
    }
}
