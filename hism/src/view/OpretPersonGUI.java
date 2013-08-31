package view;

import control.PersonHandler;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import session.LoginHandler;

/**
 *
 * @author pkann
 */
class MyCustomFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".jpeg") || file.getAbsolutePath().endsWith(".jpg") || file.getAbsolutePath().endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Picture (.jpeg / .jpg / .png)";
    }
}

public class OpretPersonGUI extends javax.swing.JDialog {

    private PersonHandler personHandler;
    private LoginHandler loginHandler;
    private File billedPath;
    private Image billed;

    public OpretPersonGUI(java.awt.Frame parent, boolean modal, PersonHandler personHandler, LoginHandler loginHandler) {
        super(parent, modal);
        this.personHandler = personHandler;
        this.loginHandler = loginHandler;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void enableHone(boolean enable) {
        email_TextField.setEnabled(enable);
        password_TextField.setEnabled(enable);
        admin_CheckBox.setEnabled(enable);
        reserve_CheckBox.setEnabled(enable);
    }

    private void reset() {
        name_TextField.setText("");
        address_TextField.setText("");
        birthday_TextField.setText("");
        picturePane_Pane.setPicture(null);
        oneOne_CheckBox.getModel().setSelected(false);
        hone_CheckBox.getModel().setSelected(false);
        email_TextField.setText("");
        password_TextField.setText("");
        admin_CheckBox.getModel().setSelected(false);
        reserve_CheckBox.getModel().setSelected(false);
        billed = null;
        billedPath = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser_FileChooser = new javax.swing.JFileChooser();
        main_Pane = new javax.swing.JPanel();
        person_Pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name_TextField = new javax.swing.JTextField();
        address_TextField = new javax.swing.JTextField();
        birthday_TextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        picturePane_Pane = new view.PicturePane();
        choose_Button = new javax.swing.JButton();
        oneOne_CheckBox = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        notAdmin2_Label = new javax.swing.JLabel();
        hone_Pane = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        hone_CheckBox = new javax.swing.JCheckBox();
        email_TextField = new javax.swing.JTextField();
        password_TextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        admin_CheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        reserve_CheckBox = new javax.swing.JCheckBox();
        notAdmin_Label = new javax.swing.JLabel();
        tool_Pane = new javax.swing.JPanel();
        opret_Button = new javax.swing.JButton();
        annuller_Button = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        expireDate_TextField = new javax.swing.JTextField();

        fileChooser_FileChooser.setDialogTitle("Vælg billed");
        fileChooser_FileChooser.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(java.awt.Color.darkGray);

        jLabel1.setText("Navn:");

        jLabel2.setText("Adresse:");

        jLabel3.setText("Fødselsdag:");

        jLabel4.setText("Billed:");

        javax.swing.GroupLayout picturePane_PaneLayout = new javax.swing.GroupLayout(picturePane_Pane);
        picturePane_Pane.setLayout(picturePane_PaneLayout);
        picturePane_PaneLayout.setHorizontalGroup(
            picturePane_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        picturePane_PaneLayout.setVerticalGroup(
            picturePane_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        choose_Button.setText("Vælg");
        choose_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_ButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("1-1:");

        notAdmin2_Label.setForeground(new java.awt.Color(170, 48, 48));
        notAdmin2_Label.setText("Du er ikke administrator, og kan ikke sætte 1-1");

        javax.swing.GroupLayout person_PaneLayout = new javax.swing.GroupLayout(person_Pane);
        person_Pane.setLayout(person_PaneLayout);
        person_PaneLayout.setHorizontalGroup(
            person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(person_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name_TextField)
                    .addComponent(address_TextField)
                    .addComponent(birthday_TextField)
                    .addGroup(person_PaneLayout.createSequentialGroup()
                        .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(person_PaneLayout.createSequentialGroup()
                                .addComponent(oneOne_CheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notAdmin2_Label))
                            .addGroup(person_PaneLayout.createSequentialGroup()
                                .addComponent(picturePane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choose_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        person_PaneLayout.setVerticalGroup(
            person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(person_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(birthday_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(picturePane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choose_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(person_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oneOne_CheckBox)
                        .addComponent(jLabel7))
                    .addComponent(notAdmin2_Label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("Høne:");

        jLabel9.setText("E-mail:");

        jLabel10.setText("Kode:");

        hone_CheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hone_CheckBoxStateChanged(evt);
            }
        });

        email_TextField.setEnabled(false);

        password_TextField.setEnabled(false);

        jLabel5.setText("Admin:");

        admin_CheckBox.setEnabled(false);

        jLabel6.setText("Reserve:");

        reserve_CheckBox.setEnabled(false);

        notAdmin_Label.setForeground(new java.awt.Color(170, 48, 48));
        notAdmin_Label.setText("Du er ikke administrator, og kan ikke oprette en høne");

        javax.swing.GroupLayout hone_PaneLayout = new javax.swing.GroupLayout(hone_Pane);
        hone_Pane.setLayout(hone_PaneLayout);
        hone_PaneLayout.setHorizontalGroup(
            hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hone_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email_TextField)
                    .addComponent(password_TextField)
                    .addGroup(hone_PaneLayout.createSequentialGroup()
                        .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(admin_CheckBox)
                            .addComponent(reserve_CheckBox)
                            .addGroup(hone_PaneLayout.createSequentialGroup()
                                .addComponent(hone_CheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notAdmin_Label)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        hone_PaneLayout.setVerticalGroup(
            hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hone_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(hone_CheckBox))
                    .addComponent(notAdmin_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(email_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(password_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(admin_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hone_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(reserve_CheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        opret_Button.setText("Opret");
        opret_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opret_ButtonActionPerformed(evt);
            }
        });

        annuller_Button.setText("Annuller");
        annuller_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annuller_ButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Udløbsdato:");

        javax.swing.GroupLayout tool_PaneLayout = new javax.swing.GroupLayout(tool_Pane);
        tool_Pane.setLayout(tool_PaneLayout);
        tool_PaneLayout.setHorizontalGroup(
            tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tool_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expireDate_TextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annuller_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opret_Button)
                .addContainerGap())
        );
        tool_PaneLayout.setVerticalGroup(
            tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tool_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(opret_Button)
                        .addComponent(annuller_Button))
                    .addGroup(tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(expireDate_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(person_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hone_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tool_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(person_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hone_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(main_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annuller_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuller_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_annuller_ButtonActionPerformed

    private void hone_CheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hone_CheckBoxStateChanged
        if (hone_CheckBox.getModel().isSelected()) {
            enableHone(true);
        } else {
            enableHone(false);
        }
    }//GEN-LAST:event_hone_CheckBoxStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        reset();
    }//GEN-LAST:event_formWindowClosed

    private void opret_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opret_ButtonActionPerformed
        String name = name_TextField.getText();
        String address = address_TextField.getText();
        String birthday = birthday_TextField.getText();
        boolean oneOne = oneOne_CheckBox.getModel().isSelected();
        String expireDate = expireDate_TextField.getText();

        if (hone_CheckBox.getModel().isSelected()) {
            String username = email_TextField.getText();
            String password = password_TextField.getText();
            boolean admin = admin_CheckBox.getModel().isSelected();
            boolean reserve = reserve_CheckBox.getModel().isSelected();

            if (!name.isEmpty() && !address.isEmpty() && !birthday.isEmpty() && !username.isEmpty() && !password.isEmpty()) {

                if (personHandler.createHone(name, address, birthday, expireDate, billedPath, username, password, admin, reserve) == 1) {
                    System.out.println("Brugernavnet er allerede i brug");
                } else {
                    dispose();
                }
            } else {
                System.out.println("Udfyld alle felter");
            }
        } else {
            if (!name.isEmpty() && !address.isEmpty() && !birthday.isEmpty()) {
                personHandler.createPerson(name, address, birthday, expireDate, billedPath);
                dispose();
            } else {
                System.out.println("Udfyld alle felter");
            }
        }

    }//GEN-LAST:event_opret_ButtonActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (loginHandler.isAdmin()) {
            hone_CheckBox.setEnabled(true);
            notAdmin_Label.setVisible(false);

            oneOne_CheckBox.setEnabled(true);
            notAdmin2_Label.setVisible(false);
        } else {
            hone_CheckBox.setEnabled(false);
            notAdmin_Label.setVisible(true);

            oneOne_CheckBox.setEnabled(false);
            notAdmin2_Label.setVisible(true);
        }
    }//GEN-LAST:event_formWindowActivated

    private void choose_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_ButtonActionPerformed
        int returnVal = fileChooser_FileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            billedPath = fileChooser_FileChooser.getSelectedFile();
            try {
                billed = ImageIO.read(billedPath);
                picturePane_Pane.setPicture(billed);
            } catch (IOException ex) {
                Logger.getLogger(OpretPersonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_choose_ButtonActionPerformed
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
//            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OpretPersonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                OpretPersonGUI dialog = new OpretPersonGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField address_TextField;
    private javax.swing.JCheckBox admin_CheckBox;
    private javax.swing.JButton annuller_Button;
    private javax.swing.JTextField birthday_TextField;
    private javax.swing.JButton choose_Button;
    private javax.swing.JTextField email_TextField;
    private javax.swing.JTextField expireDate_TextField;
    private javax.swing.JFileChooser fileChooser_FileChooser;
    private javax.swing.JCheckBox hone_CheckBox;
    private javax.swing.JPanel hone_Pane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JTextField name_TextField;
    private javax.swing.JLabel notAdmin2_Label;
    private javax.swing.JLabel notAdmin_Label;
    private javax.swing.JCheckBox oneOne_CheckBox;
    private javax.swing.JButton opret_Button;
    private javax.swing.JTextField password_TextField;
    private javax.swing.JPanel person_Pane;
    private view.PicturePane picturePane_Pane;
    private javax.swing.JCheckBox reserve_CheckBox;
    private javax.swing.JPanel tool_Pane;
    // End of variables declaration//GEN-END:variables
}
