/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Patrick
 */
public class PropertiesTool {
    
    private static Properties instance;
    
    private PropertiesTool() {}
    
    public static Properties getInstance() {
        if(instance == null) {
            makeInstance();
            return instance;
        } else {
            return instance;
        }
    }
    
    public static void writeProperties(Properties instance) {
        OutputStream output = null;
        try {
            output = new FileOutputStream("configuration.properties");
            instance.store(output, null);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JDialog(), "Konfigurations filen blev ikke fundet", "Fejl", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JDialog(), "Der skete en fejl da konfigurationen blev skrevet", "Fejl", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JDialog(), "Kunne ikke afslutte skrivning af konfigurations filen", "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private static void makeInstance() {
        instance = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("configuration.properties");
            instance.load(input);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JDialog(), "Konfigurations filen blev ikke fundet", "Fejl", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JDialog(), "Der skete en fejl da konfigurationen blev indlæst", "Fejl", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JDialog(), "Kunne ikke afslutte indlæsning af konfigurations filen", "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
