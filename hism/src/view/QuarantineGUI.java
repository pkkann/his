package view;

import javax.swing.ButtonGroup;
import model.Person;

/**
 *
 * @author pkann
 */
public class QuarantineGUI extends javax.swing.JDialog {

    private Person p;
    private ButtonGroup bg;

    public QuarantineGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        createButtonGroup();
        reset();
        setLocationRelativeTo(null);
    }

    private void reset() {
        none_RadioButton.setSelected(true);
        always_RadioButton.setSelected(false);
        period_RadioButton.setSelected(false);
        period_TextField.setText("");
        period_TextField.setEnabled(false);
        p = null;
    }

    public void setPerson(Person p) {
        this.p = p;
        if (!p.isQuarantine()) {
            none_RadioButton.setSelected(true);
        } else if (p.isQuarantine() && p.getQuarantineToDate().isEmpty()) {
            always_RadioButton.setSelected(true);
        } else if (p.isQuarantine() && !p.getQuarantineToDate().isEmpty()) {
            period_RadioButton.setSelected(true);
            period_TextField.setText(p.getQuarantineToDate());
        }
    }

    private void createButtonGroup() {
        bg = new ButtonGroup();
        bg.add(none_RadioButton);
        bg.add(always_RadioButton);
        bg.add(period_RadioButton);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_Pane = new javax.swing.JPanel();
        app_Pane = new javax.swing.JPanel();
        always_RadioButton = new javax.swing.JRadioButton();
        period_RadioButton = new javax.swing.JRadioButton();
        tilDate_Label = new javax.swing.JLabel();
        period_TextField = new javax.swing.JTextField();
        save_Button = new javax.swing.JButton();
        none_RadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Karantæne");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(java.awt.Color.darkGray);

        always_RadioButton.setText("Altid");

        period_RadioButton.setText("Periode");
        period_RadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_RadioButtonItemStateChanged(evt);
            }
        });

        tilDate_Label.setText("Til dato:");

        save_Button.setText("Gem karantæne");
        save_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_ButtonActionPerformed(evt);
            }
        });

        none_RadioButton.setText("Ingen");

        javax.swing.GroupLayout app_PaneLayout = new javax.swing.GroupLayout(app_Pane);
        app_Pane.setLayout(app_PaneLayout);
        app_PaneLayout.setHorizontalGroup(
            app_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(app_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(app_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(app_PaneLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(tilDate_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(period_TextField))
                    .addGroup(app_PaneLayout.createSequentialGroup()
                        .addGroup(app_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(none_RadioButton)
                            .addComponent(always_RadioButton)
                            .addComponent(period_RadioButton))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, app_PaneLayout.createSequentialGroup()
                        .addGap(0, 226, Short.MAX_VALUE)
                        .addComponent(save_Button)))
                .addContainerGap())
        );
        app_PaneLayout.setVerticalGroup(
            app_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(app_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(none_RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(always_RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(period_RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(app_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tilDate_Label)
                    .addComponent(period_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(save_Button)
                .addContainerGap())
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(app_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(app_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        reset();
    }//GEN-LAST:event_formWindowClosed

    private void save_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_ButtonActionPerformed
        if (none_RadioButton.isSelected()) {
            p.setQuarantine(false);
            p.setQuarantineToDate("");
        } else if (always_RadioButton.isSelected()) {
            p.setQuarantine(true);
            p.setQuarantineToDate("");
        } else if (period_RadioButton.isSelected()) {
            p.setQuarantine(true);
            p.setQuarantineToDate(period_TextField.getText());
        }
        dispose();
    }//GEN-LAST:event_save_ButtonActionPerformed

    private void period_RadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_RadioButtonItemStateChanged
        if (period_RadioButton.isSelected()) {
            period_TextField.setEnabled(true);
        } else {
            period_TextField.setEnabled(false);
        }
    }//GEN-LAST:event_period_RadioButtonItemStateChanged
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QuarantineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QuarantineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QuarantineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QuarantineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                QuarantineGUI dialog = new QuarantineGUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton always_RadioButton;
    private javax.swing.JPanel app_Pane;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JRadioButton none_RadioButton;
    private javax.swing.JRadioButton period_RadioButton;
    private javax.swing.JTextField period_TextField;
    private javax.swing.JButton save_Button;
    private javax.swing.JLabel tilDate_Label;
    // End of variables declaration//GEN-END:variables
}
