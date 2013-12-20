/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import entity.Quarantine;
import model.PersonRegister;
import model.QuarantineRegister;

/**
 *
 * @author patrick
 */
public class QuarantineHandler implements HismHandlerIF {

    private QuarantineRegister quR;
    private PersonRegister peR;
    private EnrollmentHandler enH;

    public QuarantineHandler(QuarantineRegister qaR, PersonRegister peR, EnrollmentHandler enH) {
        this.quR = qaR;
        this.peR = peR;
        this.enH = enH;
    }

    /**
     * Create a quarantine
     *
     * @param personID
     * @return Error code : Integer
     */
    public int createQuarantine(int personID) {

        if (!enH.isEnrolled(personID)) {

            // Get person
            Person p = peR.getPerson(personID);

            Quarantine q;

            if (p != null) {
                // Create quarantine
                q = new Quarantine(p);

                // Register quarantine
                quR.registerQuarantine(q);
            } else {
                return GET_ERROR;
            }
        } else {
            return ENROLLED_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Save a quarantine
     *
     * @param quarantineID
     * @return Error code : Integer
     */
    public int saveQuarantine(int quarantineID) {

        // Get quarantine
        Quarantine q = quR.getQuarantine(quarantineID);

        if (q != null) {
            quR.saveQuarantine(q);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Remove a quarantine
     *
     * @param idPerson
     * @return Error code : Integer
     */
    public int removeQuarantine(int idPerson) {

        // Get quarantine
        Quarantine q = quR.getQuarantine(idPerson);

        if (q != null) {
            // Remove quarantine
            quR.deleteQuarantine(q);
        } else {
            return GET_ERROR;
        }

        return NO_ERROR;
    }

    /**
     * Return a quarantine
     *
     * @param idPerson
     * @return qa : Quarantine
     */
    public Quarantine getQuarantine(int idPerson) {
        return quR.getQuarantine(idPerson);
    }

    /**
     * Returns wether a person is quarantined or not
     *
     * @param idPerson
     * @return boolean
     */
    public boolean isQuarantined(int idPerson) {
        Quarantine q = quR.getQuarantine(idPerson);
        if (q == null) {
            return false;
        } else {
            return true;
        }
    }

}
