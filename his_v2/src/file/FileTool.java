/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

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

    private FileTool() {
    }

    public static boolean copyFile(File fIn, File fOut) {
        try {
            new File(fOut.getParent()).mkdir();
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
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    public static void deleteFile(File f) {
        f.delete();
    }

    public static void createFolders() {
        File picDir = new File(his.His.picDir);
        File personsPicDir = new File(his.His.picDir + "/persons");
        File guestsPicDir = new File(his.His.picDir + "/guests");
        File reportDir = new File(his.His.reportDir);
        File webcamDir = new File(his.His.webcamDir);
        picDir.mkdir();
        personsPicDir.mkdir();
        guestsPicDir.mkdir();
        reportDir.mkdir();
        webcamDir.mkdir();
    }
}
