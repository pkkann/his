/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Enrollment;
import entity.Guest;
import entity.Person;
import entity.User;
import exception.IDNotFoundException;
import java.util.HashSet;
import model.EnrollmentRegister;
import model.PersonRegister;
import model.UserRegister;

/**
 *
 * @author patrick
 */
public class EnrollmentHandler {

    private EnrollmentRegister enR;
    private PersonRegister peR;
    private UserRegister usR;

    public EnrollmentHandler(EnrollmentRegister enR, PersonRegister peR) {
        this.enR = enR;
        this.peR = peR;
    }

    /**
     * Create an enrollment of a person. Guests should be delivered in a HashSet
     * with string arrays representing every guest
     *
     * @param idPerson
     * @param idUser
     * @param guests
     */
    public void createEnrollment(int idPerson, int idUser, HashSet<String[]> guests) throws IDNotFoundException {
        System.out.println("Creating an enrollment...");

        Person p = peR.getPerson(idPerson);
        User u = usR.getUser(idUser);
        HashSet<Guest> gg = new HashSet();

        if (p != null && u != null) {
            System.out.println("Doing sub-creations");
            for (String[] sa : guests) {
                Guest g = new Guest(sa[0], sa[1], sa[2], sa[3], sa[4]);
                gg.add(g);
            }
            System.out.println("Sub-creations complete!");

            Enrollment en = new Enrollment(p, u);
            en.setGuests(gg);

            System.out.println("Creation complete!");

            enR.registerEnrollment(en);
        } else {
            throw new IDNotFoundException();
        }

    }

    /**
     * Save an enrollment with new details
     * @param idEnrollment
     * @param idPerson
     * @param idUser
     * @param guests
     * @throws IDNotFoundException 
     */
    public void saveEnrollment(int idEnrollment, int idPerson, int idUser, HashSet<String[]> guests) throws IDNotFoundException {
        System.out.println("Setting an enrollment to new details...");
        
        Enrollment en = enR.getEnrollment(idEnrollment);
        Person p = peR.getPerson(idPerson);
        User u = usR.getUser(idUser);
        HashSet<Guest> gg = new HashSet();
        
        if (en != null && p != null && u != null) {
            System.out.println("Doing sub-creations");
            for (String[] sa : guests) {
                Guest g = new Guest(sa[0], sa[1], sa[2], sa[3], sa[4]);
                gg.add(g);
            }
            System.out.println("Sub-creations complete!");
            
            en.setEnrolledPerson(p);
            en.setEnrolledByUser(u);
            en.setGuests(gg);

            System.out.println("Set complete!");

            enR.saveEnrollment(en);
        } else {
            throw new IDNotFoundException();
        }
    }

    /**
     * Removes an enrollment
     * @param idEnrollment
     * @throws IDNotFoundException 
     */
    public void removeEnrollment(int idEnrollment) throws IDNotFoundException {
        System.out.println("Removing an enrollment...");

        Enrollment en = enR.getEnrollment(idEnrollment);

        if (en != null) {
            enR.deleteEnrollment(en);

            System.out.println("Removal complete!");
        } else {
            throw new IDNotFoundException();
        }
    }
}
