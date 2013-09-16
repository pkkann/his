/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.person.PersonHandler;
import date.ADate;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import login.LoginHandler;
import model.Person;
import view.person.CreatePersonDIA;
import view.person.RemovePersonDIA;
import view.user.CreateUserDIA;
import view.user.RemoveUserDIA;

/**
 *
 * @author patrick
 */
public class MainGUI extends javax.swing.JFrame {

    private LoginHandler loginHandler;
    private PersonHandler personHandler;
    private CreateUserDIA createUserDIA;
    private RemoveUserDIA removeUserDIA;
    private CreatePersonDIA createPersonDIA;
    private RemovePersonDIA removePersonDIA;
    private Person selectedPerson;

    public MainGUI(LoginHandler loginHandler, PersonHandler personHandler, CreateUserDIA createUserDIA, RemoveUserDIA removeUserDIA, CreatePersonDIA createPersonDIA, RemovePersonDIA removePersonDIA) {
        initComponents();
        this.loginHandler = loginHandler;
        this.personHandler = personHandler;
        this.createUserDIA = createUserDIA;
        this.removeUserDIA = removeUserDIA;
        this.createPersonDIA = createPersonDIA;
        this.removePersonDIA = removePersonDIA;
    }

    public void setDate(ADate date) {
        date_Label.setText(date.getDay() + "/" + (date.getMonth() + 1) + "/" + date.getYear());
    }

    private void setPerson(Person p) {
        if (p != null) {
            selectedPerson = p;
            firstname_TextField.setText(p.getFirstname());
            middlename_TextField.setText(p.getMiddlename());
            lastname_TextField.setText(p.getLastname());
            birthday_TextField.setText(ADate.formatADate(p.getBirthdayDate(), "/"));
            expirationDate_TextField.setText(ADate.formatADate(p.getExpirationDate(), "/"));
            if (p.isExpired()) {
                expirationDate_TextField.setForeground(Color.white);
                expirationDate_TextField.setBackground(Color.red);
            } else {
                expirationDate_TextField.setForeground(Color.black);
                expirationDate_TextField.setBackground(new Color(240, 240, 240));
            }
            if (p.isOneOne()) {
                oneOne_TextField.setText("Ja");
                oneOne_TextField.setBackground(Color.cyan);
            } else {
                oneOne_TextField.setText("Nej");
                oneOne_TextField.setBackground(new Color(240, 240, 240));
            }
            address_TextField.setText(p.getAddress());
            try {
                picturePane_PicturePane.setPicture(ImageIO.read(p.getPicturePath()));
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (p.isQuarantine()) {
                statusPicPane_StatusPicPane.setStatus(1);
            } else if (p.isExpired()) {
                statusPicPane_StatusPicPane.setStatus(3);
            } else {
                statusPicPane_StatusPicPane.setStatus(0);
            }

        }
    }

    private void clearPerson() {
        selectedPerson = null;
        firstname_TextField.setText("");
        middlename_TextField.setText("");
        lastname_TextField.setText("");
        birthday_TextField.setText("");
        expirationDate_TextField.setText("");
        expirationDate_TextField.setForeground(Color.black);
        expirationDate_TextField.setBackground(new Color(240, 240, 240));
        oneOne_TextField.setText("");
        address_TextField.setText("");
        picturePane_PicturePane.setPicture(null);
        statusPicPane_StatusPicPane.setStatus(2);
    }

    private void clearResult() {
        try {
            DefaultListModel dlm = new DefaultListModel();
            result_List.setModel(dlm);
        } catch (NullPointerException ex) {
        }
    }

    public void setUser() {
        user_Label.setText(loginHandler.getLoggedInUser().getFirstname() + " " + loginHandler.getLoggedInUser().getLastname());
        if (loginHandler.getLoggedInUser().isAdministrator()) {
            editPerson_MenuItem.setEnabled(true);
            deletePerson_MenuItem.setEnabled(true);

            createUser_MenuItem.setEnabled(true);
            editUser_MenuItem.setEnabled(true);
            deleteUser_MenuItem.setEnabled(true);

            administrator_Label.setText("Ja");
        } else {
            editPerson_MenuItem.setEnabled(false);
            deletePerson_MenuItem.setEnabled(false);

            createUser_MenuItem.setEnabled(false);
            editUser_MenuItem.setEnabled(false);
            deleteUser_MenuItem.setEnabled(false);

            administrator_Label.setText("Nej");
        }

        if (loginHandler.getLoggedInUser().isReserve()) {
            reserve_Label.setText("Ja");
        } else {
            reserve_Label.setText("Nej");
        }
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
        result_Pane = new javax.swing.JPanel();
        result_ScrollPane = new javax.swing.JScrollPane();
        result_List = new javax.swing.JList();
        details_Pane = new javax.swing.JPanel();
        picturePane_PicturePane = new view.image.PicturePane();
        statusPicPane_StatusPicPane = new view.image.StatusPicPane();
        firstnameInfo_Label = new javax.swing.JLabel();
        middlenameInfo_Label = new javax.swing.JLabel();
        lastnameInfo_Label = new javax.swing.JLabel();
        birthdayInfo_Label = new javax.swing.JLabel();
        expirationDateInfo_Label = new javax.swing.JLabel();
        firstname_TextField = new javax.swing.JTextField();
        middlename_TextField = new javax.swing.JTextField();
        lastname_TextField = new javax.swing.JTextField();
        birthday_TextField = new javax.swing.JTextField();
        expirationDate_TextField = new javax.swing.JTextField();
        oneOneInfo_Label = new javax.swing.JLabel();
        oneOne_TextField = new javax.swing.JTextField();
        enroll_Button = new javax.swing.JButton();
        requestQuarantine_Button = new javax.swing.JButton();
        kick_Button = new javax.swing.JButton();
        addressInfo_Label = new javax.swing.JLabel();
        address_TextField = new javax.swing.JTextField();
        renew_Button = new javax.swing.JButton();
        search_TextField = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        bottom_Pane = new javax.swing.JPanel();
        dateInfo_Label = new javax.swing.JLabel();
        date_Label = new javax.swing.JLabel();
        userInfo_Label = new javax.swing.JLabel();
        user_Label = new javax.swing.JLabel();
        enrolledInfo_Label = new javax.swing.JLabel();
        enrolled_Label = new javax.swing.JLabel();
        administratorInfo_Label = new javax.swing.JLabel();
        administrator_Label = new javax.swing.JLabel();
        reserveInfo_Label = new javax.swing.JLabel();
        reserve_Label = new javax.swing.JLabel();
        menuBar_MenuBar = new javax.swing.JMenuBar();
        file_Menu = new javax.swing.JMenu();
        repport_MenuItem = new javax.swing.JMenuItem();
        reset_MenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        changeUser_MenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        releaseNotes_MenuItem = new javax.swing.JMenuItem();
        administration_Menu = new javax.swing.JMenu();
        persons_Menu = new javax.swing.JMenu();
        createPerson_MenuItem = new javax.swing.JMenuItem();
        editPerson_MenuItem = new javax.swing.JMenuItem();
        deletePerson_MenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        oneTimeEnroll_MenuItem = new javax.swing.JMenuItem();
        users_Menu = new javax.swing.JMenu();
        createUser_MenuItem = new javax.swing.JMenuItem();
        editUser_MenuItem = new javax.swing.JMenuItem();
        deleteUser_MenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        result_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultat"));

        result_List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        result_List.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                result_ListValueChanged(evt);
            }
        });
        result_ScrollPane.setViewportView(result_List);

        javax.swing.GroupLayout result_PaneLayout = new javax.swing.GroupLayout(result_Pane);
        result_Pane.setLayout(result_PaneLayout);
        result_PaneLayout.setHorizontalGroup(
            result_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(result_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(result_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
        result_PaneLayout.setVerticalGroup(
            result_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(result_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(result_ScrollPane)
                .addContainerGap())
        );

        details_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Detaljer"));

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

        javax.swing.GroupLayout statusPicPane_StatusPicPaneLayout = new javax.swing.GroupLayout(statusPicPane_StatusPicPane);
        statusPicPane_StatusPicPane.setLayout(statusPicPane_StatusPicPaneLayout);
        statusPicPane_StatusPicPaneLayout.setHorizontalGroup(
            statusPicPane_StatusPicPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        statusPicPane_StatusPicPaneLayout.setVerticalGroup(
            statusPicPane_StatusPicPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        firstnameInfo_Label.setText("Fornavn:");

        middlenameInfo_Label.setText("Mellemnavn:");

        lastnameInfo_Label.setText("Efternavn:");

        birthdayInfo_Label.setText("Fødselsdag:");

        expirationDateInfo_Label.setText("Udløbsdato:");

        firstname_TextField.setEditable(false);

        middlename_TextField.setEditable(false);

        lastname_TextField.setEditable(false);

        birthday_TextField.setEditable(false);

        expirationDate_TextField.setEditable(false);

        oneOneInfo_Label.setText("1-1:");

        oneOne_TextField.setEditable(false);

        enroll_Button.setText("Indskriv");

        requestQuarantine_Button.setText("Anmod om karantæne");

        kick_Button.setText("Smid ud");

        addressInfo_Label.setText("Adresse:");

        address_TextField.setEditable(false);

        renew_Button.setText("Forny");
        renew_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renew_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout details_PaneLayout = new javax.swing.GroupLayout(details_Pane);
        details_Pane.setLayout(details_PaneLayout);
        details_PaneLayout.setHorizontalGroup(
            details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(details_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addComponent(enroll_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(renew_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kick_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(requestQuarantine_Button))
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(middlenameInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstnameInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lastnameInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(birthdayInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expirationDateInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(oneOneInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstname_TextField)
                            .addComponent(middlename_TextField)
                            .addComponent(lastname_TextField)
                            .addComponent(birthday_TextField)
                            .addComponent(expirationDate_TextField)
                            .addComponent(oneOne_TextField)
                            .addComponent(address_TextField))
                        .addGap(18, 18, 18)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusPicPane_StatusPicPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        details_PaneLayout.setVerticalGroup(
            details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(details_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusPicPane_StatusPicPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstnameInfo_Label)
                            .addComponent(firstname_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(middlenameInfo_Label)
                            .addComponent(middlename_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastnameInfo_Label)
                            .addComponent(lastname_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birthdayInfo_Label)
                            .addComponent(birthday_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expirationDateInfo_Label)
                            .addComponent(expirationDate_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oneOne_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oneOneInfo_Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressInfo_Label)
                            .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enroll_Button)
                    .addComponent(requestQuarantine_Button)
                    .addComponent(kick_Button)
                    .addComponent(renew_Button))
                .addContainerGap())
        );

        search_Button.setText("Søg");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_PaneLayout.createSequentialGroup()
                        .addComponent(search_TextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button))
                    .addGroup(main_PaneLayout.createSequentialGroup()
                        .addComponent(details_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(result_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(result_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(details_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bottom_Pane.setBackground(new java.awt.Color(51, 51, 51));

        dateInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dateInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        dateInfo_Label.setText("Dato:");

        date_Label.setForeground(new java.awt.Color(102, 153, 0));
        date_Label.setText("DATE");

        userInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        userInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        userInfo_Label.setText("Bruger:");

        user_Label.setForeground(new java.awt.Color(102, 153, 0));
        user_Label.setText("USER");

        enrolledInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        enrolledInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        enrolledInfo_Label.setText("Indskrevet:");

        enrolled_Label.setForeground(new java.awt.Color(102, 153, 0));
        enrolled_Label.setText("ENROLLED");

        administratorInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        administratorInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        administratorInfo_Label.setText("Administrator:");

        administrator_Label.setForeground(new java.awt.Color(102, 153, 0));
        administrator_Label.setText("ADMIN");

        reserveInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        reserveInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        reserveInfo_Label.setText("Reserve:");

        reserve_Label.setForeground(new java.awt.Color(102, 153, 0));
        reserve_Label.setText("RESERVE");

        javax.swing.GroupLayout bottom_PaneLayout = new javax.swing.GroupLayout(bottom_Pane);
        bottom_Pane.setLayout(bottom_PaneLayout);
        bottom_PaneLayout.setHorizontalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottom_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateInfo_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_Label)
                .addGap(18, 18, 18)
                .addComponent(userInfo_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_Label)
                .addGap(18, 18, 18)
                .addComponent(administratorInfo_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(administrator_Label)
                .addGap(18, 18, 18)
                .addComponent(reserveInfo_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reserve_Label)
                .addGap(18, 18, 18)
                .addComponent(enrolledInfo_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enrolled_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bottom_PaneLayout.setVerticalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottom_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateInfo_Label)
                    .addComponent(date_Label)
                    .addComponent(userInfo_Label)
                    .addComponent(user_Label)
                    .addComponent(enrolledInfo_Label)
                    .addComponent(enrolled_Label)
                    .addComponent(administratorInfo_Label)
                    .addComponent(administrator_Label)
                    .addComponent(reserveInfo_Label)
                    .addComponent(reserve_Label))
                .addContainerGap())
        );

        file_Menu.setText("Filer");

        repport_MenuItem.setText("Print rapport");
        file_Menu.add(repport_MenuItem);

        reset_MenuItem.setText("Daglig nulstilling");
        file_Menu.add(reset_MenuItem);
        file_Menu.add(jSeparator1);

        changeUser_MenuItem.setText("Login som anden bruger");
        changeUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeUser_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(changeUser_MenuItem);
        file_Menu.add(jSeparator3);

        releaseNotes_MenuItem.setText("Release Notes");
        file_Menu.add(releaseNotes_MenuItem);

        menuBar_MenuBar.add(file_Menu);

        administration_Menu.setText("Administration");

        persons_Menu.setText("Personer");

        createPerson_MenuItem.setText("Opret person");
        createPerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPerson_MenuItemActionPerformed(evt);
            }
        });
        persons_Menu.add(createPerson_MenuItem);

        editPerson_MenuItem.setText("Rediger person");
        persons_Menu.add(editPerson_MenuItem);

        deletePerson_MenuItem.setText("Slet person");
        deletePerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePerson_MenuItemActionPerformed(evt);
            }
        });
        persons_Menu.add(deletePerson_MenuItem);
        persons_Menu.add(jSeparator2);

        oneTimeEnroll_MenuItem.setText("Engangs indskrivning");
        persons_Menu.add(oneTimeEnroll_MenuItem);

        administration_Menu.add(persons_Menu);

        users_Menu.setText("Brugere");

        createUser_MenuItem.setText("Opret bruger");
        createUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUser_MenuItemActionPerformed(evt);
            }
        });
        users_Menu.add(createUser_MenuItem);

        editUser_MenuItem.setText("Rediger bruger");
        users_Menu.add(editUser_MenuItem);

        deleteUser_MenuItem.setText("Slet bruger");
        deleteUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUser_MenuItemActionPerformed(evt);
            }
        });
        users_Menu.add(deleteUser_MenuItem);

        administration_Menu.add(users_Menu);

        menuBar_MenuBar.add(administration_Menu);

        setJMenuBar(menuBar_MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottom_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottom_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeUser_MenuItemActionPerformed
        setVisible(false);
        clearPerson();
        clearResult();
        loginHandler.requestLoginView();
        setUser();
        setVisible(true);
    }//GEN-LAST:event_changeUser_MenuItemActionPerformed

    private void createUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUser_MenuItemActionPerformed
        createUserDIA.setVisible(true);
    }//GEN-LAST:event_createUser_MenuItemActionPerformed

    private void createPerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPerson_MenuItemActionPerformed
        createPersonDIA.setUser();
        createPersonDIA.setVisible(true);
    }//GEN-LAST:event_createPerson_MenuItemActionPerformed

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        clearResult();
        clearPerson();
        ArrayList<Person> persons = personHandler.search(search_TextField.getText());
        DefaultListModel dlm = new DefaultListModel();

        for (Person p : persons) {
            dlm.addElement(p.getId() + ":" + p.getFirstname() + " " + p.getLastname());
        }

        result_List.setModel(dlm);
    }//GEN-LAST:event_search_ButtonActionPerformed

    private void result_ListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_result_ListValueChanged
        clearPerson();
        if (!evt.getValueIsAdjusting()) {
            String selectionText = (String) result_List.getSelectedValue();
            String ids = "";
            for (int i = 0; i <= selectionText.length(); i++) {
                if (selectionText.substring(i, i + 1).equals(":")) {
                    break;
                }
                String s = selectionText.substring(i, i + 1);
                ids = ids + s;
            }
            int id = Integer.valueOf(ids);
            setPerson(personHandler.getPerson(id));
        }
    }//GEN-LAST:event_result_ListValueChanged

    private void deleteUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUser_MenuItemActionPerformed
        removeUserDIA.setVisible(true);
    }//GEN-LAST:event_deleteUser_MenuItemActionPerformed

    private void renew_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renew_ButtonActionPerformed
        
    }//GEN-LAST:event_renew_ButtonActionPerformed

    private void deletePerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePerson_MenuItemActionPerformed
        removePersonDIA.setVisible(true);
    }//GEN-LAST:event_deletePerson_MenuItemActionPerformed
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
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainGUI().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressInfo_Label;
    private javax.swing.JTextField address_TextField;
    private javax.swing.JMenu administration_Menu;
    private javax.swing.JLabel administratorInfo_Label;
    private javax.swing.JLabel administrator_Label;
    private javax.swing.JLabel birthdayInfo_Label;
    private javax.swing.JTextField birthday_TextField;
    private javax.swing.JPanel bottom_Pane;
    private javax.swing.JMenuItem changeUser_MenuItem;
    private javax.swing.JMenuItem createPerson_MenuItem;
    private javax.swing.JMenuItem createUser_MenuItem;
    private javax.swing.JLabel dateInfo_Label;
    private javax.swing.JLabel date_Label;
    private javax.swing.JMenuItem deletePerson_MenuItem;
    private javax.swing.JMenuItem deleteUser_MenuItem;
    private javax.swing.JPanel details_Pane;
    private javax.swing.JMenuItem editPerson_MenuItem;
    private javax.swing.JMenuItem editUser_MenuItem;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JLabel enrolledInfo_Label;
    private javax.swing.JLabel enrolled_Label;
    private javax.swing.JLabel expirationDateInfo_Label;
    private javax.swing.JTextField expirationDate_TextField;
    private javax.swing.JMenu file_Menu;
    private javax.swing.JLabel firstnameInfo_Label;
    private javax.swing.JTextField firstname_TextField;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JButton kick_Button;
    private javax.swing.JLabel lastnameInfo_Label;
    private javax.swing.JTextField lastname_TextField;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JMenuBar menuBar_MenuBar;
    private javax.swing.JLabel middlenameInfo_Label;
    private javax.swing.JTextField middlename_TextField;
    private javax.swing.JLabel oneOneInfo_Label;
    private javax.swing.JTextField oneOne_TextField;
    private javax.swing.JMenuItem oneTimeEnroll_MenuItem;
    private javax.swing.JMenu persons_Menu;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JMenuItem releaseNotes_MenuItem;
    private javax.swing.JButton renew_Button;
    private javax.swing.JMenuItem repport_MenuItem;
    private javax.swing.JButton requestQuarantine_Button;
    private javax.swing.JLabel reserveInfo_Label;
    private javax.swing.JLabel reserve_Label;
    private javax.swing.JMenuItem reset_MenuItem;
    private javax.swing.JList result_List;
    private javax.swing.JPanel result_Pane;
    private javax.swing.JScrollPane result_ScrollPane;
    private javax.swing.JButton search_Button;
    private javax.swing.JTextField search_TextField;
    private view.image.StatusPicPane statusPicPane_StatusPicPane;
    private javax.swing.JLabel userInfo_Label;
    private javax.swing.JLabel user_Label;
    private javax.swing.JMenu users_Menu;
    // End of variables declaration//GEN-END:variables
}
