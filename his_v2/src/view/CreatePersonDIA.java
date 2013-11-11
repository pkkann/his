/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.PersonHandler;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import view.message.DialogMessage;

/**
 *
 * @author patrick
 */
public class CreatePersonDIA extends javax.swing.JDialog {

    private PersonHandler peH;
    private String picturePath = "";

    public CreatePersonDIA(java.awt.Frame parent, boolean modal, PersonHandler peH) {
        super(parent, modal);
        initComponents();
        setTitleIcon();
        this.peH = peH;
    }

    private void setTitleIcon() {
        Image icon = null;
        try {
            icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("res/createPerson.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        titleIcon_PicturePane.setPicture(icon, false);
    }

    private void cleanFields() {
        firstname_TextField.setText("");
        middlename_TextField.setText("");
        lastname_TextField.setText("");
        address_TextField.setText("");
        birthday_day_TextField.setText("");
        birthday_month_TextField.setText("");
        birthday_year_TextField.setText("");
        expiration_month_TextField.setText("");
        expiration_year_TextField.setText("");
        hoene_CheckBox.setSelected(false);
        reserve_CheckBox.setSelected(false);
        oneOne_CheckBox.setSelected(false);

        expiration_month_TextField.setEnabled(true);
        expiration_year_TextField.setEnabled(true);
    }

    private void cleanPicture() {
        picturePane_PicturePane.setPicture(null, true);
        noPicture_CheckBox.setSelected(false);
        picturePath = "";
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
        fields_Pane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        firstname_TextField = new javax.swing.JTextField();
        middlename_TextField = new javax.swing.JTextField();
        lastname_TextField = new javax.swing.JTextField();
        address_TextField = new javax.swing.JTextField();
        birthday_day_TextField = new javax.swing.JTextField();
        birthday_month_TextField = new javax.swing.JTextField();
        birthday_year_TextField = new javax.swing.JTextField();
        expiration_month_TextField = new javax.swing.JTextField();
        expiration_year_TextField = new javax.swing.JTextField();
        hoene_CheckBox = new javax.swing.JCheckBox();
        reserve_CheckBox = new javax.swing.JCheckBox();
        oneOne_CheckBox = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        picture_Pane = new javax.swing.JPanel();
        picturePane_PicturePane = new view.image.PicturePane();
        choose_Button = new javax.swing.JButton();
        noPicture_CheckBox = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        capture_Button = new javax.swing.JButton();
        tools_Pane = new javax.swing.JPanel();
        create_Button = new javax.swing.JButton();
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

        title_Pane.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Opret en person");

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
        jLabel7.setText("Udløbsdato:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Er:");

        firstname_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        middlename_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lastname_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        address_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        birthday_day_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        birthday_month_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        birthday_year_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        expiration_month_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        expiration_year_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        hoene_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hoene_CheckBox.setText("Høne");
        hoene_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hoene_CheckBoxItemStateChanged(evt);
            }
        });

        reserve_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reserve_CheckBox.setText("Reserve");
        reserve_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                reserve_CheckBoxItemStateChanged(evt);
            }
        });

        oneOne_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        oneOne_CheckBox.setText("1-1");
        oneOne_CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                oneOne_CheckBoxItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("dd/mm/yyyy");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("mm/yyyy");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("blok-husnummer");

        javax.swing.GroupLayout fields_PaneLayout = new javax.swing.GroupLayout(fields_Pane);
        fields_Pane.setLayout(fields_PaneLayout);
        fields_PaneLayout.setHorizontalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstname_TextField)
                            .addComponent(middlename_TextField)
                            .addComponent(lastname_TextField)
                            .addComponent(address_TextField)
                            .addGroup(fields_PaneLayout.createSequentialGroup()
                                .addComponent(birthday_day_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(expiration_month_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(birthday_month_TextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(fields_PaneLayout.createSequentialGroup()
                                        .addComponent(expiration_year_TextField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(fields_PaneLayout.createSequentialGroup()
                                        .addComponent(birthday_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addGroup(fields_PaneLayout.createSequentialGroup()
                        .addComponent(hoene_CheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(reserve_CheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(oneOne_CheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fields_PaneLayout.setVerticalGroup(
            fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fields_PaneLayout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(address_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
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
                    .addComponent(expiration_month_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiration_year_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fields_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(hoene_CheckBox)
                    .addComponent(reserve_CheckBox)
                    .addComponent(oneOne_CheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout picturePane_PicturePaneLayout = new javax.swing.GroupLayout(picturePane_PicturePane);
        picturePane_PicturePane.setLayout(picturePane_PicturePaneLayout);
        picturePane_PicturePaneLayout.setHorizontalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        picturePane_PicturePaneLayout.setVerticalGroup(
            picturePane_PicturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        choose_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        choose_Button.setText("Vælg billed");
        choose_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_ButtonActionPerformed(evt);
            }
        });

        noPicture_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        noPicture_CheckBox.setText("Intet billed");
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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("For bedst resultat, skal billedet være 250x250");

        capture_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        capture_Button.setText("Tag billed");
        capture_Button.setEnabled(false);

        javax.swing.GroupLayout picture_PaneLayout = new javax.swing.GroupLayout(picture_Pane);
        picture_Pane.setLayout(picture_PaneLayout);
        picture_PaneLayout.setHorizontalGroup(
            picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picture_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(picture_PaneLayout.createSequentialGroup()
                        .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noPicture_CheckBox)
                            .addGroup(picture_PaneLayout.createSequentialGroup()
                                .addComponent(choose_Button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(capture_Button)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        picture_PaneLayout.setVerticalGroup(
            picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picture_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(picture_PaneLayout.createSequentialGroup()
                        .addGroup(picture_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choose_Button)
                            .addComponent(capture_Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noPicture_CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addComponent(picturePane_PicturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        create_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        create_Button.setText("Opret person");
        create_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_ButtonActionPerformed(evt);
            }
        });

        cancel_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancel_Button.setText("Annuller");
        cancel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tools_PaneLayout = new javax.swing.GroupLayout(tools_Pane);
        tools_Pane.setLayout(tools_PaneLayout);
        tools_PaneLayout.setHorizontalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancel_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(create_Button)
                .addContainerGap())
        );
        tools_PaneLayout.setVerticalGroup(
            tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tools_PaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tools_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(create_Button)
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
                    .addComponent(picture_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tools_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fields_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(picture_Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void cancel_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_ButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancel_ButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cleanFields();
        cleanPicture();
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

    private void create_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_ButtonActionPerformed
        boolean hoene = hoene_CheckBox.isSelected();
        boolean reserve = reserve_CheckBox.isSelected();
        boolean oneOne = oneOne_CheckBox.isSelected();
        String firstname = firstname_TextField.getText();
        String middlename = middlename_TextField.getText();
        String lastname = lastname_TextField.getText();
        String address = address_TextField.getText();
        String birthday = birthday_day_TextField.getText() + "/" + birthday_month_TextField.getText() + "/" + birthday_year_TextField.getText();
        String expiration;
        if (!hoene && !reserve && !oneOne) {
            expiration = expiration_month_TextField.getText() + "/" + expiration_year_TextField.getText();
        } else {
            expiration = "";
        }
        if(noPicture_CheckBox.isSelected()) {
            picturePath = "N";
        }
        Calendar c = Calendar.getInstance();
        String creationDate = String.valueOf(c.get(Calendar.DATE)) + "/" + String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));

        ArrayList<String[]> result = peH.searchPersonLon(firstname, middlename, lastname, address, birthday);

        if (result.isEmpty()) {
            int errorCode = peH.createPerson(firstname, middlename, lastname, address, birthday, expiration, creationDate, hoene, reserve, oneOne, picturePath);
            DialogMessage.showMessage(this, errorCode);

            if (errorCode == 0) {
                dispose();
            }
        } else {
            String persons = "";
            for (String[] ss : result) {
                persons = persons + " - Navn: " + ss[0] + " || Adresse: " + ss[1] + " || Fødselsdag: " + ss[2] + "\n";
            }

            int n = DialogMessage.showQuestionMessage(this, "Denne person er måske allerede i systemet. Vil du oprette alligevel?\n\nPersoner fundet er:\n" + persons, "Sikker?");
            if (n == 0) {
                System.out.println(picturePath);
                int errorCode = peH.createPerson(firstname, middlename, lastname, address, birthday, expiration, creationDate, hoene, reserve, oneOne, picturePath);
                DialogMessage.showMessage(this, errorCode);

                if (errorCode == 0) {
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_create_ButtonActionPerformed

    private void choose_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_ButtonActionPerformed
        noPicture_CheckBox.setSelected(false);
        picturePath = "";
        int returnVal = fileChooser_FileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            picturePath = fileChooser_FileChooser.getSelectedFile().toString();
            File f = new File(picturePath);
            Image img = null;
            try {
                img = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(CreatePersonDIA.class.getName()).log(Level.SEVERE, null, ex);
            }
            picturePane_PicturePane.setPicture(img, true);
        }
    }//GEN-LAST:event_choose_ButtonActionPerformed

    private void noPicture_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPicture_CheckBoxActionPerformed
        if(noPicture_CheckBox.isSelected()) {
            picturePath = "N";
            picturePane_PicturePane.setPicture(null, true);
            choose_Button.setEnabled(false);
        } else {
            picturePath = "";
            choose_Button.setEnabled(true);
        }
    }//GEN-LAST:event_noPicture_CheckBoxActionPerformed

    private void noPicture_CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noPicture_CheckBoxItemStateChanged
        //
    }//GEN-LAST:event_noPicture_CheckBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address_TextField;
    private javax.swing.JTextField birthday_day_TextField;
    private javax.swing.JTextField birthday_month_TextField;
    private javax.swing.JTextField birthday_year_TextField;
    private javax.swing.JButton cancel_Button;
    private javax.swing.JButton capture_Button;
    private javax.swing.JButton choose_Button;
    private javax.swing.JButton create_Button;
    private javax.swing.JTextField expiration_month_TextField;
    private javax.swing.JTextField expiration_year_TextField;
    private javax.swing.JPanel fields_Pane;
    private javax.swing.JFileChooser fileChooser_FileChooser;
    private javax.swing.JTextField firstname_TextField;
    private javax.swing.JCheckBox hoene_CheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lastname_TextField;
    private javax.swing.JPanel main_Pane;
    private javax.swing.JTextField middlename_TextField;
    private javax.swing.JCheckBox noPicture_CheckBox;
    private javax.swing.JCheckBox oneOne_CheckBox;
    private view.image.PicturePane picturePane_PicturePane;
    private javax.swing.JPanel picture_Pane;
    private javax.swing.JCheckBox reserve_CheckBox;
    private view.image.PicturePane titleIcon_PicturePane;
    private javax.swing.JPanel title_Pane;
    private javax.swing.JPanel tools_Pane;
    // End of variables declaration//GEN-END:variables
}
