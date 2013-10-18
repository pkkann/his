/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import control.person.PersonHandler;
import control.person.PersonRegister;
import control.picture.PictureHandler;
import control.picture.PictureRegister;
import control.user.UserHandler;
import control.user.UserRegister;
import date.ADate;
import date.DateChangeDetecter;
import db.person.PersonDAO;
import db.user.UserDAO;
import file.FileTool;
import java.io.File;
import javax.swing.JOptionPane;
import login.LoginHandler;
import view.*;
import view.person.AddGuestDIA;
import view.person.CreatePersonDIA;
import view.person.EditPersonDIA;
import view.person.EnrollPersonDIA;
import view.person.RemovePersonDIA;
import view.person.RenewPersonDIA;
import view.user.CreateUserDIA;
import view.user.EditUserDIA;
import view.user.RemoveUserDIA;
import view.user.ResetUserPasswordDIA;

/**
 *
 * @author patrick
 */
public class HISM {

    public static final String title = "Den våde høne - Indskrivnings system";
    public static final String version = "v1.0";
    private DateChangeDetecter dch;
    private UserRegister userRegister;
    private UserHandler userHandler;
    private LoginHandler loginHandler;
    private static MainGUI mainGUI;
    private static LoginDIA loginDIA;
    private ResetUserPasswordDIA resetUserPasswordDIA;
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private RemoveUserDIA removeUserDIA;
    private CreatePersonDIA createPersonDIA;
    private EditPersonDIA editPersonDIA;
    private RemovePersonDIA removePersonDIA;
    private RenewPersonDIA renewPersonDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private AddGuestDIA addGuestDIA;
    private PersonRegister personRegister;
    private PersonHandler personHandler;
    private PictureRegister pictureRegister;
    private PictureHandler pictureHandler;
    private UserDAO userDAO;
    private PersonDAO personDAO;

    public HISM() {
        setLookAndFeel();

        pictureRegister = new PictureRegister();
        pictureHandler = new PictureHandler(pictureRegister);
        personRegister = new PersonRegister();
        personDAO = new PersonDAO();
        personHandler = new PersonHandler(personRegister, pictureHandler, personDAO);

        userDAO = new UserDAO();
        userRegister = new UserRegister();
        userHandler = new UserHandler(userRegister, userDAO);

        loginHandler = new LoginHandler(loginDIA, userHandler, mainGUI);
        createPersonDIA = new CreatePersonDIA(mainGUI, true, personHandler, loginHandler);
        renewPersonDIA = new RenewPersonDIA(mainGUI, true, personHandler);
        editPersonDIA = new EditPersonDIA(mainGUI, true, personHandler, renewPersonDIA);
        removePersonDIA = new RemovePersonDIA(mainGUI, true, personHandler);
        addGuestDIA = new AddGuestDIA(mainGUI, true, personHandler);
        enrollPersonDIA = new EnrollPersonDIA(mainGUI, true, personHandler, addGuestDIA);
        resetUserPasswordDIA = new ResetUserPasswordDIA(mainGUI, true, userHandler);
        createUserDIA = new CreateUserDIA(mainGUI, true, userHandler);
        editUserDIA = new EditUserDIA(mainGUI, true, userHandler, resetUserPasswordDIA, loginHandler);
        removeUserDIA = new RemoveUserDIA(mainGUI, true, userHandler);
        mainGUI = new MainGUI(loginHandler, personHandler, createUserDIA, editUserDIA, removeUserDIA, createPersonDIA, editPersonDIA, removePersonDIA, renewPersonDIA, enrollPersonDIA, resetUserPasswordDIA);
        dch = new DateChangeDetecter(personHandler, mainGUI);
    }

    public static void main(String[] args) {
        try {
            HISM hism = new HISM();
            FileTool.createFolders();
            hism.userHandler.populateUsersFromDB();
            hism.personHandler.populatePersonsFromDB();
            hism.dch.start();
            hism.firstStart();
            JOptionPane.showMessageDialog(loginDIA, "LÆS, HVIS DU IKKE HAR LÆST.\nDa dette program er i beta test, kan der forekomme fejl.\nI tilfælde af fejl, eller der intet sker nå man trykker på en knap,\nskriv da dato og tidspunkt ned på et stykke papir og aflever til Patrick Kann i høne gruppen.", "Udgivelses info", JOptionPane.PLAIN_MESSAGE);
            hism.loginHandler.requestLoginView();
            mainGUI.setUser();
            mainGUI.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginDIA, "Der opstod en uventet fejl. Kontakt en administrator.\n"
                    + "Databasen er stadig intakt, så hvis programmet gik ned, skal du blot åbne det igen.\n"
                    + "Husk på, dette er beta software! Hvis du ikke rapportere fejlen, bliver den ikke rettet!\n"
                    + "Nå du rapportere fejlen, skal administratoren bruge dato og tidspunkt.", "Uventet fejl", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
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
            java.util.logging.Logger.getLogger(CreatePersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    private void firstStart() {
        //testData();
        if (userHandler.countAdmins() == 0) {
            JOptionPane.showMessageDialog(loginDIA, "Der er ingen administrator. Du skal nu lave en...", "Fejl", JOptionPane.ERROR_MESSAGE);
            createUserDIA.setCreateAdmin();
            createUserDIA.setVisible(true);
        }
    }

    private void testData() {
        for (int i = 0; i <= 200; i++) {
            personHandler.createPerson("test" + i, "test" + i, "test" + i, "test" + i, new ADate(12, 12, 2013), new ADate(15, 11, 2013), new File(""), new ADate(15, 9, 2013), false, false);
            System.out.println("create " + i);
        }
    }
}
