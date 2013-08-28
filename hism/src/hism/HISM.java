package hism;

import control.LookAndFeelDeterminer;
import control.PersonHandler;
import control.PersonKatalog;
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
    
    private LoginHandler loginHandler;
    private PersonHandler personhandler;

    public HISM() {

        LAFDeterminer = new LookAndFeelDeterminer();
        setLookAndFeel();
        
        personKatalog = new PersonKatalog();
        
        personhandler = new PersonHandler(personKatalog);
        loginHandler = new LoginHandler(personKatalog);
        
        mainGUI = new MainGUI();
        loginGUI = new LoginGUI(mainGUI, true);
        
        mainGUI.setVisible(true);
        
        while(loginHandler.getLoggedIn() == null) {
            loginGUI.setVisible(true);
        }
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
