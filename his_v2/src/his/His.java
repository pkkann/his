/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package his;

import com.itextpdf.text.DocumentException;
import control.*;
import file.FileTool;
import hibernate.HiberUtil;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import junit.framework.Assert;
import model.*;
import org.hibernate.classic.Session;
import view.*;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import date.DateChecker;
import control.DateHandler;
import java.io.FileNotFoundException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author patrick
 */
public class His {

    // Settings
    public static final String title = "Den Våde Høne - Indskrivnings system";
    public static final String version = "2.0 RC2";
    public static final String picDir = "pictures";
    public static final String reportDir = "reports";
    public static final String reportEnrolledDir = reportDir + "/enrolled";
    public static final String reportPersonsDir = reportDir + "/persons";
    public static final String reportUsersDir = reportDir + "/users";
    public static final String reportQuarantinesDir = reportDir + "/quarantines";
    public static final String webcamDir = "webcam";
    // Control
    private PersonHandler peH;
    private UserHandler usH;
    private EnrollmentHandler enH;
    private QuarantineHandler quH;
    private LoginHandler loH;
    private DateHandler dah;
    private ReportHandler reH;
    // Model
    private PersonRegister peR;
    private UserRegister usR;
    private EnrollmentRegister enR;
    private QuarantineRegister quR;
    private GuestRegister guR;
    // View
    private CreateGuestDIA createGuestDIA;
    private CreatePersonDIA createPersonDIA;
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private EditPersonDIA editPersonDIA;
    private QuarantinePersonManagementDIA quarantinePersonManagemenDIA;
    private RemoveUserDIA removeUserDIA;
    private EditProfileDIA editProfileDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private AboutDIA aboutDIA;
    private MainGUI mainGUI;
    private LoadingGUI loadingGUI;
    private SettingsDIA settingsDIA;
    private RenewPersonDIA renewPersonDIA;

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
        guR = new GuestRegister();

        // Control
        peH = new PersonHandler(peR, enR);
        usH = new UserHandler(usR);
        enH = new EnrollmentHandler(enR, peR, usR, guR);
        quH = new QuarantineHandler(quR, peR, enH);
        dah = new DateHandler(new DateChecker(), peH);
        reH = new ReportHandler(enR, peR, quR, usR);


        // View
        createGuestDIA = new CreateGuestDIA(mainGUI, false, enH);
        createPersonDIA = new CreatePersonDIA(mainGUI, false, peH);
        createUserDIA = new CreateUserDIA(mainGUI, true, usH);
        editUserDIA = new EditUserDIA(mainGUI, true, usH);
        editPersonDIA = new EditPersonDIA(mainGUI, false, peH, enH);
        quarantinePersonManagemenDIA = new QuarantinePersonManagementDIA(mainGUI, true, peH, quH);
        removeUserDIA = new RemoveUserDIA(mainGUI, true, usH);
        settingsDIA = new SettingsDIA(mainGUI, false);
        editProfileDIA = new EditProfileDIA(mainGUI, true, usH);
        enrollPersonDIA = new EnrollPersonDIA(mainGUI, false, enH, createGuestDIA);
        createGuestDIA.setEnrollPersonDIA(enrollPersonDIA);
        aboutDIA = new AboutDIA(mainGUI, true);
        renewPersonDIA = new RenewPersonDIA(mainGUI, true, peH);
        mainGUI = new MainGUI(peH, enH, quH, settingsDIA, createPersonDIA, 
                removeUserDIA, editUserDIA, createUserDIA, editProfileDIA, 
                enrollPersonDIA, aboutDIA, editPersonDIA, 
                quarantinePersonManagemenDIA, renewPersonDIA, reH);

        // Control - login
        loH = new LoginHandler(usR, mainGUI);

        // Start sequence
        System.out.println("##### His starting... #####");
        System.out.println("##### Testing database connection... #####");

        try {
            Session s = HiberUtil.getSessionFactory().openSession();
            Assert.assertFalse(s.connection().isReadOnly());
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(new JDialog(), "Der kunne ikke oprettes forbindelse til databasen", "Fejl", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        System.out.println("##### Creating directories... #####");
        FileTool.createFolders();

        System.out.println("##### Loading tables... #####");
        peR.loadPersonsFromDB();
        usR.loadUsersFromDB();
        quR.loadQuarantinesFromDB();
        enR.loadEnrollmentsFromDB();
        guR.loadGuestsFromDB();
        peH.checkExpirationDates();

        //testData();
        
        loadingGUI.setVisible(false);
        loH.requestLogin();
        System.out.println("##### His started! #####");
    }

    private void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            try {
                UIManager.setLookAndFeel(new WindowsLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {}
        }

        //</editor-fold>
    }

    private void testData() {
//        System.out.println("##### Creating Test Data... #####");
//        usH.createUser("pkkann", "rollercoaster", "rollercoaster", "Patrick", "Diller", "Kann", "10/10/2013", false, true);

//        for (int i = 1; i < 600; i++) {
//            peH.createPerson("person" + i, "person" + i, "person" + i, "person" + i, "21/04/1989", "10/11/2013", "24/10/2013", true, false, false, "N");
//            enH.createEnrollment(i, 4);
//        }
    }

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        His hism = new His();
    }
}
