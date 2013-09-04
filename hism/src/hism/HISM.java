package hism;

import control.LookAndFeelDeterminer;
import control.PersonHandler;
import control.PersonKatalog;
import file.FileTool;
import java.io.File;
import javax.swing.JFrame;
import model.Person;
import session.LoginHandler;
import view.*;

/**
 *
 * @author pkann
 */
public class HISM {

    private LookAndFeelDeterminer LAFDeterminer;
    private PersonKatalog personKatalog;
    private MainGUI mainGUI;
    private LoginGUI loginGUI;
    private OpretPersonGUI opretPersonGUI;
    private QuarantineGUI quarantineGUI;
    private LoginHandler loginHandler;
    private PersonHandler personHandler;

    public HISM() {

        // Set look and feel
        LAFDeterminer = new LookAndFeelDeterminer();
        setLookAndFeel();
        
        FileTool.createFolders();
        
        
        // Create person things
        personKatalog = new PersonKatalog();
        personHandler = new PersonHandler(personKatalog);
        
        // Create login things
        loginHandler = new LoginHandler(personKatalog);
        loginGUI = new LoginGUI(mainGUI, true, loginHandler);
        
        // Create opret things
        opretPersonGUI = new OpretPersonGUI(mainGUI, true, personHandler, loginHandler);
        
        // Create quarantine things
        quarantineGUI = new QuarantineGUI(mainGUI, true);
        
        // Create test data
        testData();

        // Create main things
        mainGUI = new MainGUI(loginGUI, opretPersonGUI, loginHandler, personHandler, quarantineGUI);
    }

    public void testData() {
        personHandler.createHone("Patrick Kann", "8-56", "21-04-89", "21-08-2013", new File("//home//pkann//Pictures//images.jpg"), "pkkann", "rollercoaster", true, false);
        personHandler.createHone("Test", "8-56", "21-04-89", "21-08-2013", new File("//home//pkann//Pictures//images.jpg"), "test", "test", false, false);
    }

    private void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (LAFDeterminer.getLookAndFeel().equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    public static void main(String[] args) {
        HISM hism = new HISM();
    }
}
