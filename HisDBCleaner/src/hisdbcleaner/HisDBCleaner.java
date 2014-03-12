/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hisdbcleaner;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author patrick
 */
public class HisDBCleaner {
    
    private final Handler handler;
    
    private final MainGUI mainGUI;
    
    public HisDBCleaner() {
        setLookAndFeel();
        
        handler = new Handler();
        
        mainGUI = new MainGUI(handler);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HisDBCleaner h = new HisDBCleaner();
        
        h.mainGUI.setVisible(true);
    }
    
}
