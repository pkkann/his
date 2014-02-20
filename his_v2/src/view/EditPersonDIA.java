/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.EnrollmentHandler;
import control.PersonHandler;
import entity.Person;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.TableTool;
import view.message.DialogMessage;
import webcam.WebcamTool;

/**
 *
 * @author patrick
 */
public class EditPersonDIA extends javax.swing.JDialog {

    private int selectedPerson = -1;
    private PersonHandler peH;
    private EnrollmentHandler enH;
    private String newPicturePath = "";
    private boolean canEdit = false;
    private boolean pictureChanged = false;

    public EditPersonDIA(java.awt.Frame parent, boolean modal, PersonHandler peH, EnrollmentHandler enH) {
        super(parent, modal);
        initComponents();
        initTableListener();
        setTitleIcon();
        this.peH = peH;
        this.enH = enH;
    }

    private void initTableListener() {
        result_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && result_Table.getSelectedRowCount() != 0) {
                    int selRow = result_Table.getSelectedRow();
                    Object selIDObject = result_Table.getModel().getValueAt(selRow, 0);
                    int selID = Integer.valueOf(String.valueOf(selIDObject));
                    setSelectedPerson(selID);
                }
            }
        });
    }
    
    public void setPicturePath(String path) {
        this.newPicturePath = path;
        pictureChanged = true;
    }

    public void setPicturePanel() {
        File f = new File(newPicturePath);
        Image img = null;
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(CreatePersonDIA.class.getName()).log(Level.SEVERE, null, ex);
        }
        picturePane_PicturePane.setPicture(img, true);
    }

    private void setSelectedPerson(int idPerson) {
        cleanSelectedPerson();
        selectedPerson = idPerson;

        Person p = peH.getPerson(idPerson);

        if (enH.isEnrolled(idPerson)) {
            canEdit = false;
        } else {
            canEdit = true;
        }

        if (p != null) {
            if (canEdit) {
                firstname_TextField.setText(p.getFirstname());
                firstname_TextField.setEnabled(true);

                middlename_TextField.setText(p.getMiddlename());
                middlename_TextField.setEnabled(true);

                lastname_TextField.setText(p.getLastname());
                lastname_TextField.setEnabled(true);

                address_TextField.setText(p.getAddress());
                address_TextField.setEnabled(true);

                hoene_CheckBox.setSelected(p.isHoene());
                hoene_CheckBox.setEnabled(true);

                reserve_CheckBox.setSelected(p.isReserve());
                reserve_CheckBox.setEnabled(true);

                oneOne_CheckBox.setSelected(p.isOneOne());
                oneOne_CheckBox.setEnabled(true);

                String[] birthSplit = p.getBirthdayDate().split("/");
                birthday_day_TextField.setText(birthSplit[0]);
                birthday_day_TextField.setEnabled(true);
                birthday_month_TextField.setText(birthSplit[1]);
                birthday_month_TextField.setEnabled(true);
                birthday_year_TextField.setText(birthSplit[2]);
                birthday_year_TextField.setEnabled(true);

                if (!p.isHoene() && !p.isReserve() && !p.isOneOne()) {
                    String[] expireSplit = p.getExpirationDate().split("/");
                    expiration_month_TextField.setText(expireSplit[0]);
                    expiration_month_TextField.setEnabled(true);
                    expiration_year_TextField.setText(expireSplit[1]);
                    expiration_year_TextField.setEnabled(true);
                }

                noPicture_CheckBox.setEnabled(true);
                if (p.getPicturePath().equals("N")) {
                    noPicture_CheckBox.setSelected(true);
                } else {
                    noPicture_CheckBox.setSelected(false);
                    choosePic_Button.setEnabled(true);
                    capturePic_Button.setEnabled(true);
                    try {
                        Image img = ImageIO.read(new File(p.getPicturePath()));
                        picturePane_PicturePane.setPicture(img, true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                save_Button.setEnabled(true);
            } else {
                info_Label.setText("Personen er indskrevet, og kan ikke redigeres");
            }
        }
    }

    private void setTitleIcon() {
        Image icon = null;
        try {
            icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/editPerson.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        titleIcon_PicturePane.setPicture(icon, false);
    }

    private void cleanSearchField() {
        search_TextField.setText("");
    }

    private void cleanTable() {
        DefaultTableModel dtm = TableTool.createEmptyPersonTableModel();
        result_Table.setModel(dtm);
    }

    public void cleanSelectedPerson() {
        selectedPerson = -1;
        save_Button.setEnabled(false);
        info_Label.setText("");

        firstname_TextField.setText("");
        firstname_TextField.setEnabled(false);

        middlename_TextField.setText("");
        middlename_TextField.setEnabled(false);

        lastname_TextField.setText("");
        lastname_TextField.setEnabled(false);

        address_TextField.setText("");
        address_TextField.setEnabled(false);

        birthday_day_TextField.setText("");
        birthday_day_TextField.setEnabled(false);
        birthday_month_TextField.setText("");
        birthday_month_TextField.setEnabled(false);
        birthday_year_TextField.setText("");
        birthday_year_TextField.setEnabled(false);
        
        hoene_CheckBox.setSelected(false);
        hoene_CheckBox.setEnabled(false);

        reserve_CheckBox.setSelected(false);
        reserve_CheckBox.setEnabled(false);

        oneOne_CheckBox.setSelected(false);
        oneOne_CheckBox.setEnabled(false);

        expiration_month_TextField.setText("");
        expiration_month_TextField.setEnabled(false);
        expiration_year_TextField.setText("");
        expiration_year_TextField.setEnabled(false);

        noPicture_CheckBox.setSelected(false);
        noPicture_CheckBox.setEnabled(false);
        picturePane_PicturePane.setPicture(null, true);
        choosePic_Button.setEnabled(false);
        capturePic_Button.setEnabled(false);
        
        pictureChanged = false;

    }

    public void search() {
        cleanSelectedPerson();
        ArrayList<String[]> data = peH.searchPerson(search_TextField.getText(), false);
        DefaultTableModel dtm = TableTool.createPersonTableModel(data);
        result_Table.setModel(dtm);
    }
    
    public boolean shouldExpireDateBeEnabled() {
        if (hoene_CheckBox.isSelected() || reserve_CheckBox.isSelected() || oneOne_CheckBox.isSelected()) {
            return false;
        } else {
            return true;
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

        fileChooser_FileChooser = new javax.swing.JFileChooser();
        main_Pane = new javax.swing.JPanel();
        title_Pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titleIcon_PicturePane = new view.image.PicturePane();
        search_Pane = new javax.swing.JPanel();
        search_TextField = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        result_Table = new javax.swing.JTable();
        fields_Pane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        firstname_TextField = new javax.swing.JTextField();
        middlename_TextField = new javax.swing.JTextField();
        lastname_TextField = new javax.swing.JTextField();
        address_TextField = new javax.swing.JTextField();
        picturePane_PicturePane = new view.image.PicturePane();
        jPanel5 = new javax.swing.JPanel();
        choosePic_Button = new javax.swing.JButton();
        capturePic_Button = new javax.swing.JButton();
        noPicture_CheckBox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        birthday_day_TextField = new javax.swing.JTextField();
        birthday_month_TextField = new javax.swing.JTextField();
        birthday_year_TextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        hoene_CheckBox = new javax.swing.JCheckBox();
        reserve_CheckBox = new javax.swing.JCheckBox();
        oneOne_CheckBox = new javax.swing.JCheckBox();
        save_Button = new javax.swing.JButton();
        info_Label = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        expiration_month_TextField = new javax.swing.JTextField();
        expiration_year_TextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tools_Pane = new javax.swing.JPanel();
        close_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        main_Pane.setBackground(new java.awt.Color(51, 51, 51));

        title_Pane.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rediger en person");

        javax.swing.GroupLayout titleIcon_PicturePaneLayout = new javax.swing.GroupLayout(titleIcon_PicturePane);
        titleIcon_PicturePane.setLayout(titleIcon_PicturePaneLayout);
        titleIcon_PicturePaneLayout.setHorizontalGroup(
            titleIcon_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        titleIcon_PicturePaneLayout.setVerticalGroup(
            titleIcon_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout title_PaneLayout = new javax.swing.GroupLayout(title_Pane);
        title_Pane.setLayout(title_PaneLayout);
        title_PaneLayout.setHorizontalGroup(
            title_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleIcon_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        title_PaneLayout.setVerticalGroup(
            title_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(title_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(title_PaneLayout.createSequentialGroup()
                        .addComponent(titleIcon_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        search_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_TextFieldActionPerformed(evt);
            }
        });

        search_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_Button.setText("Søg");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        result_Table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        result_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Navn", "Adresse", "Fødselsdag", "Udløbsdato", "Oprettelsesdato", "Høne", "Reserve", "1-1"
            }
        ));
        result_Table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(result_Table);

        javax.swing.GroupLayout search_PaneLayout = new javax.swing.GroupLayout(search_Pane);
        search_Pane.setLayout(search_PaneLayout);
        search_PaneLayout.setHorizontalGroup(
            search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(search_PaneLayout.createSequentialGroup()
                        .addComponent(search_TextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Fornavn:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mellemnavn:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Efternavn:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Adresse:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Fødselsdag:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Er:");

        firstname_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstname_TextField.setEnabled(false);

        middlename_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        middlename_TextField.setEnabled(false);

        lastname_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastname_TextField.setEnabled(false);

        address_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        address_TextField.setEnabled(false);

        javax.swing.GroupLayout picturePane_PicturePaneLayout = new javax.swing.GroupLayout(picturePane_PicturePane);
        picturePane_PicturePane.setLayout(picturePane_PicturePaneLayout);
        picturePane_PicturePaneLayout.setHorizontalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        picturePane_PicturePaneLayout.setVerticalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        choosePic_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        choosePic_Button.setText("Vælg nyt billed");
        choosePic_Button.setEnabled(false);
        choosePic_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePic_ButtonActionPerformed(evt);
            }
        });

        capturePic_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        capturePic_Button.setText("Tag nyt billed");
        capturePic_Button.setEnabled(false);
        capturePic_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capturePic_ButtonActionPerformed(evt);
            }
        });

        noPicture_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        noPicture_CheckBox.setText("Intet billed");
        noPicture_CheckBox.setEnabled(false);
        noPicture_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                noPicture_CheckBoxItemStateChanged(evt);
            }
        });
        noPicture_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noPicture_CheckBoxActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("For bedst resultat, skal billedet være 250x250");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(choosePic_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capturePic_Button))
                    .addComponent(noPicture_CheckBox)
                    .addComponent(jLabel8))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choosePic_Button)
                    .addComponent(capturePic_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noPicture_CheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        birthday_day_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        birthday_day_TextField.setEnabled(false);

        birthday_month_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        birthday_month_TextField.setEnabled(false);

        birthday_year_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        birthday_year_TextField.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("dd/mm/yyyy");

        hoene_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hoene_CheckBox.setText("Høne");
        hoene_CheckBox.setEnabled(false);
        hoene_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hoene_CheckBoxItemStateChanged(evt);
            }
        });

        reserve_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserve_CheckBox.setText("Reserve");
        reserve_CheckBox.setEnabled(false);
        reserve_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                reserve_CheckBoxItemStateChanged(evt);
            }
        });

        oneOne_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        oneOne_CheckBox.setText("1-1");
        oneOne_CheckBox.setEnabled(false);
        oneOne_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                oneOne_CheckBoxItemStateChanged(evt);
            }
        });

        save_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save_Button.setText("Gem person");
        save_Button.setEnabled(false);
        save_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_ButtonActionPerformed(evt);
            }
        });

        info_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        info_Label.setForeground(new java.awt.Color(204, 0, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Udløbsdato:");

        expiration_month_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expiration_month_TextField.setEnabled(false);

        expiration_year_TextField.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("mm/yyyy");

        javax.swing.GroupLayout fields_PaneLayout = new javax.swing.GroupLayout(fields_Pane);
        fields_Pane.setLayout(fields_PaneLayout);
        fields_PaneLayout.setHorizontalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(info_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(save_Button))
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fields_PaneLayout.createSequentialGroup()
                                .addComponent(hoene_CheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(reserve_CheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(oneOne_CheckBox))
                            .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(firstname_TextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(middlename_TextField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lastname_TextField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(address_TextField, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(fields_PaneLayout.createSequentialGroup()
                                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(expiration_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(fields_PaneLayout.createSequentialGroup()
                                        .addComponent(birthday_day_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(birthday_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(birthday_year_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(expiration_year_TextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fields_PaneLayout.setVerticalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(firstname_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(middlename_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lastname_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(expiration_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expiration_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(birthday_day_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthday_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthday_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(hoene_CheckBox)
                            .addComponent(reserve_CheckBox)
                            .addComponent(oneOne_CheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fields_PaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(info_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save_Button, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        close_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        close_Button.setText("Luk");
        close_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tools_PaneLayout = new javax.swing.GroupLayout(tools_Pane);
        tools_Pane.setLayout(tools_PaneLayout);
        tools_PaneLayout.setHorizontalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close_Button)
                .addContainerGap())
        );
        tools_PaneLayout.setVerticalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close_Button)
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
                    .addComponent(search_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(search_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fields_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tools_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void close_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_close_ButtonActionPerformed

    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        search();
    }//GEN-LAST:event_search_ButtonActionPerformed

    private void search_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_TextFieldActionPerformed
        search();
    }//GEN-LAST:event_search_TextFieldActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cleanSelectedPerson();
        cleanSearchField();
        cleanTable();
    }//GEN-LAST:event_formWindowClosed

    private void hoene_CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hoene_CheckBoxItemStateChanged
        boolean b = shouldExpireDateBeEnabled();
        expiration_month_TextField.setEnabled(b);
        expiration_year_TextField.setEnabled(b);
    }//GEN-LAST:event_hoene_CheckBoxItemStateChanged

    private void reserve_CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_reserve_CheckBoxItemStateChanged
        boolean b = shouldExpireDateBeEnabled();
        expiration_month_TextField.setEnabled(b);
        expiration_year_TextField.setEnabled(b);
    }//GEN-LAST:event_reserve_CheckBoxItemStateChanged

    private void oneOne_CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_oneOne_CheckBoxItemStateChanged
        boolean b = shouldExpireDateBeEnabled();
        expiration_month_TextField.setEnabled(b);
        expiration_year_TextField.setEnabled(b);
    }//GEN-LAST:event_oneOne_CheckBoxItemStateChanged

    private void noPicture_CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noPicture_CheckBoxItemStateChanged
        if (noPicture_CheckBox.isSelected()) {
            choosePic_Button.setEnabled(false);
            capturePic_Button.setEnabled(false);
            picturePane_PicturePane.setPicture(null, true);
            newPicturePath = "N";
            pictureChanged = true;
        } else {
            choosePic_Button.setEnabled(true);
            capturePic_Button.setEnabled(true);
            newPicturePath = "";
            pictureChanged = true;
        }
    }//GEN-LAST:event_noPicture_CheckBoxItemStateChanged

    private void choosePic_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePic_ButtonActionPerformed
        newPicturePath = "";
        int returnVal = fileChooser_FileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            newPicturePath = fileChooser_FileChooser.getSelectedFile().toString();
            File f = new File(newPicturePath);
            Image img = null;
            try {
                img = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(CreatePersonDIA.class.getName()).log(Level.SEVERE, null, ex);
            }
            picturePane_PicturePane.setPicture(img, true);
            pictureChanged = true;
        }
    }//GEN-LAST:event_choosePic_ButtonActionPerformed

    private void save_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_ButtonActionPerformed
        boolean hoene = hoene_CheckBox.isSelected();
        boolean reserve = reserve_CheckBox.isSelected();
        boolean oneOne = oneOne_CheckBox.isSelected();
        String firstname = firstname_TextField.getText();
        String middlename = middlename_TextField.getText();
        String lastname = lastname_TextField.getText();
        String address = address_TextField.getText();
        String expiration = "";
        
        if (!hoene && !reserve && !oneOne) {
            expiration = expiration_month_TextField.getText() + "/" + expiration_year_TextField.getText();
        }
        
        String birthday = birthday_day_TextField.getText() + "/" + birthday_month_TextField.getText() + "/" + birthday_year_TextField.getText();
        
        int errorCode = 0;
        System.out.println(pictureChanged);
        if(pictureChanged) {
            errorCode = peH.savePerson(selectedPerson, firstname, middlename, lastname, address, birthday, expiration, hoene, reserve, oneOne, newPicturePath);
        } else {
            errorCode = peH.savePerson(selectedPerson, firstname, middlename, lastname, address, birthday, expiration, hoene, reserve, oneOne);
        }
        
        DialogMessage.showMessage(this, errorCode);
        
        if(errorCode == 0) {
            cleanSelectedPerson();
            search();
        }
        
        
    }//GEN-LAST:event_save_ButtonActionPerformed

    private void capturePic_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capturePic_ButtonActionPerformed
        try {
            WebcamTool.spawnWebcamFrame(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(CreatePersonDIA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_capturePic_ButtonActionPerformed

    private void noPicture_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPicture_CheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noPicture_CheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address_TextField;
    private javax.swing.JTextField birthday_day_TextField;
    private javax.swing.JTextField birthday_month_TextField;
    private javax.swing.JTextField birthday_year_TextField;
    private javax.swing.JButton capturePic_Button;
    private javax.swing.JButton choosePic_Button;
    private javax.swing.JButton close_Button;
    private javax.swing.JTextField expiration_month_TextField;
    private javax.swing.JTextField expiration_year_TextField;
    private javax.swing.JPanel fields_Pane;
    private javax.swing.JFileChooser fileChooser_FileChooser;
    private javax.swing.JTextField firstname_TextField;
    private javax.swing.JCheckBox hoene_CheckBox;
    private javax.swing.JLabel info_Label;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastname_TextField;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JTextField middlename_TextField;
    private javax.swing.JCheckBox noPicture_CheckBox;
    private javax.swing.JCheckBox oneOne_CheckBox;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JCheckBox reserve_CheckBox;
    private javax.swing.JTable result_Table;
    private javax.swing.JButton save_Button;
    private javax.swing.JButton search_Button;
    private javax.swing.JPanel search_Pane;
    private javax.swing.JTextField search_TextField;
    private view.image.PicturePane titleIcon_PicturePane;
    private javax.swing.JPanel title_Pane;
    private javax.swing.JPanel tools_Pane;
    // End of variables declaration//GEN-END:variables
}
