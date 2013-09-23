/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.csv;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class CSVTool {

    public static void generateReport(ArrayList<String[]> report, String filename) throws FileNotFoundException, IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename + ".csv"));

        for (String[] s : report) {
            for (int i = 0; i < s.length; i++) {
                dos.writeBytes(s[i]);
                if((i + 1) < s.length) {
                    dos.writeBytes(",");
                }
            }
            dos.writeBytes("\n");
        }

        dos.close();
    }
}
