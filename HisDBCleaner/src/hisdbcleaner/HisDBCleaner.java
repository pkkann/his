/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hisdbcleaner;

import java.sql.Statement;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author patrick
 */
public class HisDBCleaner {

    private TaskHandler handler;

    private final MainGUI mainGUI;

    public HisDBCleaner() {
        setLookAndFeel();

        handler = new TaskHandler();

        mainGUI = new MainGUI(this);
    }

    public void cleanDB() {
        handler.createTask("Fix capitalisations", new Runnable() {

            @Override
            public void run() {
                Session s = hibernate.HibernateUtil.getSessionFactory().openSession();
                Transaction tx = s.beginTransaction();
                SQLQuery sql = s.createSQLQuery("UPDATE person SET firstname = CONCAT(UCASE(LEFT(firstname, 1)), LCASE(SUBSTRING(firstname, 2)));");
                SQLQuery sql1 = s.createSQLQuery("UPDATE person SET middlename = CONCAT(UCASE(LEFT(middlename, 1)), LCASE(SUBSTRING(middlename, 2)));");
                SQLQuery sql2 = s.createSQLQuery("UPDATE person SET lastname = CONCAT(UCASE(LEFT(lastname, 1)), LCASE(SUBSTRING(lastname, 2)));");
                SQLQuery sql3 = s.createSQLQuery("UPDATE person SET address = CONCAT(UCASE(LEFT(address, 1)), LCASE(SUBSTRING(address, 2)));");
                
                sql.executeUpdate();
                sql1.executeUpdate();
                sql2.executeUpdate();
                sql3.executeUpdate();
                tx.commit();
                s.close();
            }
        });

        handler.runTasks();
    }

    private void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            try {
                UIManager.setLookAndFeel(new WindowsLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
            }
        }

        //</editor-fold>
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HisDBCleaner h = new HisDBCleaner();

        h.mainGUI.setVisible(true);
    }

}
