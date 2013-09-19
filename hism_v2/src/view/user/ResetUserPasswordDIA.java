/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import control.user.UserHandler;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author patrick
 */
public class ResetUserPasswordDIA extends javax.swing.JDialog {

    private User selectedUser;
    private UserHandler userHandler;
    private boolean adminMode = false;

    public ResetUserPasswordDIA(java.awt.Frame parent, boolean modal, UserHandler userHandler) {
        super(parent, modal);
        initComponents();
        this.userHandler = userHandler;
    }

    public void setUser(User u) {
        this.selectedUser = u;
    }

    public void enableAdminMode() {
        currentPassword_TextField.setEnabled(false);
        adminMode = true;
    }

    private void clean() {
        currentPassword_TextField.setText("");
        newPassword_TextField.setText("");
        newPasswordAgain_TextField.setText("");
        currentPassword_TextField.setEnabled(true);
        adminMode = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_Pane = new javax.swing.JPanel();
        detail_Pane = new javax.swing.JPanel();
        currentPasswordInfo_Label = new javax.swing.JLabel();
        newPasswordInfo_Label = new javax.swing.JLabel();
        newPasswordAgainInfo_Label = new javax.swing.JLabel();
        currentPassword_TextField = new javax.swing.JTextField();
        newPassword_TextField = new javax.swing.JTextField();
        newPasswordAgain_TextField = new javax.swing.JTextField();
        tool_Pane = new javax.swing.JPanel();
        reset_Button = new javax.swing.JButton();
        cancel_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(new java.awt.Color(204, 0, 0));

        currentPasswordInfo_Label.setText("Nuværende kode:");

        newPasswordInfo_Label.setText("Ny kode:");

        newPasswordAgainInfo_Label.setText("Ny kode igen:");

        javax.swing.GroupLayout detail_PaneLayout = new javax.swing.GroupLayout(detail_Pane);
        detail_Pane.setLayout(detail_PaneLayout);
        detail_PaneLayout.setHorizontalGroup(
            detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentPasswordInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newPasswordInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newPasswordAgainInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentPassword_TextField)
                    .addComponent(newPassword_TextField)
                    .addComponent(newPasswordAgain_TextField))
                .addContainerGap())
        );
        detail_PaneLayout.setVerticalGroup(
            detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPasswordInfo_Label)
                    .addComponent(currentPassword_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordInfo_Label)
                    .addComponent(newPassword_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordAgainInfo_Label)
                    .addComponent(newPasswordAgain_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reset_Button.setText("Reset kode");
        reset_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_ButtonActionPerformed(evt);
            }
        });

        cancel_Button.setText("Annuller");
        cancel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tool_PaneLayout = new javax.swing.GroupLayout(tool_Pane);
        tool_Pane.setLayout(tool_PaneLayout);
        tool_PaneLayout.setHorizontalGroup(
            tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tool_PaneLayout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(cancel_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reset_Button)
                .addContainerGap())
        );
        tool_PaneLayout.setVerticalGroup(
            tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tool_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_Button)
                    .addComponent(cancel_Button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detail_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tool_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detail_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tool_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancel_ButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        clean();
    }//GEN-LAST:event_formWindowClosed

    private void reset_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_ButtonActionPerformed
        if (selectedUser != null) {
            if (!adminMode) {
                if (!currentPassword_TextField.getText().isEmpty() && !newPassword_TextField.getText().isEmpty() && !newPasswordAgain_TextField.getText().isEmpty()) {
                    if (selectedUser.getPassword().equals(currentPassword_TextField.getText())) {
                        if (newPassword_TextField.getText().equals(newPasswordAgain_TextField.getText())) {
                            selectedUser.setPassword(newPassword_TextField.getText());
                            userHandler.saveUser(selectedUser);
                            JOptionPane.showMessageDialog(this, "Done!", "Reset", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Koderne er ikke de samme", "Fejl", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Nuværende kode er forkert", "Fejl", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Alle felter skal udfyldes", "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (!newPassword_TextField.getText().isEmpty() && !newPasswordAgain_TextField.getText().isEmpty()) {
                    if (newPassword_TextField.getText().equals(newPasswordAgain_TextField.getText())) {
                        selectedUser.setPassword(newPassword_TextField.getText());
                        userHandler.saveUser(selectedUser);
                        JOptionPane.showMessageDialog(this, "Done!", "Reset", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Koderne er ikke de samme", "Fejl", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Alle felter skal udfyldes", "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "SELECTED USER HAS NOT BEEN SET. The developer is an idiot!", "FUCKING ERROR", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }//GEN-LAST:event_reset_ButtonActionPerformed
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
//            java.util.logging.Logger.getLogger(ResetPasswordDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ResetPasswordDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ResetPasswordDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ResetPasswordDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ResetPasswordDIA dialog = new ResetPasswordDIA(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cancel_Button;
    private javax.swing.JLabel currentPasswordInfo_Label;
    private javax.swing.JTextField currentPassword_TextField;
    private javax.swing.JPanel detail_Pane;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JLabel newPasswordAgainInfo_Label;
    private javax.swing.JTextField newPasswordAgain_TextField;
    private javax.swing.JLabel newPasswordInfo_Label;
    private javax.swing.JTextField newPassword_TextField;
    private javax.swing.JButton reset_Button;
    private javax.swing.JPanel tool_Pane;
    // End of variables declaration//GEN-END:variables
}
