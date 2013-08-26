/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author pkann
 */
public class FileTool {

    private static FileInputStream in;
    private static FileOutputStream out;
    
    private FileTool() {}

    public static boolean copyFile(File fIn, File fOut) {
        try {
            in = new FileInputStream(fIn);
            out = new FileOutputStream(fOut);


            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
