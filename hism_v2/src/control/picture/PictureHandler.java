/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.picture;

import java.io.File;

/**
 *
 * @author patrick
 */
public class PictureHandler {
    
    private PictureRegister pictureRegister;
    
    public PictureHandler(PictureRegister pictureRegister) {
        this.pictureRegister = pictureRegister;
    }
    
    public void insertPicture(File f) {
        pictureRegister.add(f);
    }
    
    public int getpictureID() {
        return pictureRegister.size() + 1;
    }
    
}
