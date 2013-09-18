/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.person;

import control.person.PersonHandler;
import date.ADate;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Person;

/**
 *
 * @author patrick
 */
class MyCustomFilter3 extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".jpeg") || file.getAbsolutePath().endsWith(".jpg") || file.getAbsolutePath().endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Picture (.jpeg / .jpg / .png)";
    }
}

public class RenewPersonDIA extends javax.swing.JDialog {

    private Person selectedPerson;
    private boolean pictureChanged = false;
    private File picturePath;
    private PersonHandler personHandler;

    public RenewPersonDIA(java.awt.Frame parent, boolean modal, PersonHandler personHandler) {
        super(parent, modal);
        initComponents();
        this.personHandler = personHandler;
    }

    public void setXMode() {
        cancel_Button.setEnabled(false);
    }

    public void setPerson(Person p) {
        selectedPerson = p;
    }

    private void renew() {
        if (pictureChanged && !expiration_day_TextField.getText().isEmpty() && !expiration_month_TextField.getText().isEmpty() && !expiration_year_TextField.getText().isEmpty()) {
            Calendar expireDate = Calendar.getInstance();
            Calendar currentDate = Calendar.getInstance();
            expireDate.set(Integer.valueOf(expiration_year_TextField.getText()), (Integer.valueOf(expiration_month_TextField.getText()) - 1), Integer.valueOf(expiration_day_TextField.getText()));
            if (expireDate.after(currentDate)) {
                selectedPerson.setExpired(false);
                selectedPerson.setExpirationDate(new ADate(expiration_day_TextField.getText() + expiration_month_TextField.getText() + expiration_year_TextField.getText()));
                selectedPerson.setPicturePath(picturePath);
                personHandler.savePerson(selectedPerson, pictureChanged);
                JOptionPane.showMessageDialog(this, "Personen blev fornyet", "Fornyelse", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Udløbsdatoen kan ikke være før eller lig den nuværende dato", "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Alle felter skal udfyldes, og der skal vælges et nyt billed", "Fejl", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clean() {
        expiration_day_TextField.setText("");
        expiration_month_TextField.setText("");
        expiration_year_TextField.setText("");
        picturePane_PicturePane.setPicture(null);
        pictureChanged = false;
        picturePath = null;
        cancel_Button.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser_FileChooser = new javax.swing.JFileChooser();
        main_Pane = new javax.swing.JPanel();
        detail_Pane = new javax.swing.JPanel();
        expirationdateInfo_label = new javax.swing.JLabel();
        expiration_day_TextField = new javax.swing.JTextField();
        expiration_month_TextField = new javax.swing.JTextField();
        expiration_year_TextField = new javax.swing.JTextField();
        info_Label = new javax.swing.JLabel();
        picture_Pane = new javax.swing.JPanel();
        picturePane_PicturePane = new view.image.PicturePane();
        choose_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tool_Pane = new javax.swing.JPanel();
        renew_Button = new javax.swing.JButton();
        cancel_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(new java.awt.Color(51, 51, 51));

        expirationdateInfo_label.setText("Ny udløbsdato:");

        info_Label.setText("dd/mm/yyyy");

        javax.swing.GroupLayout detail_PaneLayout = new javax.swing.GroupLayout(detail_Pane);
        detail_Pane.setLayout(detail_PaneLayout);
        detail_PaneLayout.setHorizontalGroup(
            detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(expirationdateInfo_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expiration_day_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expiration_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expiration_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Label)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        detail_PaneLayout.setVerticalGroup(
            detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detail_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirationdateInfo_label)
                    .addComponent(expiration_day_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiration_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiration_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(info_Label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout picturePane_PicturePaneLayout = new javax.swing.GroupLayout(picturePane_PicturePane);
        picturePane_PicturePane.setLayout(picturePane_PicturePaneLayout);
        picturePane_PicturePaneLayout.setHorizontalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        picturePane_PicturePaneLayout.setVerticalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        choose_Button.setText("Vælg");
        choose_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_ButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Nyt billed");

        javax.swing.GroupLayout picture_PaneLayout = new javax.swing.GroupLayout(picture_Pane);
        picture_Pane.setLayout(picture_PaneLayout);
        picture_PaneLayout.setHorizontalGroup(
            picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picture_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choose_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        picture_PaneLayout.setVerticalGroup(
            picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picture_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(choose_Button)
                        .addComponent(jLabel1))
                    .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        renew_Button.setText("Forny");
        renew_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renew_ButtonActionPerformed(evt);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancel_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(renew_Button)
                .addContainerGap())
        );
        tool_PaneLayout.setVerticalGroup(
            tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tool_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tool_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renew_Button)
                    .addComponent(cancel_Button))
                .addContainerGap())
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detail_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(picture_Pane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tool_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detail_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(picture_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tool_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancel_ButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        clean();
    }//GEN-LAST:event_formWindowClosed

    private void choose_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_ButtonActionPerformed
        int returnVal = fileChooser_FileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            picturePath = fileChooser_FileChooser.getSelectedFile();
            try {
                picturePane_PicturePane.setPicture(ImageIO.read(picturePath));
                pictureChanged = true;
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_choose_ButtonActionPerformed

    private void renew_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renew_ButtonActionPerformed
        renew();
        dispose();
    }//GEN-LAST:event_renew_ButtonActionPerformed
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
//            java.util.logging.Logger.getLogger(RenewPersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RenewPersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RenewPersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RenewPersonDIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RenewPersonDIA dialog = new RenewPersonDIA(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton choose_Button;
    private javax.swing.JPanel detail_Pane;
    private javax.swing.JTextField expiration_day_TextField;
    private javax.swing.JTextField expiration_month_TextField;
    private javax.swing.JTextField expiration_year_TextField;
    private javax.swing.JLabel expirationdateInfo_label;
    private javax.swing.JFileChooser fileChooser_FileChooser;
    private javax.swing.JLabel info_Label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel main_Pane;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JPanel picture_Pane;
    private javax.swing.JButton renew_Button;
    private javax.swing.JPanel tool_Pane;
    // End of variables declaration//GEN-END:variables
}
