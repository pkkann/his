package view;

import control.PersonHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import model.Person;
import session.LoginHandler;

/**
 *
 * @author pkann
 */
public class MainGUI extends javax.swing.JFrame {

    private LoginGUI loginGUI;
    private OpretPersonGUI opretPersonGUI;
    private LoginHandler loginHandler;
    private ButtonGroup category_1;
    private PersonHandler personHandler;

    public MainGUI(LoginGUI loginGUI, OpretPersonGUI opretPersonGUI, LoginHandler loginHandler, PersonHandler personHandler) {
        initComponents();
        setupButtonGroup();
        this.loginGUI = loginGUI;
        this.opretPersonGUI = opretPersonGUI;
        this.loginHandler = loginHandler;
        this.personHandler = personHandler;
        setLocationRelativeTo(null);
        updateDate();
        login();
    }

    public void login() {
        if (loginHandler.getLoggedIn() == null) {
            setVisible(false);
            loginGUI.setVisible(true);
            login();
        } else {
            setVisible(true);
            loginGUI.setVisible(false);
            user_Label.setText(loginHandler.getLoggedIn().getName());
        }
    }

    private void setPerson(Person p) {
        name_TextField.setText(p.getName());
        address_TextField.setText(p.getAddress());
        birthday_TextField.setText(p.getBirthdayDate());
        hone_CheckBox.setSelected(p.isHone());
        reserve_CheckBox.setSelected(p.isReserve());
        admin_CheckBox.setSelected(p.isAdmin());
        oneOne_CheckBox.setSelected(false);
        expiration_TextField.setText(p.getExpirationDate());
        try {
            picturePane_Pane.setPicture(ImageIO.read(p.getPicturePath()));
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (p.isQuarantine()) {
            statusPicPane_Pane.setStatus(1);
            statusPicPane_Pane.setToolTipText("OK");
        } else {
            statusPicPane_Pane.setStatus(0);
            statusPicPane_Pane.setToolTipText("Karantæne");
        }
    }

    private void clearPerson() {
        name_TextField.setText("");
        address_TextField.setText("");
        birthday_TextField.setText("");
        hone_CheckBox.setSelected(false);
        reserve_CheckBox.setSelected(false);
        admin_CheckBox.setSelected(false);
        oneOne_CheckBox.setSelected(false);
        expiration_TextField.setText("");
        picturePane_Pane.setPicture(null);
        statusPicPane_Pane.setStatus(2);
        statusPicPane_Pane.setToolTipText("");
    }

    private void setupButtonGroup() {
        category_1 = new ButtonGroup();
        category_1.add(birthday_RadioButton);
        category_1.add(name_RadioButton);
        birthday_RadioButton.getModel().setSelected(true);
    }

    private void clearResult() {
        try {
            DefaultListModel dlm = new DefaultListModel();
            result_List.setModel(dlm);
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    private void updateDate() {
        theDate_Label.setText(String.valueOf(Calendar.getInstance().get(Calendar.DATE) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_Pane = new javax.swing.JPanel();
        search_Pane = new javax.swing.JPanel();
        search_TextField = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        birthday_RadioButton = new javax.swing.JRadioButton();
        name_RadioButton = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        details_Pane = new javax.swing.JPanel();
        name_Label = new javax.swing.JLabel();
        address_Label = new javax.swing.JLabel();
        birthday_Label = new javax.swing.JLabel();
        name_TextField = new javax.swing.JTextField();
        address_TextField = new javax.swing.JTextField();
        birthday_TextField = new javax.swing.JTextField();
        hone_Label = new javax.swing.JLabel();
        hone_CheckBox = new javax.swing.JCheckBox();
        reserve_Label = new javax.swing.JLabel();
        reserve_CheckBox = new javax.swing.JCheckBox();
        admin_label = new javax.swing.JLabel();
        admin_CheckBox = new javax.swing.JCheckBox();
        oneOne_Label = new javax.swing.JLabel();
        oneOne_CheckBox = new javax.swing.JCheckBox();
        expiration_Label = new javax.swing.JLabel();
        expiration_TextField = new javax.swing.JTextField();
        picture_Label = new javax.swing.JLabel();
        status_Pane = new javax.swing.JPanel();
        statusPicPane_Pane = new view.image.StatusPicPane();
        enroll_Button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        picturePane_Pane = new view.image.PicturePane();
        jButton1 = new javax.swing.JButton();
        result_Pane = new javax.swing.JPanel();
        result_ScrollPane = new javax.swing.JScrollPane();
        result_List = new javax.swing.JList();
        bottom_Pane = new javax.swing.JPanel();
        dato_Label = new javax.swing.JLabel();
        theDate_Label = new javax.swing.JLabel();
        splitter1_Label = new javax.swing.JLabel();
        guests_Label = new javax.swing.JLabel();
        theGuests_Label = new javax.swing.JLabel();
        splitter2_Label = new javax.swing.JLabel();
        loggedIn_Label = new javax.swing.JLabel();
        user_Label = new javax.swing.JLabel();
        menuBar_MenuBar = new javax.swing.JMenuBar();
        file_Menu = new javax.swing.JMenu();
        logOff_MenuItem = new javax.swing.JMenuItem();
        close_MenuItem = new javax.swing.JMenuItem();
        administrer_Menu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(871, 527));
        setResizable(false);

        search_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Søg", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.gray));

        search_Button.setText("Søg");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        birthday_RadioButton.setText("Fødselsdag");

        name_RadioButton.setText("Navn");

        jCheckBox1.setText("Reserve");

        jCheckBox2.setText("Høne");

        javax.swing.GroupLayout search_PaneLayout = new javax.swing.GroupLayout(search_Pane);
        search_Pane.setLayout(search_PaneLayout);
        search_PaneLayout.setHorizontalGroup(
            search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(search_PaneLayout.createSequentialGroup()
                        .addComponent(birthday_RadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(name_RadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox2)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addComponent(search_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_Button)
                .addContainerGap())
        );
        search_PaneLayout.setVerticalGroup(
            search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthday_RadioButton)
                    .addComponent(name_RadioButton)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        details_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oplysninger", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.gray));

        name_Label.setText("Navn:");

        address_Label.setText("Adresse:");

        birthday_Label.setText("Fødselsdag:");

        name_TextField.setEditable(false);
        name_TextField.setEnabled(false);

        address_TextField.setEditable(false);
        address_TextField.setEnabled(false);

        birthday_TextField.setEditable(false);
        birthday_TextField.setEnabled(false);

        hone_Label.setText("Høne:");

        hone_CheckBox.setEnabled(false);

        reserve_Label.setText("Reserve:");

        reserve_CheckBox.setEnabled(false);

        admin_label.setText("Admin:");

        admin_CheckBox.setEnabled(false);

        oneOne_Label.setText("1-1:");

        oneOne_CheckBox.setEnabled(false);

        expiration_Label.setText("Udløbsdato:");

        expiration_TextField.setEditable(false);
        expiration_TextField.setEnabled(false);

        picture_Label.setText("Billed:");

        status_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        javax.swing.GroupLayout statusPicPane_PaneLayout = new javax.swing.GroupLayout(statusPicPane_Pane);
        statusPicPane_Pane.setLayout(statusPicPane_PaneLayout);
        statusPicPane_PaneLayout.setHorizontalGroup(
            statusPicPane_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        statusPicPane_PaneLayout.setVerticalGroup(
            statusPicPane_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout status_PaneLayout = new javax.swing.GroupLayout(status_Pane);
        status_Pane.setLayout(status_PaneLayout);
        status_PaneLayout.setHorizontalGroup(
            status_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, status_PaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(statusPicPane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        status_PaneLayout.setVerticalGroup(
            status_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_PaneLayout.createSequentialGroup()
                .addComponent(statusPicPane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        enroll_Button.setText("Indskriv");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picturePane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picturePane_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Karantæne");

        javax.swing.GroupLayout details_PaneLayout = new javax.swing.GroupLayout(details_Pane);
        details_Pane.setLayout(details_PaneLayout);
        details_PaneLayout.setHorizontalGroup(
            details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(details_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(picture_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expiration_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(oneOne_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admin_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reserve_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hone_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(birthday_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(address_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(name_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(address_TextField)
                    .addComponent(birthday_TextField)
                    .addComponent(hone_CheckBox)
                    .addComponent(reserve_CheckBox)
                    .addComponent(admin_CheckBox)
                    .addComponent(oneOne_CheckBox)
                    .addComponent(expiration_TextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, details_PaneLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(status_Pane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, details_PaneLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enroll_Button)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        details_PaneLayout.setVerticalGroup(
            details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(details_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_Label)
                    .addComponent(name_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address_Label)
                    .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthday_Label)
                    .addComponent(birthday_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hone_Label)
                    .addComponent(hone_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reserve_Label)
                    .addComponent(reserve_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin_label)
                    .addComponent(admin_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneOne_Label)
                    .addComponent(oneOne_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expiration_Label)
                    .addComponent(expiration_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(details_PaneLayout.createSequentialGroup()
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(picture_Label)
                            .addGroup(details_PaneLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(status_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(details_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enroll_Button)
                            .addComponent(jButton1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        result_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.gray));

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
                .addComponent(result_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        result_PaneLayout.setVerticalGroup(
            result_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(result_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(result_ScrollPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(main_PaneLayout.createSequentialGroup()
                        .addComponent(details_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(result_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(details_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(result_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bottom_Pane.setBackground(java.awt.Color.darkGray);

        dato_Label.setForeground(java.awt.Color.white);
        dato_Label.setText("Dato:");

        theDate_Label.setForeground(java.awt.Color.white);
        theDate_Label.setText("DATE");

        splitter1_Label.setForeground(java.awt.Color.white);
        splitter1_Label.setText("|");

        guests_Label.setForeground(java.awt.Color.white);
        guests_Label.setText("Gæster:");

        theGuests_Label.setForeground(java.awt.Color.white);
        theGuests_Label.setText("GUESTS");

        splitter2_Label.setForeground(java.awt.Color.white);
        splitter2_Label.setText("|");

        loggedIn_Label.setForeground(java.awt.Color.white);
        loggedIn_Label.setText("Logged ind:");

        user_Label.setForeground(java.awt.Color.white);
        user_Label.setText("USER");

        javax.swing.GroupLayout bottom_PaneLayout = new javax.swing.GroupLayout(bottom_Pane);
        bottom_Pane.setLayout(bottom_PaneLayout);
        bottom_PaneLayout.setHorizontalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottom_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dato_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(theDate_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitter1_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guests_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(theGuests_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitter2_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loggedIn_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bottom_PaneLayout.setVerticalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottom_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dato_Label)
                    .addComponent(theDate_Label)
                    .addComponent(splitter1_Label)
                    .addComponent(guests_Label)
                    .addComponent(theGuests_Label)
                    .addComponent(splitter2_Label)
                    .addComponent(loggedIn_Label)
                    .addComponent(user_Label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        file_Menu.setText("File");

        logOff_MenuItem.setText("Log ud");
        logOff_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOff_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(logOff_MenuItem);

        close_MenuItem.setText("Luk");
        close_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_MenuItemActionPerformed(evt);
            }
        });
        file_Menu.add(close_MenuItem);

        menuBar_MenuBar.add(file_Menu);

        administrer_Menu.setText("Administrer");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Opret person");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        administrer_Menu.add(jMenuItem1);

        menuBar_MenuBar.add(administrer_Menu);

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
    }// </editor-fold>//GEN-END:initComponents

    private void logOff_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOff_MenuItemActionPerformed
        loginHandler.logout();
        clearPerson();
        clearResult();
        login();
    }//GEN-LAST:event_logOff_MenuItemActionPerformed

    private void close_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_MenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_close_MenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        opretPersonGUI.setVisible(true);
        clearResult();
        clearPerson();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        ArrayList<Person> personer = personHandler.getPersoner();
        DefaultListModel listModel = new DefaultListModel();

        for (Person p : personer) {
            listModel.addElement(p.getName());
        }

        result_List.setModel(listModel);
    }//GEN-LAST:event_search_ButtonActionPerformed

    private void result_ListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_result_ListValueChanged
        int selection = result_List.getSelectedIndex();
        Person p = personHandler.getPerson(selection);
        setPerson(p);
    }//GEN-LAST:event_result_ListValueChanged
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
    private javax.swing.JLabel address_Label;
    private javax.swing.JTextField address_TextField;
    private javax.swing.JCheckBox admin_CheckBox;
    private javax.swing.JLabel admin_label;
    private javax.swing.JMenu administrer_Menu;
    private javax.swing.JLabel birthday_Label;
    private javax.swing.JRadioButton birthday_RadioButton;
    private javax.swing.JTextField birthday_TextField;
    private javax.swing.JPanel bottom_Pane;
    private javax.swing.JMenuItem close_MenuItem;
    private javax.swing.JLabel dato_Label;
    private javax.swing.JPanel details_Pane;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JLabel expiration_Label;
    private javax.swing.JTextField expiration_TextField;
    private javax.swing.JMenu file_Menu;
    private javax.swing.JLabel guests_Label;
    private javax.swing.JCheckBox hone_CheckBox;
    private javax.swing.JLabel hone_Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem logOff_MenuItem;
    private javax.swing.JLabel loggedIn_Label;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JMenuBar menuBar_MenuBar;
    private javax.swing.JLabel name_Label;
    private javax.swing.JRadioButton name_RadioButton;
    private javax.swing.JTextField name_TextField;
    private javax.swing.JCheckBox oneOne_CheckBox;
    private javax.swing.JLabel oneOne_Label;
    private view.image.PicturePane picturePane_Pane;
    private javax.swing.JLabel picture_Label;
    private javax.swing.JCheckBox reserve_CheckBox;
    private javax.swing.JLabel reserve_Label;
    private javax.swing.JList result_List;
    private javax.swing.JPanel result_Pane;
    private javax.swing.JScrollPane result_ScrollPane;
    private javax.swing.JButton search_Button;
    private javax.swing.JPanel search_Pane;
    private javax.swing.JTextField search_TextField;
    private javax.swing.JLabel splitter1_Label;
    private javax.swing.JLabel splitter2_Label;
    private view.image.StatusPicPane statusPicPane_Pane;
    private javax.swing.JPanel status_Pane;
    private javax.swing.JLabel theDate_Label;
    private javax.swing.JLabel theGuests_Label;
    private javax.swing.JLabel user_Label;
    // End of variables declaration//GEN-END:variables
}
