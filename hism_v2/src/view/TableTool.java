/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patrick
 */
public class TableTool {
    
    public static final String[] userColumns = {"ID", "Navn", "Brugernavn", "Administrator", "Reserve"};
    public static final String[] personColumns = {"ID", "Navn", "Adresse", "Fødselsdag", "Udløbsdato", "Oprettelsesdato", "Høne", "Reserve", "1-1"};
    public static final String[] guestColumns = {};

    private TableTool() {
    }
    
    /**
     * Creates an empty table model based on user
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmtpyUserTableModel() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }
        
        return dtm;
    }
    
    /**
     * Creates an empty table model based on person
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmptyPersonTableModel() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<personColumns.length; i++) {
            dtm.addColumn(personColumns[i]);
        }
        
        return dtm;
    }
    
    /**
     * Creates an empty table model based on guest
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmptyGuestTableModel() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<guestColumns.length; i++) {
            dtm.addColumn(guestColumns[i]);
        }
        
        return dtm;
    }

    /**
     * Creates a table model based on user
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createUserTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }
        
        for(String[] s : data) {
            dtm.addRow(s);
        }
        
        return dtm;
    }
    
    /**
     * Creates a table model based on person
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createPersonTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<personColumns.length; i++) {
            dtm.addColumn(personColumns[i]);
        }
        
        for(String[] s : data) {
            dtm.addRow(s);
        }
        
        return dtm;
    }
    
    /**
     * Creates a table model based on guest
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createGuestTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<guestColumns.length; i++) {
            dtm.addColumn(guestColumns[i]);
        }
        
        for(String[] s : data) {
            dtm.addRow(s);
        }
        
        return dtm;
    }

    
}
