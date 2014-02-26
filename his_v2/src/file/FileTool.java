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
        File personsPicDir = new File(his.His.picDir + "/personer");
        File guestsPicDir = new File(his.His.picDir + "/gæster");
        File reportDir = new File(his.His.reportDir);
        File reportEnrolledDir = new File(his.His.reportEnrolledDir);
        File reportPersonsDir = new File(his.His.reportPersonsDir);
        File reportUsersDir = new File(his.His.reportUsersDir);
        File reportQuarantinesDir = new File(his.His.reportQuarantinesDir);
        File webcamDir = new File(his.His.webcamDir);
        picDir.mkdir();
        personsPicDir.mkdir();
        guestsPicDir.mkdir();
        reportDir.mkdir();
        reportEnrolledDir.mkdir();
        reportPersonsDir.mkdir();
        reportUsersDir.mkdir();
        reportQuarantinesDir.mkdir();
        webcamDir.mkdir();
    }
}
