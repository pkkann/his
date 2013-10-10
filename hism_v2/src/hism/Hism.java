/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import control.*;
import hibernate.HiberUtil;
import javax.swing.JFrame;
import model.*;
import org.hibernate.classic.Session;
import view.*;

/**
 *
 * @author patrick
 */
public class Hism {

    // Settings
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
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private RemoveUserDIA removeUserDIA;
    private MainGUI mainGUI;
    
    
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
        createUserDIA = new CreateUserDIA(mainGUI, true, usH);
        editUserDIA = new EditUserDIA(mainGUI, true, usH);
        removeUserDIA = new RemoveUserDIA(mainGUI, true, usH);
        mainGUI = new MainGUI(peH, removeUserDIA, editUserDIA, createUserDIA);

        // Start sequence
        System.out.println("##### Hism starting #####");
        System.out.println("##### Hism testing database connection #####");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        s.close();
        testData();
        
        mainGUI.updateDate("");
        mainGUI.updateEnrolledCounter();
        mainGUI.setVisible(true);
        System.out.println("##### Hism started! #####");
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

    private void testData() {
        usH.createUser("pkkann", "rollercoaster", "rollercoaster", "Patrick", "Diller", "Kann", "10102013", false, false);
        usH.createUser("pkkann2", "rollercoaster2", "rollercoaster2", "Patrick2", "", "Kann2", "10102013", false, false);
    }

    public static void main(String[] args) {

        Hism hism = new Hism();

    }
}
