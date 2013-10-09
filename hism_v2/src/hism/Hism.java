/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import control.*;
import model.*;
import view.*;

/**
 *
 * @author patrick
 */
public class Hism {

    public static final String title = "Den Våde Høne - Indskrivnings system";
    public static final String version = "2.0 Alpha";
    
    // Control
    private PersonHandler peH;
    private UserHandler usH;
    private EnrollmentHandler enH;
    private QuarantineHandler quH;
    
    // Model
    private PersonRegister peR;
    private UserRegister usR;
    private EnrollmentRegister enR;
    private QuarantineRegister quR;
    
    // View
    private MainGUI mainGUI;
    private CreatePersonDIA createPersonDIA;
    private EditPersonDIA editPersonDIA;
    private RemovePersonDIA removePersonDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private QuarantinePersonDIA quarantinePersonDIA;
    private CreateGuestDIA createGuestDIA;
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private RemoveUserDIA removeUserDIA;
    private LoginUserDIA loginUserDIA;
    private ResetPasswordUserDIA resetPasswordUserDIA;
    

    public Hism() {
        setLookAndFeel();
        
        // Model
        peR = new PersonRegister();
        usR = new UserRegister();
        enR = new EnrollmentRegister();
        quR = new QuarantineRegister();
        
        // Control
        peH = new PersonHandler(peR, enR);
        usH = new UserHandler(usR);
        enH = new EnrollmentHandler(enR, peR, usR);
        quH = new QuarantineHandler(quR, peR);
        
        // View
        removeUserDIA = new RemoveUserDIA(mainGUI, true);
        editUserDIA = new EditUserDIA(mainGUI, true);
        createUserDIA = new CreateUserDIA(mainGUI, true, usH);
        mainGUI = new MainGUI(peH, createUserDIA, editUserDIA, removeUserDIA);
        
        // Start sequence
        mainGUI.updateEnrolledCounter();
        mainGUI.updateDate("");
        mainGUI.setVisible(true);
    }

    private void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
    }

    public static void main(String[] args) {
        
        Hism hism = new Hism();

    }
}
