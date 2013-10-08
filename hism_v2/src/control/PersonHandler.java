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
public class PersonHandler implements HismHandler{
    
    private PersonRegister peR;
    
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
    public int createPerson(String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, String picturePath) {
        
        // Check fields are filled
        if(firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty() || creationDate.isEmpty() || picturePath.isEmpty()) {
            return FIELDS_NOT_FILLED_ERROR;
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
        Person p = new Person(firstname, middlename, lastname, address, birthdayDate, expirationDate, creationDate, hoene, reserve, picturePath);
        
        // Register person
        peR.registerPerson(p);
        
        return NO_ERROR;
    }
    
    /**
     * Save a person
     * @param personID
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param birthdayDate
     * @param expirationDate
     * @param picturePath
     * @return Error code : Integer
     */
    public int savePerson(int personID, String firstname, String middlename, String lastname, String address, String birthdayDate, String expirationDate, String picturePath) {
        // Check fields are filled
        if(firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty() || picturePath.isEmpty()) {
            return FIELDS_NOT_FILLED_ERROR;
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
        
        // Set person
        Person p = peR.getPerson(personID);
        if(p == null) {
            return GET_ERROR;
        } else {
            p.setFirstname(firstname);
            p.setMiddlename(middlename);
            p.setLastname(lastname);
            p.setAddress(address);
            p.setBirthdayDate(birthdayDate);
            p.setExpirationDate(expirationDate);
            p.setPicturePath(picturePath);
        }
        
        // Register person
        peR.savePerson(p);
        
        return NO_ERROR;
    }
    
    /**
     * Remove a person
     * @param personID
     * @return Error code : Integer
     */
    public int removePerson(int personID) {
        Person p = peR.getPerson(personID);
        
        if(p == null) {
            return GET_ERROR;
        } else {
            peR.deletePerson(p);
        }
        
        return NO_ERROR;
    }
    
}
