/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author patrick
 */
public class TableTool {

    private TableTool() {
    }

    /**
     * Remove all elements in a tablemodel
     * @param tm
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel cleanTableModel(TableModel tm) {
        DefaultTableModel dtm = (DefaultTableModel) tm;
        dtm.getDataVector().removeAllElements();
        dtm.fireTableDataChanged();
        return dtm;
    }
}
