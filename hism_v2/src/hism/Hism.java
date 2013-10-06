/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hism;

import view.MainGUI;

/**
 *
 * @author patrick
 */
public class Hism {
    
    public static final String title = "Den Våde Høne - Indskrivnings system";
    public static final String version = "2.0 Alpha";

    public Hism() {
        
    }

    public static void main(String[] args) {

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
            
        } catch (InstantiationException ex) {
            
        } catch (IllegalAccessException ex) {
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            
        }
        //</editor-fold>
        MainGUI mg = new MainGUI();
        mg.setVisible(true);

    }
}
