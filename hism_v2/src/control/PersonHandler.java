/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import model.PersonRegister;

/**
 *
 * @author patrick
 */
public class PersonHandler {
    
    private PersonRegister peR;
    public static final int NO_ERROR = 0;
    public static final int FILL_OUT_ALL_FIELDS = 1;
    public static final int BIRTHDAY_FORMAT_ERROR = 2;
    public static final int EXPIRATION_FORMAT_ERROR = 3;
    public static final int EXPIRATION_DATE_ERROR = 4;
    public static final int PICTUREPATH_EMPTY_ERROR = 5;
    
    public PersonHandler(PersonRegister peR) {
        this.peR = peR;
    }
    
    /**
     * Create a person
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param birthdayDate
     * @param expirationDate
     * @param creationDate
     * @param picturePath
     * @return Error code : Integer
     */
    public int createPerson(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, String picturePath) {
        
        // Check fields are filled out
        if(firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty() || creationDate.isEmpty() || picturePath.isEmpty()) {
            return FILL_OUT_ALL_FIELDS;
        }
        
        // Check birthday is written correctly
        String[] birth_Split = birthdayDate.split("/");
        String birth_Day = birth_Split[0];
        String birth_Month = birth_Split[1];
        String birth_Year = birth_Split[2];
        
        if(birth_Day.length() != 2 || birth_Month.length() != 2 || birth_Year.length() != 4) {
            return BIRTHDAY_FORMAT_ERROR;
        }
        
        // Check expiration is written correctly
        String[] expire_Split = expirationDate.split("/");
        String expire_Month = expire_Split[0];
        String expire_Year = expire_Split[1];
        
        if(expire_Month.length() != 2 || expire_Year.length() != 4) {
            return EXPIRATION_FORMAT_ERROR;
        }
        
        // Check picturepath
        if(picturePath.isEmpty()) {
            return PICTUREPATH_EMPTY_ERROR;
        }
        
        // Create person
        Person p = new Person(firstname, middlename, lastname, address, birthdayDate, expirationDate, creationDate, picturePath);
        
        // Register person
        peR.registerPerson(p);
        
        return NO_ERROR;
    }
    
    public void savePerson(int personID, String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, String picturePath) {
        
    }
    
    public void removePerson(int personID) {
        
    }
    
}
