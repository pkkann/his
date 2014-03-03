/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import configuration.PropertiesTool;
import control.EnrollmentHandler;
import control.LoginHandler;
import entity.Enrollment;
import entity.Guest;
import entity.Person;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.TableTool;
import view.message.DialogMessage;

/**
 *
 * @author patrick
 */
public class EnrollPersonDIA extends javax.swing.JDialog {

    private Person selectedPerson;
    private EnrollmentHandler enH;
    private CreateGuestDIA createGuestDIA;
    private AddGuestDIA addGuestDIA;
    private int selectedGuest = -1;

    public EnrollPersonDIA(java.awt.Frame parent, boolean modal, EnrollmentHandler enH, CreateGuestDIA createGuestDIA, AddGuestDIA addGuestDIA) {
        super(parent, modal);
        initComponents();
        initTableListener();
        this.enH = enH;
        this.createGuestDIA = createGuestDIA;
        this.addGuestDIA = addGuestDIA;
    }

    private void initTableListener() {
        guests_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && guests_Table.getSelectedRowCount() != 0) {
                    int selRow = guests_Table.getSelectedRow();
                    Object selIDObject = guests_Table.getModel().getValueAt(selRow, 0);
                    int selID = Integer.valueOf(String.valueOf(selIDObject));
                    setGuest(selID);
                }
            }
        });
    }

    private void setGuest(int idGuest) {
        cleanSelectedGuest();

        selectedGuest = idGuest;
        Guest g = enH.getGuest(selectedPerson.getIdPerson(), idGuest);

        removeGuest_Button.setEnabled(true);

        String picturePath = g.getPicturePath();
        if (picturePath.equals("N")) {
            Image icon = null;
            try {
                icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/billedid.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            picture_PicturePane.setPicture(icon, true);
        } else {
            try {
                Image img = ImageIO.read(new File(picturePath));
                picture_PicturePane.setPicture(img, true);
            } catch (IOException ex) {
                Logger.getLogger(EnrollPersonDIA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void cleanSelectedGuest() {
        selectedGuest = -1;
        picture_PicturePane.setPicture(null, true);
    }

    public void setPerson(Person p) {
        System.out.println("############Setting person");
        this.selectedPerson = p;
        int guestMax = 0;

        if (p.isHoene()) {
            guestMax = Integer.valueOf(PropertiesTool.getInstance().getProperty("maxguests_hoene"));
        } else {
            guestMax = Integer.valueOf(PropertiesTool.getInstance().getProperty("maxguests_persons"));
        }
        info_Label.setText("Denne person kan få " + guestMax + " gæster ind");

        if (enH.isEnrolled(p.getIdPerson())) {
            Enrollment en = enH.getEnrollment(p.getIdPerson());

            // Get guests
            ArrayList<String[]> data = new ArrayList<>();
            Set<Guest> guests = en.getGuests();
            for (Guest g : guests) {
                String[] ss = {String.valueOf(g.getIdGuest()), g.getFirstname() + " " + g.getMiddlename() + " " + g.getLastname(), g.getBirthdayDate(), g.getCreationDate()};
                data.add(ss);
            }
            DefaultTableModel dtm = TableTool.createGuestTableModel(data);
            guests_Table.setModel(dtm);

//            if (dtm.getRowCount() > 0) {
//                deleteGuest_Button.setEnabled(true);
//            } else {
//                deleteGuest_Button.setEnabled(false);
//            }
            if (dtm.getRowCount() >= guestMax) {
                addGuest_Button.setEnabled(false);
            } else {
                addGuest_Button.setEnabled(true);
            }

            enroll_Button.setText("Slet indskrivning");
        } else {
            enroll_Button.setText("Indskriv");
            addGuest_Button.setEnabled(false);
            removeGuest_Button.setEnabled(false);
        }
    }

    private void setButtons() {
        if (enH.isEnrolled(selectedPerson.getIdPerson())) {
            int guestMax = 0;

            if (selectedPerson.isHoene()) {
                guestMax = Integer.valueOf(PropertiesTool.getInstance().getProperty("maxguests_hoene"));
            } else {
                guestMax = Integer.valueOf(PropertiesTool.getInstance().getProperty("maxguests_persons"));
            }
            removeGuest_Button.setEnabled(false);

            if (guests_Table.getModel().getRowCount() >= guestMax) {
                addGuest_Button.setEnabled(false);
            } else {
                addGuest_Button.setEnabled(true);
            }
        }
    }

    private void clean() {
        DefaultTableModel dtm = TableTool.createEmptyGuestTableModel();
        guests_Table.setModel(dtm);

        removeGuest_Button.setEnabled(false);
        addGuest_Button.setEnabled(true);
        enroll_Button.setText("Indskriv");
        info_Label.setText("INFO");
        picture_PicturePane.setPicture(null, true);
    }

    private void cleanGuests() {
        DefaultTableModel dtm = TableTool.createEmptyGuestTableModel();
        guests_Table.setModel(dtm);

        removeGuest_Button.setEnabled(false);
        addGuest_Button.setEnabled(false);
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
        title_Pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fields_Pane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        guests_Table = new javax.swing.JTable();
        addGuest_Button = new javax.swing.JButton();
        picture_PicturePane = new view.image.PicturePane();
        info_Label = new javax.swing.JLabel();
        removeGuest_Button = new javax.swing.JButton();
        tools_Pane = new javax.swing.JPanel();
        close_Button = new javax.swing.JButton();
        enroll_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(new java.awt.Color(51, 51, 51));

        title_Pane.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Indskrivning");

        javax.swing.GroupLayout title_PaneLayout = new javax.swing.GroupLayout(title_Pane);
        title_Pane.setLayout(title_PaneLayout);
        title_PaneLayout.setHorizontalGroup(
            title_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        title_PaneLayout.setVerticalGroup(
            title_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        guests_Table.setAutoCreateRowSorter(true);
        guests_Table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        guests_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Navn", "Fødselsdag", "Oprettelsesdato"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(guests_Table);

        addGuest_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addGuest_Button.setText("Tilføj gæst");
        addGuest_Button.setEnabled(false);
        addGuest_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGuest_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout picture_PicturePaneLayout = new javax.swing.GroupLayout(picture_PicturePane);
        picture_PicturePane.setLayout(picture_PicturePaneLayout);
        picture_PicturePaneLayout.setHorizontalGroup(
            picture_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        picture_PicturePaneLayout.setVerticalGroup(
            picture_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        info_Label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        info_Label.setForeground(new java.awt.Color(51, 153, 0));
        info_Label.setText("INFO");

        removeGuest_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        removeGuest_Button.setText("Fjern gæst");
        removeGuest_Button.setEnabled(false);
        removeGuest_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGuest_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fields_PaneLayout = new javax.swing.GroupLayout(fields_Pane);
        fields_Pane.setLayout(fields_PaneLayout);
        fields_PaneLayout.setHorizontalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addComponent(addGuest_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeGuest_Button)
                        .addGap(20, 20, 20)
                        .addComponent(info_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(picture_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fields_PaneLayout.setVerticalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fields_PaneLayout.createSequentialGroup()
                                .addComponent(addGuest_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(info_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeGuest_Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(picture_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        close_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        close_Button.setText("Luk");
        close_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_ButtonActionPerformed(evt);
            }
        });

        enroll_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        enroll_Button.setText("Indskriv");
        enroll_Button.setToolTipText("");
        enroll_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enroll_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tools_PaneLayout = new javax.swing.GroupLayout(tools_Pane);
        tools_Pane.setLayout(tools_PaneLayout);
        tools_PaneLayout.setHorizontalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close_Button)
                .addGap(5, 5, 5)
                .addComponent(enroll_Button)
                .addContainerGap())
        );
        tools_PaneLayout.setVerticalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close_Button)
                    .addComponent(enroll_Button))
                .addContainerGap())
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fields_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tools_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fields_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tools_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void close_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_close_ButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        clean();
    }//GEN-LAST:event_formWindowClosed

    private void addGuest_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGuest_ButtonActionPerformed
        Enrollment en = enH.getEnrollment(selectedPerson.getIdPerson());
        createGuestDIA.setIdEnrollment(en.getIdEnrollment());
        createGuestDIA.setIdPerson(selectedPerson.getIdPerson());
        createGuestDIA.setVisible(true);
        setPerson(selectedPerson);
        setButtons();

//        if (enH.isEnrolled(selectedPerson.getIdPerson())) {
//            addGuestDIA.setIdEnrollment(enH.getEnrollment(selectedPerson.getIdPerson()).getIdEnrollment());
//            addGuestDIA.setVisible(true);
//        }
    }//GEN-LAST:event_addGuest_ButtonActionPerformed

    private void removeGuest_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGuest_ButtonActionPerformed
        int n = DialogMessage.showQuestionMessage(new JDialog(), "Er du sikker på du vil slette denne gæst?", "Sikker?");
        if (n == 0) {
            enH.removeGuest(selectedPerson.getIdPerson(), selectedGuest);
            cleanSelectedGuest();
            setPerson(selectedPerson);
            setButtons();
        }
    }//GEN-LAST:event_removeGuest_ButtonActionPerformed

    private void enroll_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enroll_ButtonActionPerformed
        if (enH.isEnrolled(selectedPerson.getIdPerson())) {
            int n = DialogMessage.showQuestionMessage(this, "Er du sikker på du vil slette indskrivningen?\nDette anses ikke for en normal handling, og bør undgås på en vagt!", "Sikker?");
            if (n == 0) {
                enroll_Button.setText("Indskriv");
                enH.removeEnrollment(selectedPerson.getIdPerson());
                cleanGuests();
            }
        } else {
            enroll_Button.setText("Slet indskrivning");
            enH.createEnrollment(selectedPerson.getIdPerson(), LoginHandler.loggedIn.getIduser());
            setButtons();
        }
    }//GEN-LAST:event_enroll_ButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        setPerson(selectedPerson);
        setButtons();
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGuest_Button;
    private javax.swing.JButton close_Button;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JPanel fields_Pane;
    private javax.swing.JTable guests_Table;
    private javax.swing.JLabel info_Label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_Pane;
    private view.image.PicturePane picture_PicturePane;
    private javax.swing.JButton removeGuest_Button;
    private javax.swing.JPanel title_Pane;
    private javax.swing.JPanel tools_Pane;
    // End of variables declaration//GEN-END:variables
}
