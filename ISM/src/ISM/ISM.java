package ISM;

import Control.PersonHandler;
import Control.PersonKatalog;
import DB.PersonDAO;
import View.LoginGUI;
import View.MainGUI;
import View.OpretHoneGUI;
import View.OpretPersonGUI;
import java.io.File;

/**
 *
 * @author patrick
 */
public class ISM {

    private PersonDAO personDAO;
    
    private PersonKatalog personKatalog;
    
    private PersonHandler personHandler;
    
    private LoginGUI loginGUI;
    private OpretHoneGUI opretHoneGUI;
    private MainGUI mainGUI;
    private OpretPersonGUI opretPersonGUI;
    
    public static final String picDir = "pictures";

    public ISM() {
        setLookAndFeel();
        createFolders();
        
        personDAO = new PersonDAO();
        
        personKatalog = new PersonKatalog();
        
        opretHoneGUI = new OpretHoneGUI(mainGUI, true);
        personHandler = new PersonHandler(personKatalog, personDAO, opretHoneGUI);
        
        
        loginGUI = new LoginGUI(mainGUI, true);
        opretPersonGUI = new OpretPersonGUI(mainGUI, true, personHandler);
        mainGUI = new MainGUI(loginGUI, opretPersonGUI, personHandler);
    }
    
    private void createFolders() {
        File dir = new File(picDir);
        dir.mkdir();
    }

    public void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    public static void main(String[] args) {
        ISM ism = new ISM();
    }
}
