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

    private TableTool() {
    }
    
    public static DefaultTableModel generateEmtpyUserTableModel() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }
        
        return dtm;
    }

    /**
     * Remove all elements in a tablemodel
     * @param tm
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel generateUserTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for(int i = 0; i<userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }
        
        for(String[] s : data) {
            dtm.addRow(s);
        }
        
        return dtm;
    }

    
}
