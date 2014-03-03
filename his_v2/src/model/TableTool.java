/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Person;
import entity.User;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patrick
 */
public class TableTool {

    public static final String[] userColumns = {"ID", "Navn", "Brugernavn", "Administrator", "Reserve"};
    public static final String[] personColumns = {"ID", "Navn", "Adresse", "Fødselsdag", "Udløbsdato", "Oprettelsesdato", "Høne", "Reserve", "1-1"};
    public static final String[] guestColumns = {"ID", "Navn", "fødselsdag", "Oprettelsesdato"};

    private TableTool() {
    }

    /**
     * Creates an empty table model based on user
     *
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmtpyUserTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }

        return dtm;
    }

    /**
     * Creates an empty table model based on person
     *
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmptyPersonTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < personColumns.length; i++) {
            dtm.addColumn(personColumns[i]);
        }

        return dtm;
    }

    /**
     * Creates an empty table model based on guest
     *
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createEmptyGuestTableModel() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < guestColumns.length; i++) {
            dtm.addColumn(guestColumns[i]);
        }

        return dtm;
    }

    /**
     * Creates a table model based on user
     *
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createUserTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }

        for (String[] s : data) {
            dtm.addRow(s);
        }

        return dtm;
    }

    /**
     * Creates a sorted table model based on user
     *
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createSortedUserTableModel(ArrayList<User> data) {
        Collections.sort(data, new UserComparator());
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < userColumns.length; i++) {
            dtm.addColumn(userColumns[i]);
        }

        for (User u : data) {
            String admin = "Nej";
            if (u.isAdministrator()) {
                admin = "Ja";
            }
            String reserve = "Nej";
            if (u.isReserve()) {
                reserve = "Ja";
            }
            String[] dat = {String.valueOf(u.getIduser()), u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname(), u.getUsername(), admin, reserve};
            dtm.addRow(dat);
        }

        return dtm;

    }

    /**
     * Creates a table model based on person
     *
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createPersonTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < personColumns.length; i++) {
            dtm.addColumn(personColumns[i]);
        }

        for (String[] s : data) {
            dtm.addRow(s);
        }

        return dtm;
    }

    /**
     * Creates a sorted table model based on person
     *
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createSortedPersonTableModel(ArrayList<Person> data) {
        Collections.sort(data, new PersonComparator());
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < personColumns.length; i++) {
            dtm.addColumn(personColumns[i]);
        }

        for (Person p : data) {
            String hoene = "Nej";
            if (p.isHoene()) {
                hoene = "Ja";
            }
            String oneOne = "Nej";
            if (p.isOneOne()) {
                oneOne = "Ja";
            }
            String reserve = "Nej";
            if (p.isReserve()) {
                reserve = "Ja";
            }
            String[] dat = {String.valueOf(p.getIdPerson()), p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate(), p.getExpirationDate(), p.getCreationDate(), hoene, reserve, oneOne};
            dtm.addRow(dat);
        }
        return dtm;
    }

    /**
     * Creates a table model based on guest
     *
     * @param data
     * @return dtm : DefaultTableModel
     */
    public static DefaultTableModel createGuestTableModel(ArrayList<String[]> data) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < guestColumns.length; i++) {
            dtm.addColumn(guestColumns[i]);
        }

        for (String[] s : data) {
            dtm.addRow(s);
        }

        return dtm;
    }
}
