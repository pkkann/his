package view.image;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author pkann
 */
public class StatusPicPane extends PicturePane_256 {

    public StatusPicPane() {
    }

    public void setStatus(int status) {
        switch (status) {
            case 0:
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("ok.png"));
                    this.setPicture(img);
                } catch (IOException ex) {
                    Logger.getLogger(StatusPicPane.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 1:
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("quarantine.png"));
                    this.setPicture(img);
                } catch (IOException ex) {
                    Logger.getLogger(StatusPicPane.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                this.setPicture(null);
                break;
            case 3:
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("expired.png"));
                    this.setPicture(img);
                } catch (IOException ex) {
                    Logger.getLogger(StatusPicPane.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
