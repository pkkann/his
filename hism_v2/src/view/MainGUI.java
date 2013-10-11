/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.EnrollmentHandler;
import control.PersonHandler;
import control.QuarantineHandler;
import entity.Person;
import entity.Quarantine;
import hism.Hism;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patrick
 */
public class MainGUI extends javax.swing.JFrame {

    // Control
    private PersonHandler peH;
    private EnrollmentHandler enH;
    private QuarantineHandler quH;
    // View
    private RemoveUserDIA removeUserDIA;
    private EditUserDIA editUserDIA;
    private CreateUserDIA createUserDIA;
    // Model
    private int selectedPerson = -1;

    public MainGUI(PersonHandler peH, EnrollmentHandler enH, QuarantineHandler quH, RemoveUserDIA removeUserDIA, EditUserDIA editUserDIA, CreateUserDIA createUserDIA) {
        initComponents();
        initTableListener();
        setIcon();
        this.peH = peH;
        this.enH = enH;
        this.quH = quH;
        this.removeUserDIA = removeUserDIA;
        this.editUserDIA = editUserDIA;
        this.createUserDIA = createUserDIA;

        DefaultTableModel dtm = (DefaultTableModel) result_Table.getModel();

        search_Button.requestFocus();
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
        Person p = peH.getPerson(id);

        if (enH.isEnrolled(id)) {
            status_Label.setText("Personen er indskrevet");
            status_Label.setForeground(Color.black);
            status_Pane.setBackground(new Color(153, 204, 0));
        } else {
            status_Label.setText("Personen er ikke indskrevet");
            status_Label.setForeground(Color.white);
            status_Pane.setBackground(new Color(51, 51, 51));
        }



        if (p.getPicturePath().equals("N")) {
            Image icon = null;
            try {
                icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/billedid.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            picturePane_PicturePane.setPicture(icon);
        } else {
            try {
                Image img = ImageIO.read(new File(p.getPicturePath()));
                picturePane_PicturePane.setPicture(img);
            } catch (IOException ex) {
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (quH.isQuarantined(id)) {
            Quarantine q = quH.getQuarantine(id);
            if (q.getQuarantineExpireDate().isEmpty()) {
                status_Label.setText("Personen har karantæne på ubestemt tid");
            } else {
                status_Label.setText("Personen har karantæne til " + q.getQuarantineExpireDate());
            }
            status_Label.setForeground(Color.white);
            status_Pane.setBackground(Color.red);
            enroll_Button.setEnabled(false);
            renew_Button.setEnabled(false);
        } else {
            enroll_Button.setEnabled(true);
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
        picturePane_PicturePane.setPicture(null);
        enroll_Button.setEnabled(false);
        renew_Button.setEnabled(false);
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
        ArrayList<String[]> data = peH.searchPerson(searchString);
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

        jCheckBox1 = new javax.swing.JCheckBox();
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
        indstillinger_MenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        luk_MenuItem = new javax.swing.JMenuItem();
        brugere_Menu = new javax.swing.JMenu();
        createUser_MenuItem = new javax.swing.JMenuItem();
        editUser_MenuItem = new javax.swing.JMenuItem();
        deleteUser_MenuItem = new javax.swing.JMenuItem();
        personer_Menu = new javax.swing.JMenu();
        createPerson_MenuItem = new javax.swing.JMenuItem();
        editPerson_MenuItem = new javax.swing.JMenuItem();
        deletePerson_MenuItem = new javax.swing.JMenuItem();
        administrateQuarantines_MenuItem = new javax.swing.JMenuItem();
        help_Menu = new javax.swing.JMenu();
        troll_MenuItem = new javax.swing.JMenuItem();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Hism.title + " - " + Hism.version);
        setMinimumSize(new java.awt.Dimension(1300, 550));

        inner_Pane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        search_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_TextField.setForeground(new java.awt.Color(153, 153, 153));
        search_TextField.setText("Søg på en persons navn/fødselsdag/adresse");
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

        status_Pane.setBackground(new java.awt.Color(51, 51, 51));

        status_Label.setForeground(new java.awt.Color(255, 255, 255));
        status_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status_Label.setText("Ingen person valgt");

        javax.swing.GroupLayout status_PaneLayout = new javax.swing.GroupLayout(status_Pane);
        status_Pane.setLayout(status_PaneLayout);
        status_PaneLayout.setHorizontalGroup(
            status_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGap(0, 240, Short.MAX_VALUE)
        );

        renew_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        renew_Button.setText("Forny");
        renew_Button.setEnabled(false);

        javax.swing.GroupLayout inner_PaneLayout = new javax.swing.GroupLayout(inner_Pane);
        inner_Pane.setLayout(inner_PaneLayout);
        inner_PaneLayout.setHorizontalGroup(
            inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inner_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(search_TextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button))
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(result_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enroll_Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(status_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(renew_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        inner_PaneLayout.setVerticalGroup(
            inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inner_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inner_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inner_PaneLayout.createSequentialGroup()
                        .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enroll_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(renew_Button))
                    .addComponent(result_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout main_PaneLayout = new javax.swing.GroupLayout(main_Pane);
        main_Pane.setLayout(main_PaneLayout);
        main_PaneLayout.setHorizontalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inner_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        main_PaneLayout.setVerticalGroup(
            main_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_PaneLayout.createSequentialGroup()
                .addContainerGap()
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
        user_Label.setText("USER.NAME");

        editProfile_Button.setBackground(new java.awt.Color(51, 51, 51));
        editProfile_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editProfile_Button.setText("Rediger profil");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Antal indskrevne:");

        enrolled_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enrolled_Label.setForeground(new java.awt.Color(153, 204, 0));
        enrolled_Label.setText("ENROLLED");

        logoff_Button.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        logoff_Button.setText("Log af");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        filer_Menu.setText("Filer");
        filer_Menu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rapport_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/report.png"))); // NOI18N
        rapport_Menu.setText("Rapport");

        gemRapportIndskrevne_MenuItem.setText("Gem rapport med alle indskrevne");
        rapport_Menu.add(gemRapportIndskrevne_MenuItem);

        gemRapportPersoner_MenuItem.setText("Gem rapport med alle personer");
        rapport_Menu.add(gemRapportPersoner_MenuItem);

        gemRapportBrugere_MenuItem.setText("Gem rapport med alle brugere");
        rapport_Menu.add(gemRapportBrugere_MenuItem);

        gemKarantæner_MenuItem.setText("Gem rapport med alle karantæner");
        rapport_Menu.add(gemKarantæner_MenuItem);

        filer_Menu.add(rapport_Menu);

        vagtAfslutning_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/shitEnd.png"))); // NOI18N
        vagtAfslutning_Menu.setText("Vagt afslutning");

        nulstil_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reset.png"))); // NOI18N
        nulstil_MenuItem.setText("Nulstil systemet og gem rapport med alle indskrevne (PAS PÅ!)");
        vagtAfslutning_Menu.add(nulstil_MenuItem);

        filer_Menu.add(vagtAfslutning_Menu);
        filer_Menu.add(jSeparator1);

        indstillinger_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/settings.png"))); // NOI18N
        indstillinger_MenuItem.setText("Indstillinger");
        filer_Menu.add(indstillinger_MenuItem);
        filer_Menu.add(jSeparator2);

        luk_MenuItem.setText("Luk");
        luk_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luk_MenuItemActionPerformed(evt);
            }
        });
        filer_Menu.add(luk_MenuItem);

        menuBar_MenuBar.add(filer_Menu);

        brugere_Menu.setText("Brugere");
        brugere_Menu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        menuBar_MenuBar.add(brugere_Menu);

        personer_Menu.setText("Personer");
        personer_Menu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        createPerson_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        createPerson_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/createPerson.png"))); // NOI18N
        createPerson_MenuItem.setText("Opret person");
        createPerson_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPerson_MenuItemActionPerformed(evt);
            }
        });
        personer_Menu.add(createPerson_MenuItem);

        editPerson_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/editPerson.png"))); // NOI18N
        editPerson_MenuItem.setText("Rediger person");
        personer_Menu.add(editPerson_MenuItem);

        deletePerson_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deletePerson.png"))); // NOI18N
        deletePerson_MenuItem.setText("Slet person");
        personer_Menu.add(deletePerson_MenuItem);

        administrateQuarantines_MenuItem.setText("Administrer karantæner");
        personer_Menu.add(administrateQuarantines_MenuItem);

        menuBar_MenuBar.add(personer_Menu);

        help_Menu.setText("Hjælp");

        troll_MenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/trollface.png"))); // NOI18N
        troll_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                troll_MenuItemActionPerformed(evt);
            }
        });
        help_Menu.add(troll_MenuItem);

        menuBar_MenuBar.add(help_Menu);

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

    private void createPerson_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPerson_MenuItemActionPerformed
        cleanSelectedPerson();
        cleanTable();
        cleanSearch();
    }//GEN-LAST:event_createPerson_MenuItemActionPerformed

    private void luk_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luk_MenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_luk_MenuItemActionPerformed

    private void troll_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_troll_MenuItemActionPerformed
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("http://www.youtube.com/watch?v=o1eHKf-dMwo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_troll_MenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem administrateQuarantines_MenuItem;
    private javax.swing.JPanel bottom_Pane;
    private javax.swing.JMenu brugere_Menu;
    private javax.swing.JMenuItem createPerson_MenuItem;
    private javax.swing.JMenuItem createUser_MenuItem;
    private javax.swing.JLabel date_Label;
    private javax.swing.JMenuItem deletePerson_MenuItem;
    private javax.swing.JMenuItem deleteUser_MenuItem;
    private javax.swing.JMenuItem editPerson_MenuItem;
    private javax.swing.JButton editProfile_Button;
    private javax.swing.JMenuItem editUser_MenuItem;
    private javax.swing.JButton enroll_Button;
    private javax.swing.JLabel enrolled_Label;
    private javax.swing.JMenu filer_Menu;
    private javax.swing.JMenuItem gemKarantæner_MenuItem;
    private javax.swing.JMenuItem gemRapportBrugere_MenuItem;
    private javax.swing.JMenuItem gemRapportIndskrevne_MenuItem;
    private javax.swing.JMenuItem gemRapportPersoner_MenuItem;
    private javax.swing.JMenu help_Menu;
    private javax.swing.JMenuItem indstillinger_MenuItem;
    private javax.swing.JPanel inner_Pane;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton logoff_Button;
    private javax.swing.JMenuItem luk_MenuItem;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JMenuBar menuBar_MenuBar;
    private javax.swing.JMenuItem nulstil_MenuItem;
    private javax.swing.JMenu personer_Menu;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JMenu rapport_Menu;
    private javax.swing.JButton renew_Button;
    private javax.swing.JScrollPane result_ScrollPane;
    private javax.swing.JTable result_Table;
    private javax.swing.JButton search_Button;
    private javax.swing.JTextField search_TextField;
    private javax.swing.JLabel status_Label;
    private javax.swing.JPanel status_Pane;
    private javax.swing.JMenuItem troll_MenuItem;
    private javax.swing.JLabel user_Label;
    private javax.swing.JMenu vagtAfslutning_Menu;
    // End of variables declaration//GEN-END:variables
}
