/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package his;

import control.*;
import file.FileTool;
import hibernate.HiberUtil;
import model.*;
import org.hibernate.classic.Session;
import view.*;

/**
 *
 * @author patrick
 */
public class His {

    // Settings
    public static final String title = "Den Våde Høne - Indskrivnings system";
    public static final String version = "1.1 Alpha";
    public static final String picDir = "pictures";
    public static final String reportDir = "reports";
    // Control
    private PersonHandler peH;
    private UserHandler usH;
    private EnrollmentHandler enH;
    private QuarantineHandler quH;
    private LoginHandler loH;
    // Model
    private PersonRegister peR;
    private UserRegister usR;
    private EnrollmentRegister enR;
    private QuarantineRegister quR;
    // View
    private CreateGuestDIA createGuestDIA;
    private CreatePersonDIA createPersonDIA;
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private RemoveUserDIA removeUserDIA;
    private EditProfileDIA editProfileDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private MainGUI mainGUI;
    private LoadingGUI loadingGUI;
    private SettingsDIA settingsDIA;

    public His() {
        setLookAndFeel();
        
        // View - loading pane
        loadingGUI = new LoadingGUI();
        loadingGUI.setVisible(true);

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
        createGuestDIA = new CreateGuestDIA(mainGUI, true, enH);
        createPersonDIA = new CreatePersonDIA(mainGUI, true, peH);
        createUserDIA = new CreateUserDIA(mainGUI, true, usH);
        editUserDIA = new EditUserDIA(mainGUI, true, usH);
        removeUserDIA = new RemoveUserDIA(mainGUI, true, usH);
        settingsDIA = new SettingsDIA(mainGUI, true);
        editProfileDIA = new EditProfileDIA(mainGUI, true, usH);
        enrollPersonDIA = new EnrollPersonDIA(mainGUI, true, enH, createGuestDIA);
        mainGUI = new MainGUI(peH, enH, quH, settingsDIA, createPersonDIA, removeUserDIA, editUserDIA, createUserDIA, editProfileDIA, enrollPersonDIA);
        
        // Control - login
        loH = new LoginHandler(usR, mainGUI);

        // Start sequence
        System.out.println("##### His starting... #####");
        System.out.println("##### Testing database connection... #####");
        
        Session s = HiberUtil.getSessionFactory().openSession();
        s.close();
        
        System.out.println("##### Creating directories... #####");
        FileTool.createFolders();

        testData();
        
        loadingGUI.setVisible(false);
        loH.requestLogin();
        System.out.println("##### His started! #####");
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
        System.out.println("##### Creating Test Data... #####");
        usH.createUser("pkkann", "rollercoaster", "rollercoaster", "Patrick", "Diller", "Kann", "10/10/2013", false, true);
        usH.createUser("pkkann2", "rollercoaster2", "rollercoaster2", "Patrick2", "", "Kann2", "10/10/2013", false, false);

        peH.createPerson("Patrick", "", "Kann", "8-56", "21/04/1989", "10/2015", "11/10/2013", false, false, false, "N");
        //quH.createQuarantine(1, "01/2015");
    }

    public static void main(String[] args) {
        His hism = new His();
    }
}
