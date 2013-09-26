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

    private static Connection instance = null;
    
    /**
     * private constructor (this class is singleton)
     */
    private DBTool() {
    }

    /**
     * Checks if a connection instance already exists. If it does, it will be returned. If not, it will be created and returned.
     * This makes it impossible to have more than one instance of a connection (singleton)
     * @return 
     */
    public static Connection getInstance() {
        try {
            if (instance == null || instance.isClosed()) {
                makeInstance();
                return instance;
            } else {
                return instance;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Creates a connection instance
     */
    private static void makeInstance() {
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

        String dbUser = dbProp.getProperty("dbUser"); // Gets the dbUser from the properties object
        String dbPass = dbProp.getProperty("dbPass"); // Gets the dbPass from the properties object
        String dbURL = dbProp.getProperty("dbURL"); // Gets the dbURL from the properies object

        try {
            instance = DriverManager.getConnection(dbURL, dbUser, dbPass); // Creates and gets the MySQL connection and saves it to the instance variable
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Could not connect to database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
