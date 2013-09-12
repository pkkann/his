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
import model.User;
import view.*;
import view.person.CreatePersonDIA;
import view.user.CreateUserDIA;

/**
 *
 * @author patrick
 */
public class HISM {
    
    private DateChangeDetecter dch;

    private UserRegister userRegister;
    private UserHandler userHandler;
    private LoginHandler loginHandler;
    private static MainGUI mainGUI;
    private static LoginDIA loginDIA;
    private CreateUserDIA createUserDIA;
    private CreatePersonDIA createPersonDIA;
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
        createUserDIA = new CreateUserDIA(mainGUI, true, userHandler);
        mainGUI = new MainGUI(loginHandler, personHandler, createUserDIA, createPersonDIA);
        dch = new DateChangeDetecter(personHandler, mainGUI);
    }

    public static void main(String[] args) {
        HISM hism = new HISM();
        FileTool.createFolders();
        hism.userHandler.populateUsersFromDB();
        hism.personHandler.populatePersonsFromDB();
        hism.dch.start();
        hism.firstStart();
        hism.loginHandler.requestLoginView();
        mainGUI.setUser();
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
        if(userHandler.usersIsEmpty()) {
            JOptionPane.showMessageDialog(loginDIA, "Der er ingen administrator. Du skal nu lave en...", "Fejl", JOptionPane.ERROR_MESSAGE);
            createUserDIA.setCreateAdmin();
            createUserDIA.setVisible(true);
        }
    }
    
    private void testData() {
        userHandler.createUser("test@test.dk", "test", "test", "test", "test", new ADate(01, 01, 2013), true, false);
        personHandler.createPerson("Patrick", "", "Kann", "8-56", new ADate(21, 04, 1989), new ADate(11, 9, 2013), new File(""), new ADate(11, 9, 2013));
    }

}
