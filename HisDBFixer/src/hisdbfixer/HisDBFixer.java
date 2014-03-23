/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hisdbfixer;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import db.PersonDAO;
import entity.Person;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author patrick
 */
public class HisDBFixer {

    private TaskHandler handler;

    private final MainGUI mainGUI;

    public HisDBFixer() {
        setLookAndFeel();

        handler = new TaskHandler();

        mainGUI = new MainGUI(this);
    }

    public void cleanDB() {
        handler.createTask("Fixer store og små bukstaver", new Runnable() {

            @Override
            public void run() {
                ArrayList<String> sqls = new ArrayList<>();
                sqls.add("UPDATE person SET firstname = CONCAT(UCASE(LEFT(firstname, 1)), LCASE(SUBSTRING(firstname, 2)));");
                sqls.add("UPDATE person SET middlename = CONCAT(UCASE(LEFT(middlename, 1)), LCASE(SUBSTRING(middlename, 2)));");
                sqls.add("UPDATE person SET lastname = CONCAT(UCASE(LEFT(lastname, 1)), LCASE(SUBSTRING(lastname, 2)));");
                sqls.add("UPDATE person SET address = CONCAT(UCASE(LEFT(address, 1)), LCASE(SUBSTRING(address, 2)));");
                PersonDAO.runSQLScriptBatch(sqls);
            }
        });
        
        handler.createTask("Fixer gentagelser", new Runnable() {

            @Override
            public void run() {
                ArrayList<Person> persons = PersonDAO.getDublicatePersons();
                System.out.println("  " + persons.size() + " duplications");
                if (mainGUI.getDup()) {
                    if (!persons.isEmpty()) {
                        System.out.println("  Removing duplications");
                        Iterator<Person> i = persons.iterator();
                        PersonDAO.deletePersons(i);
                    } else {
                        System.out.println("  No duplications to remove");
                    }
                }
            }
        });

        System.out.println(handler.getTaskCount() + " task(s) to run");
        System.out.println("==========================");
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
        HisDBFixer h = new HisDBFixer();

        h.mainGUI.setVisible(true);
    }

}
