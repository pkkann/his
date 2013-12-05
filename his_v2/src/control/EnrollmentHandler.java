/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.HismHandlerIF.BIRTHDAY_FORMAT_ERROR;
import static control.HismHandlerIF.PICTUREPATH_EMPTY_ERROR;
import entity.Enrollment;
import entity.Guest;
import entity.Person;
import entity.User;
import file.FileTool;
import java.io.File;
import java.io.Serializable;
import model.EnrollmentRegister;
import model.PersonRegister;
import model.UserRegister;

/**
 *
 * @author patrick
 */
public class EnrollmentHandler implements HismHandlerIF {

    private EnrollmentRegister enR;
    private PersonRegister peR;
    private UserRegister usR;

    public EnrollmentHandler(EnrollmentRegister enR, PersonRegister peR, UserRegister usR) {
        this.enR = enR;
        this.peR = peR;
        this.usR = usR;
    }

    /**
     * Create an enrollment
     *
     * @param personID
     * @param userID
     * @return Error code : Integer
     */
    public int createEnrollment(int idPerson, int idUser) {

        // Get person
        Person p = peR.getPerson(idPerson);

        // Get user
        User u = usR.getUser(idUser);

        if (p != null && u != null) {
            // Create enrollment
            Enrollment en = new Enrollment(p, u);

            //Register enrollment
            enR.registerEnrollment(en);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Create a guest
     *
     * @param enrollmentID
     * @param personID
     * @param firstname
     * @param middlename
     * @param lastname
     * @param birthdayDate
     * @param creationDate
     * @return Error code : Integer
     */
    public int createGuest(int idEnrollment, int idPerson, String firstname, String middlename, String lastname, String birthdayDate, String creationDate, String picturePath) {

        // Get enrollment
        Enrollment en = enR.getEnrollment(idPerson);

        // Get person
        Person p = peR.getPerson(idPerson);

        if (en != null && p != null) {
            // Check fields are filled
            if (firstname.isEmpty() || lastname.isEmpty() || birthdayDate.isEmpty() || creationDate.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }

            // Check birthday is written correctly
            try {
                String[] birth_Split = birthdayDate.split("/");
                String birth_Day = birth_Split[0];
                String birth_Month = birth_Split[1];
                String birth_Year = birth_Split[2];

                if (birth_Day.length() != 2 || birth_Month.length() != 2 || birth_Year.length() != 4) {
                    return BIRTHDAY_FORMAT_ERROR;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                return BIRTHDAY_FORMAT_ERROR;
            }

            // Check picturepath
            boolean copyPic = false;
            if (picturePath.isEmpty()) {
                return PICTUREPATH_EMPTY_ERROR;
            } else if (!picturePath.equals("N")) {
                copyPic = true;
            }

            // Check number of guests allowed
            if (p.isHoene()) {
                if (en.getGuests().size() < 5) {
                    // Create guest
                    Guest g = new Guest(firstname, middlename, lastname, birthdayDate, creationDate, picturePath);

                    // Register guest
                    Serializable sz = enR.registerGuest(en, g);

                    // Copy picture
                    if (copyPic) {
                        String oldPicturePath = picturePath;
                        String newPicturePath = his.His.picDir + "/guests/" + (Integer) sz + "/" + "face.jpg";
                        FileTool.copyFile(new File(oldPicturePath), new File(newPicturePath));
                        g.setPicturePath(newPicturePath);
                        enR.saveEnrollment(en);
                    }
                } else {
                    return TOO_MANY_GUESTS;
                }
            } else {
                if (en.getGuests().size() < 3) {
                    // Create guest
                    Guest g = new Guest(firstname, middlename, lastname, birthdayDate, creationDate, picturePath);

                    // Register guest
                    Serializable sz = enR.registerGuest(en, g);

                    // Copy picture
                    if (copyPic) {
                        String oldPicturePath = picturePath;
                        String newPicturePath = his.His.picDir + "/guests/" + (Integer) sz + "/" + "face.jpg";
                        FileTool.copyFile(new File(oldPicturePath), new File(newPicturePath));
                        g.setPicturePath(newPicturePath);
                        enR.saveEnrollment(en);
                    }
                } else {
                    return TOO_MANY_GUESTS;
                }
            }
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Remove a guest
     *
     * @param idEnrollment
     * @param idGuest
     * @return Error code : Integer
     */
    public int removeGuest(int idPerson, int idGuest) {

        // Get enrollment
        Enrollment en = enR.getEnrollment(idPerson);

        // Get guest
        Guest g = enR.getGuest(en, idGuest);

        if (g != null) {
            enR.DeleteGuest(en, g);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }
    
    /**
     * Return a guest
     * @param idPerson
     * @param idGuest
     * @return g : Guest
     */
    public Guest getGuest(int idPerson, int idGuest) {
        
        // Get enrollment
        Enrollment en = enR.getEnrollment(idPerson);
        
        // Get guest
        Guest g = enR.getGuest(en, idGuest);
        
        return g;
    }

    /**
     * Remove an enrollment
     *
     * @param idPerson
     * @return Error code : Integer
     */
    public int removeEnrollment(int idPerson) {

        // Get enrollment
        Enrollment en = getEnrollment(idPerson);

        if (en != null) {
            // Remove enrollment
            enR.deleteEnrollment(en);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Return an enrollment
     *
     * @param idPerson
     * @return en : Enrollment
     */
    public Enrollment getEnrollment(int idPerson) {
        return enR.getEnrollment(idPerson);
    }

    /**
     * Returns wether a person is enrolled or not
     *
     * @param idPerson
     * @return boolean
     */
    public boolean isEnrolled(int idPerson) {
        Enrollment en = enR.getEnrollment(idPerson);
        if (en == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Removes all enrollments
     * @return Error code : Integer
     */
    public int removeAllEnrollments() {
        enR.removeAllEnrollments();
        return NO_ERROR;
    }
}
