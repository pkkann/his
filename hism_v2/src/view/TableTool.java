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

    private TableTool() {
    }

    public static DefaultTableModel generateTableModel(String[] columns, ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        if (columns.length != 0 && data != null) {
            // Add columns
            for (String s : columns) {
                dtm.addColumn(s);
            }
            
            // Add data
            for (String[] ss : data) {
                dtm.addRow(ss);
            }
            
            return dtm;
        }
        
        return null;
    }
}
