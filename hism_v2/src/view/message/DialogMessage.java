/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.message;

import control.HismHandler;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author patrick
 */
public class DialogMessage {

    private DialogMessage() {
    }
    
    public static int showQuestionMessage(JDialog j, String question, String subject) {
        int n = JOptionPane.showConfirmDialog(j,question,subject,JOptionPane.YES_NO_OPTION);
        return n;
    }

    public static void showMessage(JDialog j, int errorCode) {
        switch (errorCode) {
            case HismHandler.NO_ERROR:
                JOptionPane.showMessageDialog(j, "Færdig!");
                break;
            case HismHandler.FIELDS_NOT_FILLED_ERROR:
                JOptionPane.showMessageDialog(j, "Alle felter skal udfyldes", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.BIRTHDAY_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Fødselsdags dato formatet er skrevet forkert\n- dd/mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.EXPIRATION_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Udløbsdato formatet er skrevet forkert\n- mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.EXPIRATION_DATE_ERROR:
                JOptionPane.showMessageDialog(j, "Udløbsdatoen kan ikke være før, eller lig den nuværende dato", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PICTUREPATH_EMPTY_ERROR:
                JOptionPane.showMessageDialog(j, "Billedet blev ikke fundet\nKontakt en administrator for support", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.GET_ERROR:
                JOptionPane.showMessageDialog(j, "Der skete en fejl da programmet forsøgte at hente et objekt\nKontakt en administrator for support", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.TOO_MANY_GUESTS:
                JOptionPane.showMessageDialog(j, "Der kan ikke tilføjes flere gæster", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.QUARANTINE_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Karantæne udløbsdato formatet er skrevet forkert\n- dd/mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.QUARANTINE_DATE_ERROR:
                JOptionPane.showMessageDialog(j, "Karantæne udløbsdatoen kan ikke være før, eller lig den nuværende dato", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.USERNAME_NOT_FREE_ERROR:
                JOptionPane.showMessageDialog(j, "Det valgte brugernavn er allerede i brug", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PASSWORD_NOT_LONG_ENOUGH_ERROR:
                JOptionPane.showMessageDialog(j, "Koden skal mindst være på 10 tegn", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PASSWORDS_DO_NOT_MATCH:
                JOptionPane.showMessageDialog(j, "Koderne er ikke ens", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.LOGIN_FAILED:
                JOptionPane.showMessageDialog(j, "Brugernavn eller kode er forkert", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static void showMessage(JFrame j, int errorCode) {
        switch (errorCode) {
            case HismHandler.NO_ERROR:
                JOptionPane.showMessageDialog(j, "Done!");
                break;
            case HismHandler.FIELDS_NOT_FILLED_ERROR:
                JOptionPane.showMessageDialog(j, "Alle felter skal udfyldes", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.BIRTHDAY_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Fødselsdags dato formatet er skrevet forkert\n- dd/mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.EXPIRATION_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Udløbsdato formatet er skrevet forkert\n- dd/mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.EXPIRATION_DATE_ERROR:
                JOptionPane.showMessageDialog(j, "Udløbsdatoen kan ikke være før, eller lig den nuværende dato", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PICTUREPATH_EMPTY_ERROR:
                JOptionPane.showMessageDialog(j, "Billedet blev ikke fundet\nKontakt en administrator for support", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.GET_ERROR:
                JOptionPane.showMessageDialog(j, "Der skete en fejl da programmet forsøgte at hente et objekt\nKontakt en administrator for support", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.TOO_MANY_GUESTS:
                JOptionPane.showMessageDialog(j, "Der kan ikke tilføjes flere gæster", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.QUARANTINE_FORMAT_ERROR:
                JOptionPane.showMessageDialog(j, "Karantæne udløbsdato formatet er skrevet forkert\n- dd/mm/yyyy", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.QUARANTINE_DATE_ERROR:
                JOptionPane.showMessageDialog(j, "Karantæne udløbsdatoen kan ikke være før, eller lig den nuværende dato", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.USERNAME_NOT_FREE_ERROR:
                JOptionPane.showMessageDialog(j, "Det valgte brugernavn er allerede i brug", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PASSWORD_NOT_LONG_ENOUGH_ERROR:
                JOptionPane.showMessageDialog(j, "Koden skal mindst være på 10 tegn", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.PASSWORDS_DO_NOT_MATCH:
                JOptionPane.showMessageDialog(j, "Koderne er ikke ens", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
            case HismHandler.LOGIN_FAILED:
                JOptionPane.showMessageDialog(j, "Brugernavn eller kode er forkert", "Fejl", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
