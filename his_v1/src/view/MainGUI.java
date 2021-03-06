/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.person.PersonHandler;
import date.ADate;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import login.LoginHandler;
import main.HISM;
import model.Person;
import view.person.CreatePersonDIA;
import view.person.EditPersonDIA;
import view.person.EnrollPersonDIA;
import view.person.RemovePersonDIA;
import view.person.RenewPersonDIA;
import view.user.CreateUserDIA;
import view.user.EditUserDIA;
import view.user.RemoveUserDIA;
import view.user.ResetUserPasswordDIA;

/**
 *
 * @author patrick
 */
public class MainGUI extends javax.swing.JFrame {

    private LoginHandler loginHandler;
    private PersonHandler personHandler;
    private CreateUserDIA createUserDIA;
    private EditUserDIA editUserDIA;
    private RemoveUserDIA removeUserDIA;
    private CreatePersonDIA createPersonDIA;
    private EditPersonDIA editPersonDIA;
    private RemovePersonDIA removePersonDIA;
    private RenewPersonDIA renewPersonDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private Person selectedPerson;
    private ResetUserPasswordDIA resetUserPasswordDIA;

    public MainGUI(LoginHandler loginHandler, PersonHandler personHandler, CreateUserDIA createUserDIA, EditUserDIA editUserDIA, RemoveUserDIA removeUserDIA, CreatePersonDIA createPersonDIA, EditPersonDIA editPersonDIA, RemovePersonDIA removePersonDIA, RenewPersonDIA renewPersonDIA, EnrollPersonDIA enrollPersonDIA, ResetUserPasswordDIA resetUserPasswordDIA) {
        initComponents();
        this.loginHandler = loginHandler;
        this.personHandler = personHandler;
        this.createUserDIA = createUserDIA;
        this.editUserDIA = editUserDIA;
        this.removeUserDIA = removeUserDIA;
        this.createPersonDIA = createPersonDIA;
        this.editPersonDIA = editPersonDIA;
        this.removePersonDIA = removePersonDIA;
        this.renewPersonDIA = renewPersonDIA;
        this.enrollPersonDIA = enrollPersonDIA;
        this.resetUserPasswordDIA = resetUserPasswordDIA;
        setTitle(HISM.title + " - " + HISM.version);
    }

    public void setDate(ADate date) {
        date_Label.setText(ADate.formatADate(date, "/"));
    }

    private void setPerson(Person p) {
        if (p != null) {
            selectedPerson = p;
            firstname_TextField.setText(p.getFirstname());
            middlename_TextField.setText(p.getMiddlename());
            lastname_TextField.setText(p.getLastname());
            birthday_TextField.setText(ADate.formatADate(p.getBirthdayDate(), "/"));
            expirationDate_TextField.setText(ADate.formatADate(p.getExpirationDate(), "/"));

            if (p.isOneOne()) {
                oneOne_TextField.setText("Ja");
                oneOne_TextField.setBackground(Color.cyan);
                renew_Button.setEnabled(false);
            } else {
                oneOne_TextField.setText("Nej");
                oneOne_TextField.setBackground(new Color(240, 240, 240));
                renew_Button.setEnabled(true);
            }
            if (p.isExpired()) {
                expirationDate_TextField.setForeground(Color.white);
                expirationDate_TextField.setBackground(Color.red);
                renew_Button.setEnabled(true);
                enroll_Button.setEnabled(false);
            } else {
                expirationDate_TextField.setForeground(Color.black);
                expirationDate_TextField.setBackground(new Color(240, 240, 240));
                renew_Button.setEnabled(false);
                enroll_Button.setEnabled(true);
            }
            if (p.isHone()) {
                hone_TextField.setText("Ja");
                hone_TextField.setBackground(Color.PINK);
            } else {
                hone_TextField.setText("Nej");
                hone_TextField.setBackground(new Color(240, 240, 240));
            }

            address_TextField.setText(p.getAddress());
            try {
                picturePane_PicturePane.setPicture(ImageIO.read(p.getPicturePath()));
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (p.isQuarantine()) {
                statusPicPane_StatusPicPane.setStatus(1);
                enroll_Button.setEnabled(false);
            } else if (p.isExpired()) {
                statusPicPane_StatusPicPane.setStatus(3);
            } else {
                statusPicPane_StatusPicPane.setStatus(0);
            }
//            kick_Button.setEnabled(true);
//            requestQuarantine_Button.setEnabled(true);
            if (p.isEnrolled()) {
                enrollInfo_Label.setText("Indskrevet");
                enrollInfo_Label.setForeground(Color.black);
                lamp_Pane.setBackground(Color.green);
            } else {
                enrollInfo_Label.setText("Ikke indskrevet");
                enrollInfo_Label.setForeground(Color.white);
                lamp_Pane.setBackground(new Color(51, 51, 51));
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
        enroll_Button.setEnabled(false);
        renew_Button.setEnabled(false);
        kick_Button.setEnabled(false);
        requestQuarantine_Button.setEnabled(false);
        oneOne_TextField.setBackground(new Color(240, 240, 240));
        hone_TextField.setText("");
        hone_TextField.setBackground(new Color(240, 240, 240));
        enrollInfo_Label.setText("");
        enrollInfo_Label.setForeground(Color.white);
        lamp_Pane.setBackground(new Color(51, 51, 51));
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
    
    private void search() {
        clearResult();
        clearPerson();
        ArrayList<Person> persons = personHandler.search(search_TextField.getText());
        DefaultListModel dlm = new DefaultListModel();

        for (Person p : persons) {
            dlm.addElement(p.getId() + ":" + p.getFirstname() + " " + p.getLastname());
        }

        result_List.setModel(dlm);
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
        honeInfo_Label = new javax.swing.JLabel();
        hone_TextField = new javax.swing.JTextField();
        lamp_Pane = new javax.swing.JPanel();
        enrollInfo_Label = new javax.swing.JLabel();
        search_TextField = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        bottom_Pane = new javax.swing.JPanel();
        dateInfo_Label = new javax.swing.JLabel();
        date_Label = new javax.swing.JLabel();
        userInfo_Label = new javax.swing.JLabel();
        user_Label = new javax.swing.JLabel();
        administratorInfo_Label = new javax.swing.JLabel();
        administrator_Label = new javax.swing.JLabel();
        reserveInfo_Label = new javax.swing.JLabel();
        reserve_Label = new javax.swing.JLabel();
        menuBar_MenuBar = new javax.swing.JMenuBar();
        file_Menu = new javax.swing.JMenu();
        report_MenuItem = new javax.swing.JMenuItem();
        reset_MenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        changeUser_MenuItem = new javax.swing.JMenuItem();
        administration_Menu = new javax.swing.JMenu();
        persons_Menu = new javax.swing.JMenu();
        createPerson_MenuItem = new javax.swing.JMenuItem();
        editPerson_MenuItem = new javax.swing.JMenuItem();
        deletePerson_MenuItem = new javax.swing.JMenuItem();
        users_Menu = new javax.swing.JMenu();
        createUser_MenuItem = new javax.swing.JMenuItem();
        editUser_MenuItem = new javax.swing.JMenuItem();
        deleteUser_MenuItem = new javax.swing.JMenuItem();
        bruger_Menu = new javax.swing.JMenu();
        resetPassword_MenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

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

        enroll_Button.setText("Indskrivning");
        enroll_Button.setEnabled(false);
        enroll_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enroll_ButtonActionPerformed(evt);
            }
        });

        requestQuarantine_Button.setText("Anmod om karantæne");
        requestQuarantine_Button.setToolTipText("Denne funktion er ikke implementeret endnu");
        requestQuarantine_Button.setEnabled(false);
        requestQuarantine_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestQuarantine_ButtonActionPerformed(evt);
            }
        });

        kick_Button.setText("Smid ud");
        kick_Button.setToolTipText("Denne funktion er ikke implementeret endnu");
        kick_Button.setEnabled(false);
        kick_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_ButtonActionPerformed(evt);
            }
        });

        addressInfo_Label.setText("Adresse:");

        address_TextField.setEditable(false);

        renew_Button.setText("Forny");
        renew_Button.setEnabled(false);
        renew_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renew_ButtonActionPerformed(evt);
            }
        });

        honeInfo_Label.setText("Høne:");

        hone_TextField.setEditable(false);

        lamp_Pane.setBackground(new java.awt.Color(51, 51, 51));
        lamp_Pane.setPreferredSize(new java.awt.Dimension(0, 4));

        enrollInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        enrollInfo_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout lamp_PaneLayout = new javax.swing.GroupLayout(lamp_Pane);
        lamp_Pane.setLayout(lamp_PaneLayout);
        lamp_PaneLayout.setHorizontalGroup(
            lamp_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enrollInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lamp_PaneLayout.setVerticalGroup(
            lamp_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enrollInfo_Label)
        );

        javax.swing.GroupLayout details_PaneLayout = new javax.swing.GroupLayout(details_Pane);
        details_Pane.setLayout(details_PaneLayout);
        details_PaneLayout.setHorizontalGroup(
            details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(details_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(honeInfo_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(middlenameInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstnameInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lastnameInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(birthdayInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expirationDateInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(oneOneInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressInfo_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hone_TextField)
                            .addComponent(firstname_TextField)
                            .addComponent(middlename_TextField)
                            .addComponent(lastname_TextField)
                            .addComponent(birthday_TextField)
                            .addComponent(expirationDate_TextField)
                            .addComponent(oneOne_TextField)
                            .addComponent(address_TextField))
                        .addGap(18, 18, 18)
                        .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lamp_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(enroll_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(details_PaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(renew_Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kick_Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(requestQuarantine_Button))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, details_PaneLayout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(statusPicPane_StatusPicPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(honeInfo_Label)
                            .addComponent(hone_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(lamp_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enroll_Button)
                    .addComponent(requestQuarantine_Button)
                    .addComponent(kick_Button)
                    .addComponent(renew_Button))
                .addContainerGap())
        );

        search_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_TextFieldActionPerformed(evt);
            }
        });

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

        date_Label.setForeground(new java.awt.Color(102, 204, 0));
        date_Label.setText("DATE");

        userInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        userInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        userInfo_Label.setText("Bruger:");

        user_Label.setForeground(new java.awt.Color(102, 204, 0));
        user_Label.setText("USER");

        administratorInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        administratorInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        administratorInfo_Label.setText("Administrator:");

        administrator_Label.setForeground(new java.awt.Color(102, 204, 0));
        administrator_Label.setText("ADMIN");

        reserveInfo_Label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        reserveInfo_Label.setForeground(new java.awt.Color(255, 255, 255));
        reserveInfo_Label.setText("Reserve:");

        reserve_Label.setForeground(new java.awt.Color(102, 204, 0));
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
                    .addComponent(administratorInfo_Label)
                    .addComponent(administrator_Label)
                    .addComponent(reserveInfo_Label)
                    .addComponent(reserve_Label))
                .addContainerGap())
        );

        file_Menu.setText("Filer");

        report_MenuItem.setText("Gem rapport");
        report_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(report_MenuItem);

        reset_MenuItem.setText("Daglig nulstilling");
        reset_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(reset_MenuItem);
        file_Menu.add(jSeparator1);

        changeUser_MenuItem.setText("Log af");
        changeUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeUser_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(changeUser_MenuItem);

        menuBar_MenuBar.add(file_Menu);

        administration_Menu.setText("Administration");

        persons_Menu.setText("Personer");

        createPerson_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        createPerson_MenuItem.setText("Opret person");
        createPerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPerson_MenuItemActionPerformed(evt);
            }
        });
        persons_Menu.add(createPerson_MenuItem);

        editPerson_MenuItem.setText("Rediger person");
        editPerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPerson_MenuItemActionPerformed(evt);
            }
        });
        persons_Menu.add(editPerson_MenuItem);

        deletePerson_MenuItem.setText("Slet person");
        deletePerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePerson_MenuItemActionPerformed(evt);
            }
        });
        persons_Menu.add(deletePerson_MenuItem);

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
        editUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUser_MenuItemActionPerformed(evt);
            }
        });
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

        bruger_Menu.setText("Bruger");

        resetPassword_MenuItem.setText("Ændre din kode");
        resetPassword_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPassword_MenuItemActionPerformed(evt);
            }
        });
        bruger_Menu.add(resetPassword_MenuItem);

        menuBar_MenuBar.add(bruger_Menu);

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

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        search();
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
        renewPersonDIA.setPerson(selectedPerson);
        renewPersonDIA.setVisible(true);
    }//GEN-LAST:event_renew_ButtonActionPerformed

    private void editUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUser_MenuItemActionPerformed
        editUserDIA.setVisible(true);
        setUser();
    }//GEN-LAST:event_editUser_MenuItemActionPerformed

    private void resetPassword_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPassword_MenuItemActionPerformed
        resetUserPasswordDIA.setUser(loginHandler.getLoggedInUser());
        resetUserPasswordDIA.setVisible(true);
    }//GEN-LAST:event_resetPassword_MenuItemActionPerformed

    private void enroll_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enroll_ButtonActionPerformed
        enrollPersonDIA.setPerson(selectedPerson);
        enrollPersonDIA.setVisible(true);
        setPerson(selectedPerson);
    }//GEN-LAST:event_enroll_ButtonActionPerformed

    private void requestQuarantine_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestQuarantine_ButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Du kan ikke anmode om garantæne, fordi du er grim", "Fejl", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_requestQuarantine_ButtonActionPerformed

    private void deletePerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePerson_MenuItemActionPerformed
        removePersonDIA.setVisible(true);
    }//GEN-LAST:event_deletePerson_MenuItemActionPerformed

    private void editPerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPerson_MenuItemActionPerformed
        editPersonDIA.setVisible(true);
    }//GEN-LAST:event_editPerson_MenuItemActionPerformed

    private void createPerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPerson_MenuItemActionPerformed
        createPersonDIA.setUser();
        createPersonDIA.setVisible(true);
    }//GEN-LAST:event_createPerson_MenuItemActionPerformed

    private void kick_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kick_ButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Funktion ikke implementeret endnu.\nSkriv det ned på et stykke papir", "Fejl", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_kick_ButtonActionPerformed

    private void report_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_MenuItemActionPerformed
        personHandler.generateReport(false);
        JOptionPane.showMessageDialog(this, "En rapport blev oprettet. Du finder den i report mappen", "Rapport", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_report_MenuItemActionPerformed

    private void reset_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_MenuItemActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "Er du helt sikker på at du vil nulstille systemet?\nDer vil automatisk blive oprettet en rapport, men du vil ikke kunne loade indskrivningerne ind for idag igen...", "Nulstil", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            int n2 = JOptionPane.showConfirmDialog(this, "Er du HELT sikker på du vil nulstille?", "Nulstil", JOptionPane.YES_NO_OPTION);
            if (n2 == 0) {
                personHandler.generateReport(true);
                personHandler.resetSystem();
                JOptionPane.showMessageDialog(this, "Systemet blev nulstillet, og en rapport blev oprettet og gemt i report mappen\nDu bliver nu logget af...", "Nulstil", JOptionPane.PLAIN_MESSAGE);

                setVisible(false);
                clearPerson();
                clearResult();
                loginHandler.requestLoginView();
                setUser();
                setVisible(true);
            }
        }
    }//GEN-LAST:event_reset_MenuItemActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        
    }//GEN-LAST:event_formKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void search_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_TextFieldActionPerformed
        search();
    }//GEN-LAST:event_search_TextFieldActionPerformed
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
    private javax.swing.JMenu bruger_Menu;
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
    private javax.swing.JLabel enrollInfo_Label;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JLabel expirationDateInfo_Label;
    private javax.swing.JTextField expirationDate_TextField;
    private javax.swing.JMenu file_Menu;
    private javax.swing.JLabel firstnameInfo_Label;
    private javax.swing.JTextField firstname_TextField;
    private javax.swing.JLabel honeInfo_Label;
    private javax.swing.JTextField hone_TextField;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton kick_Button;
    private javax.swing.JPanel lamp_Pane;
    private javax.swing.JLabel lastnameInfo_Label;
    private javax.swing.JTextField lastname_TextField;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JMenuBar menuBar_MenuBar;
    private javax.swing.JLabel middlenameInfo_Label;
    private javax.swing.JTextField middlename_TextField;
    private javax.swing.JLabel oneOneInfo_Label;
    private javax.swing.JTextField oneOne_TextField;
    private javax.swing.JMenu persons_Menu;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JButton renew_Button;
    private javax.swing.JMenuItem report_MenuItem;
    private javax.swing.JButton requestQuarantine_Button;
    private javax.swing.JLabel reserveInfo_Label;
    private javax.swing.JLabel reserve_Label;
    private javax.swing.JMenuItem resetPassword_MenuItem;
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
