package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Patrick Kann
 */
public class DBTool {

    private static Connection instanceHISM = null;
    private static Connection instanceHIS = null;

    /**
     * private constructor (this class is singleton)
     */
    private DBTool() {
    }

    /**
     * Checks if a connection instance already exists. If it does, it will be
     * returned. If not, it will be created and returned. This makes it
     * impossible to have more than one instance of a connection (singleton)
     *
     * @return
     */
    public static Connection getInstanceHIS() {
        try {
            if (instanceHIS == null || instanceHIS.isClosed()) {
                makeInstanceHIS();
                return instanceHIS;
            } else {
                return instanceHIS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Checks if a connection instance already exists. If it does, it will be
     * returned. If not, it will be created and returned. This makes it
     * impossible to have more than one instance of a connection (singleton)
     *
     * @return
     */
    public static Connection getInstanceHISM() {
        try {
            if (instanceHISM == null || instanceHISM.isClosed()) {
                makeInstanceHISM();
                return instanceHISM;
            } else {
                return instanceHISM;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Creates a connection instance1
     */
    private static void makeInstanceHISM() {
        String FILENAME = "dbconfig.properties"; // Indikates where config file is
        Properties dbProp = new Properties(); // Creates a properties type object
        FileInputStream in = null;

        try {
            in = new FileInputStream(FILENAME); // Creates a fileinputstream and loads the config file
            dbProp.load(in); // Loads the config file into our properties object (to hold the config in memory to use)
            in.close(); // closes the fileinputstream (we dont need anymore)
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), FILENAME + " was not found", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        String dbUser = dbProp.getProperty("db1User"); // Gets the dbUser from the properties object
        String dbPass = dbProp.getProperty("db1Pass"); // Gets the dbPass from the properties object
        String dbURL = dbProp.getProperty("db1URL"); // Gets the dbURL from the properies object
        
        try {
            instanceHISM = DriverManager.getConnection(dbURL, dbUser, dbPass); // Creates and gets the MySQL connection and saves it to the instance variable
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Could not connect to database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Creates a connection instance2
     */
    private static void makeInstanceHIS() {
        String FILENAME = "dbconfig.properties"; // Indikates where config file is
        Properties dbProp = new Properties(); // Creates a properties type object
        FileInputStream in = null;

        try {
            in = new FileInputStream(FILENAME); // Creates a fileinputstream and loads the config file
            dbProp.load(in); // Loads the config file into our properties object (to hold the config in memory to use)
            in.close(); // closes the fileinputstream (we dont need anymore)
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), FILENAME + " was not found", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        String dbUser = dbProp.getProperty("db2User"); // Gets the dbUser from the properties object
        String dbPass = dbProp.getProperty("db2Pass"); // Gets the dbPass from the properties object
        String dbURL = dbProp.getProperty("db2URL"); // Gets the dbURL from the properies object
        
        try {
            instanceHIS = DriverManager.getConnection(dbURL, dbUser, dbPass); // Creates and gets the MySQL connection and saves it to the instance variable
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Could not connect to database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
