/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.DocumentException;
import model.TableTool;
import control.EnrollmentHandler;
import control.HismHandlerIF;
import control.LoginHandler;
import control.PersonHandler;
import control.QuarantineHandler;
import control.ReportHandler;
import entity.User;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import view.message.DialogMessage;

/**
 *
 * @author patrick
 */
public class MainGUI extends javax.swing.JFrame {

    // Control
    private PersonHandler peH;
    private EnrollmentHandler enH;
    private QuarantineHandler quH;
    private LoginHandler loH;
    private ReportHandler reH;
    // View
    private CreatePersonDIA createPersonDIA;
    private RemovePersonDIA removePersonDIA;
    private EditPersonDIA editPersonDIA;
    private QuarantinePersonManagementDIA quarantinePersonManagemenDIA;
    private RemoveUserDIA removeUserDIA;
    private EditUserDIA editUserDIA;
    private CreateUserDIA createUserDIA;
    private SettingsDIA settingsDIA;
    private EditProfileDIA editProfileDIA;
    private EnrollPersonDIA enrollPersonDIA;
    private AboutDIA aboutDIA;
    private RenewPersonDIA renewPersonDIA;

    // Model
    private int selectedPerson = -1;
    private User loggedIn;

    public MainGUI(PersonHandler peH, EnrollmentHandler enH, QuarantineHandler quH, 
            SettingsDIA settingsDIA, CreatePersonDIA createPersonDIA, 
            RemoveUserDIA removeUserDIA, EditUserDIA editUserDIA, 
            CreateUserDIA createUserDIA, EditProfileDIA editProfileDIA, 
            EnrollPersonDIA enrollPersonDIA, RemovePersonDIA removePersonDIA, 
            AboutDIA aboutDIA, EditPersonDIA editPersonDIA, 
            QuarantinePersonManagementDIA quarantinePersonManagemenDIA, 
            RenewPersonDIA renewPersonDIA, ReportHandler reH) {
        initComponents();
        initTableListener();
        setIcon();
        setTitle();
        this.peH = peH;
        this.enH = enH;
        this.quH = quH;
        this.reH = reH;
        this.settingsDIA = settingsDIA;
        this.createPersonDIA = createPersonDIA;
        this.removeUserDIA = removeUserDIA;
        this.removePersonDIA = removePersonDIA;
        this.editPersonDIA = editPersonDIA;
        this.editUserDIA = editUserDIA;
        this.createUserDIA = createUserDIA;
        this.editProfileDIA = editProfileDIA;
        this.enrollPersonDIA = enrollPersonDIA;
        this.aboutDIA = aboutDIA;
        this.renewPersonDIA = renewPersonDIA;
        this.quarantinePersonManagemenDIA = quarantinePersonManagemenDIA;
        DefaultTableModel dtm = (DefaultTableModel) result_Table.getModel();
        
        createPerson_Button.setMnemonic(KeyEvent.VK_R);

        search_Button.requestFocus();

    }

    public void setLoginHandler(LoginHandler loH) {
        this.loH = loH;
    }

    public void setloggedInUser() {
        System.out.println("SETTING LOGGEDIN USER");
        this.loggedIn = LoginHandler.loggedIn;
        user_Label.setText(loggedIn.getFirstname() + " " + loggedIn.getLastname());

        // Set rights
        if (!loggedIn.isAdministrator()) {
            brugere_Menu.setEnabled(false);
            deletePerson_Button.setEnabled(false);
            deletePerson_Button.setToolTipText("Du skal være administrator for at benytte denne funktion");
            administrateQuarantines_MenuItem.setEnabled(false);
            indstillinger_MenuItem.setEnabled(false);
        } else {
            brugere_Menu.setEnabled(true);
            deletePerson_Button.setEnabled(true);
            deletePerson_Button.setToolTipText("Slet personer");
            administrateQuarantines_MenuItem.setEnabled(true);
            indstillinger_MenuItem.setEnabled(true);
        }
    }

    private void setIcon() {
        Image icon = null;
        try {
            icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(icon);
    }

    private void setTitle() {
        setTitle(his.His.title);
    }

    private void initTableListener() {
        result_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && result_Table.getSelectedRowCount() != 0) {
                    int selRow = result_Table.getSelectedRow();
                    Object selIDObject = result_Table.getModel().getValueAt(selRow, 0);
                    int selID = Integer.valueOf(String.valueOf(selIDObject));
                    setPerson(selID);
                }
            }
        });
    }

    private void setPerson(int id) {
        selectedPerson = id;

        if (peH.getPerson(id).getPicturePath().equals("N")) {
            Image icon = null;
            try {
                icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/billedid.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            picturePane_PicturePane.setPicture(icon, true);
        } else {
            try {
                Image img = ImageIO.read(new File(peH.getPerson(id).getPicturePath()));
                picturePane_PicturePane.setPicture(img, true);
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (peH.getPerson(id).isExpired()) {
            status_Label.setText("Personen er udløbet");
            status_Label.setForeground(Color.white);
            status_Pane.setBackground(Color.red);
            enroll_Button.setEnabled(false);
            renew_Button.setEnabled(true);
            kick_Button.setEnabled(false);
            return;
        }

        if (quH.isQuarantined(id)) {
            status_Label.setText("Personen har karantæne");
            status_Label.setForeground(Color.white);
            status_Pane.setBackground(Color.red);
            enroll_Button.setEnabled(false);
            kick_Button.setEnabled(false);
            renew_Button.setEnabled(false);
            return;
        }

        if (enH.isEnrolled(id)) {
            if (enH.getEnrollment(id).isKicked()) {
                status_Label.setForeground(Color.white);
                status_Label.setText("Personen er smidt ud");
                status_Pane.setBackground(Color.red);
                enroll_Button.setEnabled(false);
                kick_Button.setEnabled(false);
                renew_Button.setEnabled(false);
                return;
            } else {
                status_Label.setForeground(Color.black);
                status_Label.setText("Personen er indskrevet");
                status_Pane.setBackground(new Color(153, 204, 0));
                enroll_Button.setEnabled(true);
                kick_Button.setEnabled(true);
                renew_Button.setEnabled(false);
                return;
            }
        } else {
            status_Label.setForeground(Color.white);
            status_Label.setText("Personen er ikke indskrevet");
            status_Pane.setBackground(new Color(51, 51, 51));
            enroll_Button.setEnabled(true);
            kick_Button.setEnabled(false);
            renew_Button.setEnabled(false);
            return;
        }
    }

    public void updateDate(String date) {
        String d = "";

        if (date.isEmpty()) {
            Calendar c = Calendar.getInstance();
            d = String.valueOf(c.get(Calendar.DATE)) + "/" + String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
        }

        date_Label.setText(d);
    }

    public void updateEnrolledCounter() {
        int count = peH.getEnrolledCount();
        enrolled_Label.setText(String.valueOf(count));
    }

    private void cleanSelectedPerson() {
        selectedPerson = -1;
        picturePane_PicturePane.setPicture(null, true);
        enroll_Button.setEnabled(false);
        renew_Button.setEnabled(false);
        kick_Button.setEnabled(false);
        status_Label.setText("Ingen person valgt");
        status_Label.setForeground(Color.white);
        status_Pane.setBackground(new Color(51, 51, 51));
    }

    private void cleanTable() {
        DefaultTableModel dtm = TableTool.createEmptyPersonTableModel();
        result_Table.setModel(dtm);
    }

    private void cleanSearch() {
        search_TextField.setText("Søg på en persons navn/fødselsdag/adresse");
        search_TextField.setForeground(new Color(153, 153, 153));
    }

    public void search() {
        cleanSelectedPerson();
        String searchString = search_TextField.getText();
        if (searchString.equals("Søg på en persons navn/fødselsdag/adresse")) {
            searchString = "";
        }
        ArrayList<String[]> data = peH.searchPerson(searchString, enrolled_CheckBox.isSelected());
        DefaultTableModel dtm = TableTool.createPersonTableModel(data);
        result_Table.setModel(dtm);
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
        inner_Pane = new javax.swing.JPanel();
        search_TextField = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        result_ScrollPane = new javax.swing.JScrollPane();
        result_Table = new javax.swing.JTable();
        enroll_Button = new javax.swing.JButton();
        status_Pane = new javax.swing.JPanel();
        status_Label = new javax.swing.JLabel();
        picturePane_PicturePane = new view.image.PicturePane();
        renew_Button = new javax.swing.JButton();
        enrolled_CheckBox = new javax.swing.JCheckBox();
        kick_Button = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        createPerson_Button = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        editPerson_Button = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        deletePerson_Button = new javax.swing.JButton();
        bottom_Pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date_Label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user_Label = new javax.swing.JLabel();
        editProfile_Button = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        enrolled_Label = new javax.swing.JLabel();
        logoff_Button = new javax.swing.JButton();
        menuBar_MenuBar = new javax.swing.JMenuBar();
        filer_Menu = new javax.swing.JMenu();
        rapport_Menu = new javax.swing.JMenu();
        gemRapportIndskrevne_MenuItem = new javax.swing.JMenuItem();
        gemRapportPersoner_MenuItem = new javax.swing.JMenuItem();
        gemRapportBrugere_MenuItem = new javax.swing.JMenuItem();
        gemKarantæner_MenuItem = new javax.swing.JMenuItem();
        vagtAfslutning_Menu = new javax.swing.JMenu();
        nulstil_MenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        brugere_Menu = new javax.swing.JMenu();
        createUser_MenuItem = new javax.swing.JMenuItem();
        editUser_MenuItem = new javax.swing.JMenuItem();
        deleteUser_MenuItem = new javax.swing.JMenuItem();
        administrateQuarantines_MenuItem = new javax.swing.JMenuItem();
        indstillinger_MenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        about_MenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(his.His.title + " - " + his.His.version);
        setMinimumSize(new java.awt.Dimension(1300, 550));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        inner_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        search_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_TextField.setForeground(new java.awt.Color(153, 153, 153));
        search_TextField.setText("Søg på en persons navn/fødselsdag/adresse");
        search_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_TextFieldActionPerformed(evt);
            }
        });
        search_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_TextFieldFocusLost(evt);
            }
        });

        search_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_Button.setText("Søg");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        result_Table.setAutoCreateRowSorter(true);
        result_Table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        result_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Navn", "Adresse", "Fødselsdag", "Udløbsdato", "Oprettelsesdato", "Høne", "Reserve", "1-1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        result_Table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        result_ScrollPane.setViewportView(result_Table);

        enroll_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        enroll_Button.setText("Indskrivning");
        enroll_Button.setEnabled(false);
        enroll_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enroll_ButtonActionPerformed(evt);
            }
        });

        status_Pane.setBackground(new java.awt.Color(51, 51, 51));

        status_Label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        status_Label.setForeground(new java.awt.Color(255, 255, 255));
        status_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status_Label.setText("Ingen person valgt");

        javax.swing.GroupLayout status_PaneLayout = new javax.swing.GroupLayout(status_Pane);
        status_Pane.setLayout(status_PaneLayout);
        status_PaneLayout.setHorizontalGroup(
            status_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        status_PaneLayout.setVerticalGroup(
            status_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(status_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout picturePane_PicturePaneLayout = new javax.swing.GroupLayout(picturePane_PicturePane);
        picturePane_PicturePane.setLayout(picturePane_PicturePaneLayout);
        picturePane_PicturePaneLayout.setHorizontalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        picturePane_PicturePaneLayout.setVerticalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        renew_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        renew_Button.setText("Forny");
        renew_Button.setEnabled(false);
        renew_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renew_ButtonActionPerformed(evt);
            }
        });

        enrolled_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enrolled_CheckBox.setText("Indskrevne");
        enrolled_CheckBox.setToolTipText("Søg kun på indskrevne");

        kick_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kick_Button.setText("Smid ud");
        kick_Button.setEnabled(false);
        kick_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inner_PaneLayout = new javax.swing.GroupLayout(inner_Pane);
        inner_Pane.setLayout(inner_PaneLayout);
        inner_PaneLayout.setHorizontalGroup(
            inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inner_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(search_TextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enrolled_CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_Button))
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(result_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(status_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(renew_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enroll_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kick_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        inner_PaneLayout.setVerticalGroup(
            inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inner_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_Button)
                    .addComponent(enrolled_CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enroll_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(kick_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(renew_Button))
                    .addComponent(result_ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        createPerson_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/createPerson.png"))); // NOI18N
        createPerson_Button.setToolTipText("Opret en person");
        createPerson_Button.setFocusable(false);
        createPerson_Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createPerson_Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createPerson_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPerson_ButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(createPerson_Button);
        jToolBar1.add(filler1);

        editPerson_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/editPerson.png"))); // NOI18N
        editPerson_Button.setToolTipText("Rediger personer");
        editPerson_Button.setFocusable(false);
        editPerson_Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editPerson_Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editPerson_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPerson_ButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editPerson_Button);
        jToolBar1.add(filler2);

        deletePerson_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deletePerson.png"))); // NOI18N
        deletePerson_Button.setToolTipText("Slet personer");
        deletePerson_Button.setFocusable(false);
        deletePerson_Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deletePerson_Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deletePerson_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePerson_ButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deletePerson_Button);

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inner_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inner_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottom_Pane.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dato:");

        date_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        date_Label.setForeground(new java.awt.Color(153, 204, 0));
        date_Label.setText("DATE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Logged ind:");

        user_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_Label.setForeground(new java.awt.Color(153, 204, 0));
        user_Label.setText("USER");

        editProfile_Button.setBackground(new java.awt.Color(51, 51, 51));
        editProfile_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editProfile_Button.setText("Rediger profil");
        editProfile_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfile_ButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Antal indskrevne:");

        enrolled_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enrolled_Label.setForeground(new java.awt.Color(153, 204, 0));
        enrolled_Label.setText("ENROLLED");

        logoff_Button.setBackground(new java.awt.Color(51, 51, 51));
        logoff_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        logoff_Button.setText("Log af");
        logoff_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoff_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottom_PaneLayout = new javax.swing.GroupLayout(bottom_Pane);
        bottom_Pane.setLayout(bottom_PaneLayout);
        bottom_PaneLayout.setHorizontalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottom_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_Label)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_Label)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enrolled_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 666, Short.MAX_VALUE)
                .addComponent(editProfile_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoff_Button)
                .addContainerGap())
        );
        bottom_PaneLayout.setVerticalGroup(
            bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottom_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editProfile_Button)
                        .addComponent(logoff_Button))
                    .addGroup(bottom_PaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(bottom_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(user_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enrolled_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        menuBar_MenuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        filer_Menu.setText("FILER");
        filer_Menu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rapport_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/report.png"))); // NOI18N
        rapport_Menu.setText("Rapport");

        gemRapportIndskrevne_MenuItem.setText("Gem rapport med alle indskrevne");
        gemRapportIndskrevne_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gemRapportIndskrevne_MenuItemActionPerformed(evt);
            }
        });
        rapport_Menu.add(gemRapportIndskrevne_MenuItem);

        gemRapportPersoner_MenuItem.setText("Gem rapport med alle personer");
        gemRapportPersoner_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gemRapportPersoner_MenuItemActionPerformed(evt);
            }
        });
        rapport_Menu.add(gemRapportPersoner_MenuItem);

        gemRapportBrugere_MenuItem.setText("Gem rapport med alle brugere");
        gemRapportBrugere_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gemRapportBrugere_MenuItemActionPerformed(evt);
            }
        });
        rapport_Menu.add(gemRapportBrugere_MenuItem);

        gemKarantæner_MenuItem.setText("Gem rapport med alle karantæner");
        gemKarantæner_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gemKarantæner_MenuItemActionPerformed(evt);
            }
        });
        rapport_Menu.add(gemKarantæner_MenuItem);

        filer_Menu.add(rapport_Menu);

        vagtAfslutning_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/shitEnd.png"))); // NOI18N
        vagtAfslutning_Menu.setText("Vagt afslutning");

        nulstil_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reset.png"))); // NOI18N
        nulstil_MenuItem.setText("Nulstil systemet og gem rapport med alle indskrevne (PAS PÅ!)");
        nulstil_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nulstil_MenuItemActionPerformed(evt);
            }
        });
        vagtAfslutning_Menu.add(nulstil_MenuItem);

        filer_Menu.add(vagtAfslutning_Menu);
        filer_Menu.add(jSeparator1);

        brugere_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/user.png"))); // NOI18N
        brugere_Menu.setText("Bruger administration");

        createUser_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/createUser.png"))); // NOI18N
        createUser_MenuItem.setText("Opret en bruger");
        createUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUser_MenuItemActionPerformed(evt);
            }
        });
        brugere_Menu.add(createUser_MenuItem);

        editUser_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/editUser.png"))); // NOI18N
        editUser_MenuItem.setText("Rediger brugere");
        editUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUser_MenuItemActionPerformed(evt);
            }
        });
        brugere_Menu.add(editUser_MenuItem);

        deleteUser_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deleteUser.png"))); // NOI18N
        deleteUser_MenuItem.setText("Slet brugere");
        deleteUser_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUser_MenuItemActionPerformed(evt);
            }
        });
        brugere_Menu.add(deleteUser_MenuItem);

        filer_Menu.add(brugere_Menu);

        administrateQuarantines_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/quarantinePerson.png"))); // NOI18N
        administrateQuarantines_MenuItem.setText("Administrer karantæner");
        administrateQuarantines_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administrateQuarantines_MenuItemActionPerformed(evt);
            }
        });
        filer_Menu.add(administrateQuarantines_MenuItem);

        indstillinger_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/settings.png"))); // NOI18N
        indstillinger_MenuItem.setText("Indstillinger");
        indstillinger_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indstillinger_MenuItemActionPerformed(evt);
            }
        });
        filer_Menu.add(indstillinger_MenuItem);
        filer_Menu.add(jSeparator2);

        about_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/help.png"))); // NOI18N
        about_MenuItem.setText("Om og Hjælp");
        about_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_MenuItemActionPerformed(evt);
            }
        });
        filer_Menu.add(about_MenuItem);

        menuBar_MenuBar.add(filer_Menu);

        setJMenuBar(menuBar_MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottom_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void createUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUser_MenuItemActionPerformed
        createUserDIA.setVisible(true);
    }//GEN-LAST:event_createUser_MenuItemActionPerformed

    private void editUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUser_MenuItemActionPerformed
        editUserDIA.setVisible(true);
    }//GEN-LAST:event_editUser_MenuItemActionPerformed

    private void deleteUser_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUser_MenuItemActionPerformed
        removeUserDIA.setVisible(true);
    }//GEN-LAST:event_deleteUser_MenuItemActionPerformed

    private void search_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_TextFieldFocusGained
        if (search_TextField.getText().equals("Søg på en persons navn/fødselsdag/adresse")) {
            search_TextField.setText("");
            search_TextField.setForeground(Color.black);
        }
    }//GEN-LAST:event_search_TextFieldFocusGained

    private void search_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_TextFieldFocusLost
        if (search_TextField.getText().isEmpty()) {
            search_TextField.setText("Søg på en persons navn/fødselsdag/adresse");
            search_TextField.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_search_TextFieldFocusLost

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        search();
    }//GEN-LAST:event_search_ButtonActionPerformed

    private void indstillinger_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indstillinger_MenuItemActionPerformed
        settingsDIA.setVisible(true);
    }//GEN-LAST:event_indstillinger_MenuItemActionPerformed

    private void logoff_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoff_ButtonActionPerformed
        int n = DialogMessage.showQuestionMessage(new JDialog(), "Er du sikker på du vil logge af?", "Sikker?");
        if (n == 0) {
            dispose();
            loH.requestLogin();
        }
    }//GEN-LAST:event_logoff_ButtonActionPerformed

    private void editProfile_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfile_ButtonActionPerformed
        editProfileDIA.setUser(loggedIn);
        editProfileDIA.setVisible(true);
        setloggedInUser();
    }//GEN-LAST:event_editProfile_ButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int n = DialogMessage.showQuestionMessage(new JDialog(), "Er du sikker på du vil lukke programmet?", "Sikker?");
        if (n == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void enroll_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enroll_ButtonActionPerformed
        if (selectedPerson != -1) {
            enrollPersonDIA.setPerson(peH.getPerson(selectedPerson));
            enrollPersonDIA.setVisible(true);
            setPerson(selectedPerson);
            updateEnrolledCounter();
        }
    }//GEN-LAST:event_enroll_ButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cleanSearch();
        cleanSelectedPerson();
        cleanTable();
    }//GEN-LAST:event_formWindowClosed

    private void search_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_TextFieldActionPerformed
        search();
    }//GEN-LAST:event_search_TextFieldActionPerformed

    private void nulstil_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nulstil_MenuItemActionPerformed
        int n = DialogMessage.showQuestionMessage(new JDialog(), "Er du sikker på du vil udføre daglig nulstilling?", "Nulstille?");
        if (n == 0) {
            try {
                reH.writeTotalEnrolledReport();
                DialogMessage.showCustomMessage(this, "En rapport med alle indskrevne blev gemt", "Rapport gemt", JOptionPane.INFORMATION_MESSAGE);
            } catch (DocumentException | FileNotFoundException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            int errorCode = enH.removeAllEnrollments();
            DialogMessage.showMessage(this, errorCode);

            if (errorCode == 0) {
                DialogMessage.showMessage(this, HismHandlerIF.LOGOUT_NOTIFICATION);
                dispose();
                cleanSearch();
                cleanSelectedPerson();
                cleanTable();
                loH.requestLogin();
            }
        }
    }//GEN-LAST:event_nulstil_MenuItemActionPerformed

    private void about_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_MenuItemActionPerformed
        aboutDIA.setVisible(true);
    }//GEN-LAST:event_about_MenuItemActionPerformed

    private void createPerson_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPerson_ButtonActionPerformed
        cleanSelectedPerson();
        cleanTable();
        cleanSearch();
        createPersonDIA.setVisible(true);
    }//GEN-LAST:event_createPerson_ButtonActionPerformed

    private void editPerson_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPerson_ButtonActionPerformed
        cleanSelectedPerson();
        cleanTable();
        cleanSearch();
        editPersonDIA.setVisible(true);
    }//GEN-LAST:event_editPerson_ButtonActionPerformed

    private void deletePerson_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePerson_ButtonActionPerformed
        cleanSelectedPerson();
        cleanTable();
        cleanSearch();
        removePersonDIA.setVisible(true);
    }//GEN-LAST:event_deletePerson_ButtonActionPerformed

    private void administrateQuarantines_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administrateQuarantines_MenuItemActionPerformed
        cleanSelectedPerson();
        cleanTable();
        cleanSearch();
        quarantinePersonManagemenDIA.setVisible(true);
    }//GEN-LAST:event_administrateQuarantines_MenuItemActionPerformed

    private void kick_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kick_ButtonActionPerformed
        int n = DialogMessage.showQuestionMessage(new JFrame(), "Er du sikker på du vil smide denne person ud?", "Sikker?");
        if (n == 0) {
            int errorCode = enH.kickEnrollment(selectedPerson);
            DialogMessage.showMessage(this, errorCode);
            setPerson(selectedPerson);
        }
    }//GEN-LAST:event_kick_ButtonActionPerformed

    private void renew_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renew_ButtonActionPerformed

        renewPersonDIA.setPerson(selectedPerson);
        renewPersonDIA.setVisible(true);
        setPerson(selectedPerson);
    }//GEN-LAST:event_renew_ButtonActionPerformed

    private void gemRapportIndskrevne_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gemRapportIndskrevne_MenuItemActionPerformed
        try {
            reH.writeTotalEnrolledReport();
            DialogMessage.showCustomMessage(this, "Rapport gemt", "Gemt", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gemRapportIndskrevne_MenuItemActionPerformed

    private void gemRapportPersoner_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gemRapportPersoner_MenuItemActionPerformed
        try {
            reH.writeTotalPersonReport();
            DialogMessage.showCustomMessage(this, "Rapport gemt", "Gemt", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gemRapportPersoner_MenuItemActionPerformed

    private void gemRapportBrugere_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gemRapportBrugere_MenuItemActionPerformed
        try {
            reH.writeTotalUserReport();
            DialogMessage.showCustomMessage(this, "Rapport gemt", "Gemt", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gemRapportBrugere_MenuItemActionPerformed

    private void gemKarantæner_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gemKarantæner_MenuItemActionPerformed
        try {
            reH.writeTotalQuarantineReport();
            DialogMessage.showCustomMessage(this, "Rapport gemt", "Gemt", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gemKarantæner_MenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_MenuItem;
    private javax.swing.JMenuItem administrateQuarantines_MenuItem;
    private javax.swing.JPanel bottom_Pane;
    private javax.swing.JMenu brugere_Menu;
    private javax.swing.JButton createPerson_Button;
    private javax.swing.JMenuItem createUser_MenuItem;
    private javax.swing.JLabel date_Label;
    private javax.swing.JButton deletePerson_Button;
    private javax.swing.JMenuItem deleteUser_MenuItem;
    private javax.swing.JButton editPerson_Button;
    private javax.swing.JButton editProfile_Button;
    private javax.swing.JMenuItem editUser_MenuItem;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JCheckBox enrolled_CheckBox;
    private javax.swing.JLabel enrolled_Label;
    private javax.swing.JMenu filer_Menu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JMenuItem gemKarantæner_MenuItem;
    private javax.swing.JMenuItem gemRapportBrugere_MenuItem;
    private javax.swing.JMenuItem gemRapportIndskrevne_MenuItem;
    private javax.swing.JMenuItem gemRapportPersoner_MenuItem;
    private javax.swing.JMenuItem indstillinger_MenuItem;
    private javax.swing.JPanel inner_Pane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton kick_Button;
    private javax.swing.JButton logoff_Button;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JMenuBar menuBar_MenuBar;
    private javax.swing.JMenuItem nulstil_MenuItem;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JMenu rapport_Menu;
    private javax.swing.JButton renew_Button;
    private javax.swing.JScrollPane result_ScrollPane;
    private javax.swing.JTable result_Table;
    private javax.swing.JButton search_Button;
    private javax.swing.JTextField search_TextField;
    private javax.swing.JLabel status_Label;
    private javax.swing.JPanel status_Pane;
    private javax.swing.JLabel user_Label;
    private javax.swing.JMenu vagtAfslutning_Menu;
    // End of variables declaration//GEN-END:variables

}
